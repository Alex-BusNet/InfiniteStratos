package com.sparta.is.item.base;

import com.sparta.is.creativetab.CreativeTab;
import com.sparta.is.reference.Names;
import com.sparta.is.utils.IOwnable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemYukihiraNigata extends ItemISMelee implements IOwnable
{
    public ItemYukihiraNigata()
    {
        super();
        this.setUnlocalizedName(Names.Weapons.YUKIHIRA_NIGATA);
        this.setCreativeTab(CreativeTab.IS_TAB);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState block, BlockPos pos, EntityLivingBase entityLiving)
    {
        return true;
    }

}
