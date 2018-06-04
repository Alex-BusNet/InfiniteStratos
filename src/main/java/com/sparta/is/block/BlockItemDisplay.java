package com.sparta.is.block;

import cofh.core.render.IModelRegister;
import cofh.core.util.core.IInitializer;
import com.sparta.is.core.InfiniteStratos;
import com.sparta.is.core.block.BlockISContainerBase;
import com.sparta.is.core.reference.Names;
import com.sparta.is.core.reference.Reference;
import com.sparta.is.core.utils.ItemStackUtils;
import com.sparta.is.core.utils.ItemUtils;
import com.sparta.is.init.ModBlocks;
import com.sparta.is.item.ItemDisplayStand;
import com.sparta.is.tileentity.TileEntityItemDisplay;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BlockItemDisplay extends BlockISContainerBase implements IInitializer, IModelRegister
{
    private static AxisAlignedBB BOTTOM_HALF_BB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);

    public BlockItemDisplay()
    {
        super(Material.ROCK, Names.Blocks.ITEM_DISPLAY_STAND);

        this.setUnlocalizedName(Names.Blocks.ITEM_DISPLAY_STAND);
        this.setHarvestLevel("pickaxe", 0);
        this.setHardness(1.5f);
        this.setResistance(10.0f);
        this.setSoundType(SoundType.STONE);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityItemDisplay();
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BOTTOM_HALF_BB;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing par6, float par7, float par8, float par9)
    {
        ItemStack heldItem = player.getHeldItem(hand);
        if(!world.isRemote)
        {
            TileEntityItemDisplay stand = (TileEntityItemDisplay)world.getTileEntity(pos);
            if(stand != null)
            {
                ItemStack display = stand.slots.getStackInSlot(0);
                if(ItemStackUtils.isValid(heldItem))
                {
                    if(!ItemStackUtils.isValid(display))
                    {
                        ItemStack toPut = heldItem.copy();
                        toPut = ItemStackUtils.setStackSize(toPut, 1);
                        stand.slots.setStackInSlot(0, toPut);
                        player.setHeldItem(hand, ItemStackUtils.addStackSize(heldItem, -1));
                        return true;
                    }
                    else if( ItemUtils.canBeStacked(heldItem, display))
                    {
                        int maxTransfer = Math.min(ItemStackUtils.getStackSize(display), heldItem.getMaxStackSize()-ItemStackUtils.getStackSize(heldItem));
                        if(maxTransfer > 0)
                        {
                            player.setHeldItem(hand, ItemStackUtils.addStackSize(heldItem, maxTransfer));
                            ItemStack newDisplay = display.copy();
                            newDisplay = ItemStackUtils.addStackSize(newDisplay, -maxTransfer);
                            stand.slots.setStackInSlot(0, ItemStackUtils.validateCheck(newDisplay));
                            return true;
                        }
                    }
                }
                else
                {
                    if( ItemStackUtils.isValid(display))
                    {
                        player.setHeldItem(hand, display.copy());
                        stand.slots.setStackInSlot(0, ItemStackUtils.getEmpty());
                        return true;
                    }
                }
            }
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face)
    {
        if(face == EnumFacing.DOWN) return BlockFaceShape.SOLID;
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return EnumRarity.RARE;
    }

    @Override
    public boolean initialize()
    {
        return false;
    }

    @Override
    public boolean register()
    {
        this.setRegistryName(Names.Blocks.ITEM_DISPLAY_STAND);
        ModBlocks.registerTileEntity(this);

        ItemDisplayStand itemDisplayStand = new ItemDisplayStand(this);
        itemDisplayStand.setRegistryName(this.getRegistryName());
        ForgeRegistries.ITEMS.register(itemDisplayStand);

        itemDisplay = new ItemStack(this, 1, 0);

        InfiniteStratos.proxy.getClientProxy().addIModel(this);

        return true;
    }

    @Override
    public void registerModels()
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Names.Blocks.ITEM_DISPLAY_STAND, "inventory"));
    }

    private static ItemStack itemDisplay;
}
