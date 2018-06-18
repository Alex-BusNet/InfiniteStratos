package com.sparta.is.core.utils;

import com.sparta.is.core.InfiniteStratos;
import com.sparta.is.core.utils.compat.FilterSettings;
import com.sparta.is.core.utils.compat.ISlotlessItemHandler;
import com.sparta.is.core.utils.compat.SlotlessableItemHandlerWrapper;
import com.sparta.is.core.utils.helpers.ItemStackHandlerCustom;
import com.sparta.is.core.utils.helpers.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;

public class WorldUtil
{
    public static boolean doItemInteraction(SlotlessableItemHandlerWrapper extractWrapper, SlotlessableItemHandlerWrapper insertWrapper, int maxExtract) {
        return doItemInteraction(extractWrapper, insertWrapper, maxExtract, null);
    }

    public static boolean doItemInteraction(SlotlessableItemHandlerWrapper extractWrapper, SlotlessableItemHandlerWrapper insertWrapper, int maxExtract, FilterSettings filter) {
        return doItemInteraction(extractWrapper, insertWrapper, maxExtract, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, filter);
    }

    public static boolean doItemInteraction(SlotlessableItemHandlerWrapper extractWrapper, SlotlessableItemHandlerWrapper insertWrapper, int maxExtract, int extractSlotStart, int extractSlotEnd, int insertSlotStart, int insertSlotEnd, FilterSettings filter) {
        ItemStack theoreticalExtract = extractItem(extractWrapper, maxExtract, true, extractSlotStart, extractSlotEnd, filter);
        if (ItemStackUtils.isValid(theoreticalExtract)) {
            ItemStack remaining = insertItem(insertWrapper, theoreticalExtract, false, insertSlotStart, insertSlotEnd);
            if (! ItemStack.areItemStacksEqual(remaining, theoreticalExtract)) {
                int toExtract = !ItemStackUtils.isValid(remaining) ? ItemStackUtils.getStackSize(theoreticalExtract) : ItemStackUtils.getStackSize(theoreticalExtract) - ItemStackUtils.getStackSize(remaining);
                extractItem(extractWrapper, toExtract, false, extractSlotStart, extractSlotEnd, filter);
                return true;
            }
        }
        return false;
    }

    public static ItemStack extractItem(SlotlessableItemHandlerWrapper extractWrapper, int maxExtract, boolean simulate, int slotStart, int slotEnd, FilterSettings filter) {
        ItemStack extracted = ItemStackUtils.getEmpty();

        if (InfiniteStratos.commonCapsLoaded) {
            Object handler = extractWrapper.getSlotlessHandler();
            if (handler instanceof ISlotlessItemHandler) {
                ISlotlessItemHandler slotless = (ISlotlessItemHandler) handler;

                if (filter == null || !filter.needsCheck()) {
                    extracted = slotless.extractItem(maxExtract, simulate);
                    return extracted;
                } else {
                    ItemStack would = slotless.extractItem(maxExtract, true);
                    if (filter.check(would)) {
                        if (simulate) {
                            extracted = would;
                        } else {
                            extracted = slotless.extractItem(maxExtract, false);
                        }
                    }
                    //Leave the possibility to fall back to vanilla when there is a filter
                }
            }
        }

        if (!ItemStackUtils.isValid(extracted)) {
            IItemHandler handler = extractWrapper.getNormalHandler();
            if (handler != null) {
                for (int i = Math.max(0, slotStart); i < Math.min(slotEnd, handler.getSlots()); i++) {
                    if (filter == null || !filter.needsCheck() || filter.check(handler.getStackInSlot(i))) {
                        extracted = handler.extractItem(i, maxExtract, simulate);

                        if (ItemStackUtils.isValid(extracted)) {
                            break;
                        }
                    }
                }
            }
        }

        return extracted;
    }

    public static ItemStack insertItem(SlotlessableItemHandlerWrapper insertWrapper, ItemStack stack, boolean simulate, int slotStart, int slotEnd) {
        ItemStack remain = ItemStackUtils.validateCopy(stack);

        if (InfiniteStratos.commonCapsLoaded) {
            Object handler = insertWrapper.getSlotlessHandler();
            if (handler instanceof ISlotlessItemHandler) {
                remain = ((ISlotlessItemHandler) handler).insertItem(remain, simulate);

                if (!ItemStack.areItemStacksEqual(remain, stack)) { return remain; }
            }
        }

        IItemHandler handler = insertWrapper.getNormalHandler();
        if (handler != null) {
            for (int i = Math.max(0, slotStart); i < Math.min(slotEnd, handler.getSlots()); i++) {
                remain = handler.insertItem(i, remain, simulate);
            }
        }

        return remain;
    }

    public static void doEnergyInteraction(TileEntity tileFrom, TileEntity tileTo, EnumFacing sideTo, int maxTransfer) {
        if (maxTransfer > 0) {
            EnumFacing opp = sideTo == null ? null : sideTo.getOpposite();
            if (tileFrom.hasCapability(CapabilityEnergy.ENERGY, sideTo) && tileTo.hasCapability(CapabilityEnergy.ENERGY, opp)) {
                IEnergyStorage handlerFrom = tileFrom.getCapability(CapabilityEnergy.ENERGY, sideTo);
                IEnergyStorage handlerTo = tileTo.getCapability(CapabilityEnergy.ENERGY, opp);

                if (handlerFrom != null && handlerTo != null) {
                    int drain = handlerFrom.extractEnergy(maxTransfer, true);
                    if (drain > 0) {
                        int filled = handlerTo.receiveEnergy(drain, false);
                        handlerFrom.extractEnergy(filled, false);
                        return;
                    }
                }
            }
        }
    }

    public static void doFluidInteraction(TileEntity tileFrom, TileEntity tileTo, EnumFacing sideTo, int maxTransfer) {
        if (maxTransfer > 0) {
            if (tileFrom.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, sideTo) && tileTo.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, sideTo.getOpposite())) {
                IFluidHandler handlerFrom = tileFrom.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, sideTo);
                IFluidHandler handlerTo = tileTo.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, sideTo.getOpposite());
                FluidStack drain = handlerFrom.drain(maxTransfer, false);
                if (drain != null) {
                    int filled = handlerTo.fill(drain.copy(), true);
                    handlerFrom.drain(filled, true);
                }
            }
        }
    }

    /**
     * Checks if a given Block with a given Meta is present in given Positions
     *
     * @param positions The Positions, an array of {x, y, z} arrays containing Positions
     * @param block     The Block
     * @param meta      The Meta
     * @param world     The World
     * @return Is every block present?
     */
    public static boolean hasBlocksInPlacesGiven(BlockPos[] positions, Block block, int meta, World world) {
        for (BlockPos pos : positions) {
            IBlockState state = world.getBlockState(pos);
            if (!(state.getBlock() == block && block.getMetaFromState(state) == meta)) { return false; }
        }
        return true;
    }

    public static ItemStack useItemAtSide(EnumFacing side, World world, BlockPos pos, ItemStack stack) {
        if (world instanceof WorldServer && ItemStackUtils.isValid(stack) && pos != null) {
            BlockPos offsetPos = pos.offset(side);
            IBlockState state = world.getBlockState(offsetPos);
            Block block = state.getBlock();
            boolean replaceable = block.isReplaceable(world, offsetPos);

            //Redstone
            if (replaceable && stack.getItem() == Items.REDSTONE) {
                world.setBlockState(offsetPos, Blocks.REDSTONE_WIRE.getDefaultState(), 2);
                return ItemStackUtils.addStackSize(stack, -1);
            }

            //Plants
            if (replaceable && stack.getItem() instanceof IPlantable ) {
                if (((IPlantable) stack.getItem()).getPlant(world, offsetPos).getBlock().canPlaceBlockAt(world, offsetPos)) {
                    if (world.setBlockState(offsetPos, ((IPlantable) stack.getItem()).getPlant(world, offsetPos), 2)) { return ItemStackUtils.addStackSize(stack, -1); }
                }
            }

            //Everything else
            try {
                if (world instanceof WorldServer) {
                    FakePlayer fake = FakePlayerFactory.getMinecraft((WorldServer) world);
                    ItemStack heldBefore = fake.getHeldItemMainhand();
                    setHandItemWithoutAnnoyingSound(fake, EnumHand.MAIN_HAND, stack.copy());

                    fake.getHeldItemMainhand().onItemUse(fake, world, offsetPos, fake.getActiveHand(), side.getOpposite(), 0.5F, 0.5F, 0.5F);

                    ItemStack result = fake.getHeldItem(EnumHand.MAIN_HAND);
                    setHandItemWithoutAnnoyingSound(fake, EnumHand.MAIN_HAND, heldBefore);
                    return result;
                }
            } catch (Exception e) {
                LogHelper.error("Something that places Blocks at " + offsetPos.getX() + ", " + offsetPos.getY() + ", " + offsetPos.getZ() + " in World " + world.provider.getDimension() + " threw an Exception! Don't let that happen again!", e);
            }
        }
        return stack;
    }

    public static boolean dropItemAtSide(EnumFacing side, World world, BlockPos pos, ItemStack stack) {
        BlockPos coords = pos.offset(side);
        if (world.isBlockLoaded(coords)) {
            EntityItem item = new EntityItem(world, coords.getX() + 0.5, coords.getY() + 0.5, coords.getZ() + 0.5, stack);
            item.motionX = 0;
            item.motionY = 0;
            item.motionZ = 0;

            return world.spawnEntity(item);
        }
        return false;
    }

    public static EnumFacing getDirectionBySidesInOrder(int side) {
        switch (side) {
            case 0:
                return EnumFacing.UP;
            case 1:
                return EnumFacing.DOWN;
            case 2:
                return EnumFacing.NORTH;
            case 3:
                return EnumFacing.EAST;
            case 4:
                return EnumFacing.SOUTH;
            default:
                return EnumFacing.WEST;
        }
    }

    public static EnumFacing getDirectionByPistonRotation(IBlockState state) {
        return state.getValue(BlockDirectional.FACING);
    }

    public static ArrayList<Material> getMaterialsAround(World world, BlockPos pos) {
        ArrayList<Material> blocks = new ArrayList<Material>();
        blocks.add(world.getBlockState(pos.offset(EnumFacing.NORTH)).getMaterial());
        blocks.add(world.getBlockState(pos.offset(EnumFacing.EAST)).getMaterial());
        blocks.add(world.getBlockState(pos.offset(EnumFacing.SOUTH)).getMaterial());
        blocks.add(world.getBlockState(pos.offset(EnumFacing.WEST)).getMaterial());
        return blocks;
    }

    public static boolean addToInventory(ItemStackHandlerCustom inventory, List<ItemStack> stacks, boolean actuallyDo) {
        return addToInventory(inventory, 0, inventory.getSlots(), stacks, actuallyDo);
    }

    public static boolean addToInventory(ItemStackHandlerCustom inventory, int start, int end, List<ItemStack> stacks, boolean actuallyDo) {
        //Copy the slots if just testing to later load them again
        ItemStack[] backupSlots = null;
        if (!actuallyDo) {
            backupSlots = new ItemStack[inventory.getSlots()];
            for (int i = 0; i < backupSlots.length; i++) {
                ItemStack stack = inventory.getStackInSlot(i);
                backupSlots[i] = ItemStackUtils.validateCopy(stack);
            }
        }

        int working = 0;
        for (ItemStack stack : stacks) {
            if (ItemStackUtils.isValid(stack)) {
                for (int i = start; i < end; i++) {
                    stack = inventory.insertItemInternal(i, stack, false);

                    if (!ItemStackUtils.isValid(stack)) {
                        working++;
                        break;
                    }
                }
            } else {
                working++;
            }
        }

        //Load the slots again
        if (!actuallyDo) {
            for (int i = 0; i < backupSlots.length; i++)
                inventory.setStackInSlot(i, ItemStackUtils.validateCheck(backupSlots[i]));
        }

        return working >= stacks.size();
    }

    public static int findFirstFilledSlot(ItemStackHandlerCustom slots) {
        for (int i = 0; i < slots.getSlots(); i++) {
            if (ItemStackUtils.isValid(slots.getStackInSlot(i))) { return i; }
        }
        return 0;
    }

    public static RayTraceResult getNearestPositionWithAir(World world, EntityPlayer player, int reach) {
        return getMovingObjectPosWithReachDistance(world, player, reach, false, false, true);
    }

    private static RayTraceResult getMovingObjectPosWithReachDistance(World world, EntityPlayer player, double distance, boolean p1, boolean p2, boolean p3) {
        float f = player.rotationPitch;
        float f1 = player.rotationYaw;
        double d0 = player.posX;
        double d1 = player.posY + (double) player.getEyeHeight();
        double d2 = player.posZ;
        Vec3d vec3 = new Vec3d(d0, d1, d2);
        float f2 = MathHelper.cos(-f1 * 0.017453292F - (float) Math.PI);
        float f3 = MathHelper.sin(-f1 * 0.017453292F - (float) Math.PI);
        float f4 = -MathHelper.cos(-f * 0.017453292F);
        float f5 = MathHelper.sin(-f * 0.017453292F);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vec3d vec31 = vec3.addVector((double) f6 * distance, (double) f5 * distance, (double) f7 * distance);
        return world.rayTraceBlocks(vec3, vec31, p1, p2, p3);
    }

    public static RayTraceResult getNearestBlockWithDefaultReachDistance(World world, EntityPlayer player) {
        return getNearestBlockWithDefaultReachDistance(world, player, false, true, false);
    }

    public static RayTraceResult getNearestBlockWithDefaultReachDistance(World world, EntityPlayer player, boolean stopOnLiquids, boolean ignoreBlockWithoutBoundingBox, boolean returnLastUncollidableBlock) {
        return getMovingObjectPosWithReachDistance(world, player, player.getEntityAttribute(EntityPlayer.REACH_DISTANCE).getAttributeValue(), stopOnLiquids, ignoreBlockWithoutBoundingBox, returnLastUncollidableBlock);
    }

    public static void setHandItemWithoutAnnoyingSound(EntityPlayer player, EnumHand hand, ItemStack stack) {
        if (hand == EnumHand.MAIN_HAND) {
            player.inventory.mainInventory.set(player.inventory.currentItem, stack);
        } else if (hand == EnumHand.OFF_HAND) {
            player.inventory.offHandInventory.set(0, stack);
        }
    }

    public static float fireFakeHarvestEventsForDropChance(NonNullList<ItemStack> drops, World world, BlockPos pos) {
        if (world instanceof WorldServer) {
            FakePlayer fake = FakePlayerFactory.getMinecraft((WorldServer) world);
            IBlockState state = world.getBlockState(pos);

            BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(world, pos, state, fake);
            if (! MinecraftForge.EVENT_BUS.post(event)) { return ForgeEventFactory.fireBlockHarvesting(drops, world, pos, state, 0, 1, false, fake); }
        }
        return 0F;
    }

    //Stolen from TiC
    public static boolean breakExtraBlock(ItemStack stack, World world, EntityPlayer player, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (player.capabilities.isCreativeMode) {
            if (block.removedByPlayer(state, world, pos, player, false)) {
                block.onBlockDestroyedByPlayer(world, pos, state);
            }

            // send update to client
            if (!world.isRemote) {
                ((EntityPlayerMP) player).connection.sendPacket(new SPacketBlockChange(world, pos));
            }
            return true;
        }

        // callback to the tool the player uses. Called on both sides. This damages the tool n stuff.
        stack.onBlockDestroyed(world, state, pos, player);

        // server sided handling
        if (!world.isRemote) {
            // send the blockbreak event
            int xp = ForgeHooks.onBlockBreakEvent(world, ((EntityPlayerMP) player).interactionManager.getGameType(), (EntityPlayerMP) player, pos);
            if (xp == -1) return false;

            TileEntity tileEntity = world.getTileEntity(pos);
            if (block.removedByPlayer(state, world, pos, player, true)) { // boolean is if block can be harvested, checked above
                block.onBlockDestroyedByPlayer(world, pos, state);
                block.harvestBlock(world, player, pos, state, tileEntity, stack);
                block.dropXpOnBlockBreak(world, pos, xp);
            }

            // always send block update to client
            ((EntityPlayerMP) player).connection.sendPacket(new SPacketBlockChange(world, pos));
            return true;
        }
        // client sided handling
        else {
            // clientside we do a "this block has been clicked on long enough to be broken" call. This should not send any new packets
            // the code above, executed on the server, sends a block-updates that give us the correct state of the block we destroy.

            // following code can be found in PlayerControllerMP.onPlayerDestroyBlock
            world.playEvent(2001, pos, Block.getStateId(state));
            if (block.removedByPlayer(state, world, pos, player, true)) {
                block.onBlockDestroyedByPlayer(world, pos, state);
            }
            // callback to the tool
            stack.onBlockDestroyed(world, state, pos, player);

            // send an update to the server, so we get an update back
            InfiniteStratos.proxy.getClientProxy().sendBreakPacket(pos);
            return true;
        }
    }
}