package com.sparta.is.utils;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.LinkedList;
import java.util.List;

public class BlockHelper
{
    private BlockHelper() {

    }

    public static int MAX_ID = 1024;
    public static byte[] rotateType = new byte[MAX_ID];
    public static final int[][] SIDE_COORD_MOD = { { 0, -1, 0 }, { 0, 1, 0 }, { 0, 0, -1 }, { 0, 0, 1 }, { -1, 0, 0 }, { 1, 0, 0 } };
    public static float[][] SIDE_COORD_AABB = { { 1, -2, 1 }, { 1, 2, 1 }, { 1, 1, 1 }, { 1, 1, 2 }, { 1, 1, 1 }, { 2, 1, 1 } };
    public static final byte[] SIDE_LEFT = { 4, 5, 5, 4, 2, 3 };
    public static final byte[] SIDE_RIGHT = { 5, 4, 4, 5, 3, 2 };
    public static final byte[] SIDE_OPPOSITE = { 1, 0, 3, 2, 5, 4 };
    public static final byte[] SIDE_ABOVE = { 3, 2, 1, 1, 1, 1 };
    public static final byte[] SIDE_BELOW = { 2, 3, 0, 0, 0, 0 };

    // These assume facing is towards negative - looking AT side 1, 3, or 5.
    public static final byte[] ROTATE_CLOCK_Y = { 0, 1, 4, 5, 3, 2 };
    public static final byte[] ROTATE_CLOCK_Z = { 5, 4, 2, 3, 0, 1 };
    public static final byte[] ROTATE_CLOCK_X = { 2, 3, 1, 0, 4, 5 };

    public static final byte[] ROTATE_COUNTER_Y = { 0, 1, 5, 4, 2, 3 };
    public static final byte[] ROTATE_COUNTER_Z = { 4, 5, 2, 3, 1, 0 };
    public static final byte[] ROTATE_COUNTER_X = { 3, 2, 0, 1, 4, 5 };

    public static final byte[] INVERT_AROUND_Y = { 0, 1, 3, 2, 5, 4 };
    public static final byte[] INVERT_AROUND_Z = { 1, 0, 2, 3, 5, 4 };
    public static final byte[] INVERT_AROUND_X = { 1, 0, 3, 2, 4, 5 };

    // Map which gives relative Icon to use on a block which can be placed on
    // any side.
    public static final byte[][] ICON_ROTATION_MAP = new byte[6][];

    static {
        ICON_ROTATION_MAP[0] = new byte[] { 0, 1, 2, 3, 4, 5 };
        ICON_ROTATION_MAP[1] = new byte[] { 1, 0, 2, 3, 4, 5 };
        ICON_ROTATION_MAP[2] = new byte[] { 3, 2, 0, 1, 4, 5 };
        ICON_ROTATION_MAP[3] = new byte[] { 3, 2, 1, 0, 5, 4 };
        ICON_ROTATION_MAP[4] = new byte[] { 3, 2, 5, 4, 0, 1 };
        ICON_ROTATION_MAP[5] = new byte[] { 3, 2, 4, 5, 1, 0 };
    }

    public static final class RotationType {

        public static final int PREVENT = -1;
        public static final int FOUR_WAY = 1;
        public static final int SIX_WAY = 2;
        public static final int RAIL = 3;
        public static final int PUMPKIN = 4;
        public static final int STAIRS = 5;
        public static final int REDSTONE = 6;
        public static final int LOG = 7;
        public static final int SLAB = 8;
        public static final int CHEST = 9;
        public static final int LEVER = 10;
        public static final int SIGN = 11;
    }

    static { // TODO: review which of these can be removed in favor of the
        // vanilla handler
        rotateType[Block.getIdFromBlock(Blocks.BED)] = RotationType.PREVENT;

        rotateType[Block.getIdFromBlock(Blocks.STONE_SLAB)] = RotationType.SLAB;
        rotateType[Block.getIdFromBlock(Blocks.WOODEN_SLAB)] = RotationType.SLAB;

        rotateType[Block.getIdFromBlock(Blocks.RAIL)] = RotationType.RAIL;
        rotateType[Block.getIdFromBlock(Blocks.GOLDEN_RAIL)] = RotationType.RAIL;
        rotateType[Block.getIdFromBlock(Blocks.DETECTOR_RAIL)] = RotationType.RAIL;
        rotateType[Block.getIdFromBlock(Blocks.ACTIVATOR_RAIL)] = RotationType.RAIL;

        rotateType[Block.getIdFromBlock(Blocks.PUMPKIN)] = RotationType.PUMPKIN;
        rotateType[Block.getIdFromBlock(Blocks.LIT_PUMPKIN)] = RotationType.PUMPKIN;

        rotateType[Block.getIdFromBlock(Blocks.FURNACE)] = RotationType.FOUR_WAY;
        rotateType[Block.getIdFromBlock(Blocks.LIT_FURNACE)] = RotationType.FOUR_WAY;
        rotateType[Block.getIdFromBlock(Blocks.ENDER_CHEST)] = RotationType.FOUR_WAY;

        rotateType[Block.getIdFromBlock(Blocks.TRAPPED_CHEST)] = RotationType.CHEST;
        rotateType[Block.getIdFromBlock(Blocks.CHEST)] = RotationType.CHEST;

        rotateType[Block.getIdFromBlock(Blocks.DISPENSER)] = RotationType.SIX_WAY;

        rotateType[Block.getIdFromBlock(Blocks.STICKY_PISTON)] = RotationType.SIX_WAY;
        rotateType[Block.getIdFromBlock(Blocks.PISTON)] = RotationType.SIX_WAY;
        rotateType[Block.getIdFromBlock(Blocks.HOPPER)] = RotationType.SIX_WAY;
        rotateType[Block.getIdFromBlock(Blocks.DROPPER)] = RotationType.SIX_WAY;

        rotateType[Block.getIdFromBlock(Blocks.UNPOWERED_REPEATER)] = RotationType.REDSTONE;
        rotateType[Block.getIdFromBlock(Blocks.UNPOWERED_COMPARATOR)] = RotationType.REDSTONE;
        rotateType[Block.getIdFromBlock(Blocks.POWERED_REPEATER)] = RotationType.REDSTONE;
        rotateType[Block.getIdFromBlock(Blocks.POWERED_COMPARATOR)] = RotationType.REDSTONE;

        rotateType[Block.getIdFromBlock(Blocks.LEVER)] = RotationType.LEVER;

        rotateType[Block.getIdFromBlock(Blocks.STANDING_SIGN)] = RotationType.SIGN;

        rotateType[Block.getIdFromBlock(Blocks.OAK_STAIRS)] = RotationType.STAIRS;
        rotateType[Block.getIdFromBlock(Blocks.STONE_STAIRS)] = RotationType.STAIRS;
        rotateType[Block.getIdFromBlock(Blocks.BRICK_STAIRS)] = RotationType.STAIRS;
        rotateType[Block.getIdFromBlock(Blocks.STONE_BRICK_STAIRS)] = RotationType.STAIRS;
        rotateType[Block.getIdFromBlock(Blocks.NETHER_BRICK_STAIRS)] = RotationType.STAIRS;
        rotateType[Block.getIdFromBlock(Blocks.SANDSTONE_STAIRS)] = RotationType.STAIRS;
        rotateType[Block.getIdFromBlock(Blocks.SPRUCE_STAIRS)] = RotationType.STAIRS;
        rotateType[Block.getIdFromBlock(Blocks.BIRCH_STAIRS)] = RotationType.STAIRS;
        rotateType[Block.getIdFromBlock(Blocks.JUNGLE_STAIRS)] = RotationType.STAIRS;
        rotateType[Block.getIdFromBlock(Blocks.QUARTZ_STAIRS)] = RotationType.STAIRS;
    }

    public static int getMicroBlockAngle(int side, float hitX, float hitY, float hitZ) {

        int direction = side ^ 1;
        float degreeCenter = 0.32f / 2;

        float x = 0, y = 0;
        switch (side >> 1) {
            case 0:
                x = hitX;
                y = hitZ;
                break;
            case 1:
                x = hitX;
                y = hitY;
                break;
            case 2:
                x = hitY;
                y = hitZ;
                break;
        }
        x -= .5f;
        y -= .5f;

        if (x * x + y * y > degreeCenter * degreeCenter) {

            int a = (int) ((Math.atan2(x, y) + Math.PI) * 4 / Math.PI);
            a = ++a & 7;
            switch (a >> 1) {
                case 0:
                case 4:
                    direction = 2;
                    break;
                case 1:
                    direction = 4;
                    break;
                case 2:
                    direction = 3;
                    break;
                case 3:
                    direction = 5;
                    break;
            }
        }
        return direction;
    }

    public static EnumFacing getMicroBlockAngle(EnumFacing side, float hitX, float hitY, float hitZ) {

        return EnumFacing.VALUES[getMicroBlockAngle(side.ordinal(), hitX, hitY, hitZ)];
    }

    public static int getHighestY(World world, BlockPos blockPos) {

        return world.getChunkFromBlockCoords(blockPos).getTopFilledSegment() + 16;
    }

    public static int getSurfaceBlockY(World world, BlockPos blockPos) {

        int y = world.getChunkFromBlockCoords(blockPos).getTopFilledSegment() + 16;

        Block block;
        do {
            if (--y < 0) {
                break;
            }

            blockPos.add(0, y, 0);
            block = world.getBlockState(blockPos).getBlock();
        } while ((world.getBlockState(blockPos).getBlock() == Blocks.AIR) || block.isReplaceable(world, blockPos) || (world.getBlockState(blockPos).getBlock() == Blocks.LEAVES) || block.isFoliage(world, blockPos)
                || block.canBeReplacedByLeaves(world.getBlockState(blockPos), world, blockPos));
        return y;
    }

    public static int getTopBlockY(World world, BlockPos blockPos) {

        int y = world.getChunkFromBlockCoords(blockPos).getTopFilledSegment() + 16;

        Block block;
        do {
            if (--y < 0) {
                break;
            }

            blockPos.add(0, y, 0);
            block = world.getBlockState(blockPos).getBlock();
        } while (world.getBlockState(blockPos).getBlock() == Blocks.AIR);
        return y;
    }

//	public static MovingObjectPosition getCurrentMovingObjectPosition(EntityPlayer player, double distance, boolean fluid) {
//
//		Vec3 posVec = new Vec3(player.posX, player.posY, player.posZ);
//		Vec3 lookVec = player.getLook(1);
//		posVec.addVector(0, player.getEyeHeight(), 0);
//		lookVec = posVec.addVector(lookVec.xCoord * distance, lookVec.yCoord * distance, lookVec.zCoord * distance);
//		return player.worldObj.rayTraceBlocks(posVec, lookVec, fluid);
//	}
//
//	public static MovingObjectPosition getCurrentMovingObjectPosition(EntityPlayer player, double distance) {
//
//		return getCurrentMovingObjectPosition(player, distance, false);
//	}
//
//	public static MovingObjectPosition getCurrentMovingObjectPosition(EntityPlayer player, boolean fluid) {
//
//		return getCurrentMovingObjectPosition(player, player.capabilities.isCreativeMode ? 5.0F : 4.5F, fluid);
//	}
//
//	public static MovingObjectPosition getCurrentMovingObjectPosition(EntityPlayer player) {
//
//		return getCurrentMovingObjectPosition(player, player.capabilities.isCreativeMode ? 5.0F : 4.5F, false);
//	}
//
//	public static int getCurrentMousedOverSide(EntityPlayer player) {
//
//		MovingObjectPosition mouseOver = getCurrentMovingObjectPosition(player);
//		return mouseOver == null ? 0 : mouseOver.sideHit.ordinal();
//	}

    public static int determineXZPlaceFacing(EntityLivingBase living) {

        int quadrant = MathHelper.floor(living.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        switch (quadrant) {
            case 0:
                return 2;
            case 1:
                return 5;
            case 2:
                return 3;
            case 3:
                return 4;
        }
        return 3;
    }

    public static boolean isEqual(Block blockA, Block blockB) {

        if (blockA == blockB) {
            return true;
        }
        if (blockA == null | blockB == null) {
            return false;
        }
        return blockA.equals(blockB) || blockA.isAssociatedBlock(blockB);
    }

    public static Block getAdjacentBlock(World world, BlockPos blockPos, EnumFacing dir) {

        blockPos.add(dir.getFrontOffsetX(), dir.getFrontOffsetY(), dir.getFrontOffsetZ());
        return world == null || world.isAirBlock(blockPos) ? Blocks.AIR : world.getBlockState(blockPos).getBlock();
    }

    public static Block getAdjacentBlock(World world, BlockPos blockPos, int side) {

        return world == null ? Blocks.AIR : getAdjacentBlock(world, blockPos, EnumFacing.getFront(side));
    }

    /* Safe Tile Entity Retrieval */
    public static TileEntity getAdjacentTileEntity(World world, BlockPos blockPos, EnumFacing dir) {

        blockPos.add(dir.getFrontOffsetX(), dir.getFrontOffsetY(), dir.getFrontOffsetZ());
        return world == null || !world.isAirBlock(blockPos) ? null : world.getTileEntity(blockPos);
    }

    public static TileEntity getAdjacentTileEntity(World world, BlockPos blockPos, int side) {

        return world == null ? null : getAdjacentTileEntity(world, blockPos, EnumFacing.getFront(side));
    }

    public static TileEntity getAdjacentTileEntity(TileEntity refTile, EnumFacing dir) {

        return refTile == null ? null : getAdjacentTileEntity(refTile.getWorld(), refTile.getPos(), dir);
    }

    public static TileEntity getAdjacentTileEntity(TileEntity refTile, int side) {

        return refTile == null ? null : getAdjacentTileEntity(refTile.getWorld(), refTile.getPos(),
                EnumFacing.values()[side]);
    }

    public static int determineAdjacentSide(TileEntity refTile, BlockPos blockPos) {

        return blockPos.getY() > refTile.getPos().getY() ? 1 : blockPos.getY() < refTile.getPos().getY() ? 0 : blockPos.getZ() > refTile.getPos().getZ() ? 3 : blockPos.getZ() < refTile.getPos().getZ() ? 2 : blockPos.getX() > refTile.getPos().getX() ? 5 : 4;
    }

	/* COORDINATE TRANSFORM */
//	public static int[] getAdjacentCoordinatesForSide(MovingObjectPosition pos) {
//
//		return getAdjacentCoordinatesForSide(pos.getBlockPos().getX(), pos.getBlockPos().getY(), pos.getBlockPos().getZ(), pos.sideHit.getIndex());
//	}

    public static int[] getAdjacentCoordinatesForSide(int x, int y, int z, int side) {

        return new int[] { x + SIDE_COORD_MOD[side][0], y + SIDE_COORD_MOD[side][1], z + SIDE_COORD_MOD[side][2] };
    }

//	public static AxisAlignedBB getAdjacentAABBForSide(MovingObjectPosition pos) {
//
//		return getAdjacentAABBForSide(pos.getBlockPos().getX(), pos.getBlockPos().getY(), pos.getBlockPos().getZ(), pos.sideHit.getIndex());
//	}

    public static AxisAlignedBB getAdjacentAABBForSide(int x, int y, int z, int side) {

        return new AxisAlignedBB(x + SIDE_COORD_MOD[side][0], y + SIDE_COORD_MOD[side][1], z + SIDE_COORD_MOD[side][2],
                x + SIDE_COORD_AABB[side][0], y + SIDE_COORD_AABB[side][1], z + SIDE_COORD_AABB[side][2]);
    }

    public static int getLeftSide(int side) {

        return SIDE_LEFT[side];
    }

    public static int getRightSide(int side) {

        return SIDE_RIGHT[side];
    }

    public static int getOppositeSide(int side) {

        return SIDE_OPPOSITE[side];
    }

    public static int getAboveSide(int side) {

        return SIDE_ABOVE[side];
    }

    public static int getBelowSide(int side) {

        return SIDE_BELOW[side];
    }

    /* BLOCK ROTATION */
    public static boolean canRotate(Block block) {

        int bId = Block.getIdFromBlock(block);
        return bId < MAX_ID ? rotateType[Block.getIdFromBlock(block)] != 0 : false;
    }

    public static int rotateVanillaBlock(World world, Block block, BlockPos blockPos) {

        int bId = Block.getIdFromBlock(block), bMeta = world.getBlockState(blockPos).getBlock().getMetaFromState(world.getBlockState(blockPos));
        switch (rotateType[bId]) {
            case RotationType.FOUR_WAY:
                return SIDE_LEFT[bMeta];
            case RotationType.SIX_WAY:
                if (bMeta < 6) {
                    return ++bMeta % 6;
                }
                return bMeta;
            case RotationType.RAIL:
                if (bMeta < 2) {
                    return ++bMeta % 2;
                }
                return bMeta;
            case RotationType.PUMPKIN:
                return ++bMeta % 4;
            case RotationType.STAIRS:
                return ++bMeta % 8;
            case RotationType.REDSTONE:
                int upper = bMeta & 0xC;
                int lower = bMeta & 0x3;
                return upper + ++lower % 4;
            case RotationType.LOG:
                return (bMeta + 4) % 12;
            case RotationType.SLAB:
                return (bMeta + 8) % 16;
            case RotationType.CHEST:
                int coords[] = new int[3];
                for (int i = 2; i < 6; i++) {
                    coords = getAdjacentCoordinatesForSide(blockPos.getX(), blockPos.getY(), blockPos.getZ(), i);
                    BlockPos coordPos = new BlockPos(coords[0], coords[1], coords[2]);
                    if (isEqual(world.getBlockState(coordPos).getBlock(), block)) {
                        world.setBlockState(coordPos, world.getBlockState(coordPos).getBlock().getStateFromMeta(bMeta), 1);
                        return SIDE_OPPOSITE[bMeta];
                    }
                }
                return SIDE_LEFT[bMeta];
            case RotationType.LEVER:
                int shift = 0;
                if (bMeta > 7) {
                    bMeta -= 8;
                    shift = 8;
                }
                if (bMeta == 5) {
                    return 6 + shift;
                } else if (bMeta == 6) {
                    return 5 + shift;
                } else if (bMeta == 7) {
                    return 0 + shift;
                } else if (bMeta == 0) {
                    return 7 + shift;
                }
                return bMeta + shift;
            case RotationType.SIGN:
                return ++bMeta % 16;
            case RotationType.PREVENT:
            default:
                return bMeta;
        }
    }

    public static int rotateVanillaBlockAlt(World world, Block block, BlockPos blockPos) {

        int bId = Block.getIdFromBlock(block), bMeta = world.getBlockState(blockPos).getBlock().getMetaFromState(world.getBlockState(blockPos));;
        switch (rotateType[bId]) {
            case RotationType.FOUR_WAY:
                return SIDE_RIGHT[bMeta];
            case RotationType.SIX_WAY:
                if (bMeta < 6) {
                    return (bMeta + 5) % 6;
                }
                return bMeta;
            case RotationType.RAIL:
                if (bMeta < 2) {
                    return ++bMeta % 2;
                }
                return bMeta;
            case RotationType.PUMPKIN:
                return (bMeta + 3) % 4;
            case RotationType.STAIRS:
                return (bMeta + 7) % 8;
            case RotationType.REDSTONE:
                int upper = bMeta & 0xC;
                int lower = bMeta & 0x3;
                return upper + (lower + 3) % 4;
            case RotationType.LOG:
                return (bMeta + 8) % 12;
            case RotationType.SLAB:
                return (bMeta + 8) % 16;
            case RotationType.CHEST:
                int coords[] = new int[3];
                for (int i = 2; i < 6; i++) {
                    coords = getAdjacentCoordinatesForSide(blockPos.getX(), blockPos.getY(), blockPos.getZ(), i);
                    BlockPos coordPos = new BlockPos(coords[0], coords[1], coords[2]);
                    if (isEqual(world.getBlockState(coordPos).getBlock(), block)) {
                        world.setBlockState(coordPos, world.getBlockState(coordPos).getBlock().getStateFromMeta(bMeta), 1);
                        return SIDE_OPPOSITE[bMeta];
                    }
                }
                return SIDE_RIGHT[bMeta];
            case RotationType.LEVER:
                int shift = 0;
                if (bMeta > 7) {
                    bMeta -= 8;
                    shift = 8;
                }
                if (bMeta == 5) {
                    return 6 + shift;
                } else if (bMeta == 6) {
                    return 5 + shift;
                } else if (bMeta == 7) {
                    return 0 + shift;
                } else if (bMeta == 0) {
                    return 7 + shift;
                }
            case RotationType.SIGN:
                return ++bMeta % 16;
            case RotationType.PREVENT:
            default:
                return bMeta;
        }
    }

    public static List<ItemStack> breakBlock(World worldObj, BlockPos blockPos, int fortune, boolean doBreak, boolean silkTouch) {

        return breakBlock(worldObj, null, blockPos, fortune, doBreak, silkTouch);
    }

    public static List<ItemStack> breakBlock(World worldObj, EntityPlayer player, BlockPos blockPos, int fortune, boolean doBreak, boolean silkTouch)
    {
        Block block = worldObj.getBlockState(blockPos).getBlock();

        if (block.getBlockHardness(worldObj.getBlockState(blockPos), worldObj, blockPos) == -1)
        {
            return new LinkedList<ItemStack>();
        }
        int meta = block.getMetaFromState(worldObj.getBlockState(blockPos));

        List<ItemStack> stacks = null;

        if (silkTouch && worldObj.getBlockState(blockPos).getBlock().canSilkHarvest(worldObj, blockPos, worldObj.getBlockState(blockPos), player))
        {
            stacks = new LinkedList<ItemStack>();
            stacks.add(createStackedBlock(block, meta));
        } else
        {
            stacks = block.getDrops(worldObj, blockPos, worldObj.getBlockState(blockPos), fortune);
        }
        if (!doBreak) {
            return stacks;
        }
        //worldObj.playAuxSFXAtEntity(player, 2001, blockPos, Block.getIdFromBlock(block) + (meta << 12));
        worldObj.setBlockToAir(blockPos);

        List<EntityItem> result = worldObj.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(blockPos.getX() - 2, blockPos.getY() - 2, blockPos.getZ() - 2, blockPos.getX() + 3, blockPos.getY() + 3, blockPos.getZ() + 3));
        for (int i = 0; i < result.size(); i++)
        {
            EntityItem entity = result.get(i);
            if (entity.isDead || entity.getEntityItem().getCount() <= 0)
            {
                continue;
            }
            stacks.add(entity.getEntityItem());
            entity.world.removeEntity(entity);
        }
        return stacks;
    }

    public static ItemStack createStackedBlock(Block block, int bMeta) {

        Item item = Item.getItemFromBlock(block);
        if (item.getHasSubtypes()) {
            return new ItemStack(item, 1, bMeta);
        }
        return new ItemStack(item, 1, 0);
    }
}
