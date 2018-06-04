package com.sparta.is.fluid;

import cofh.core.util.helpers.ServerHelper;
import com.sparta.is.core.fluid.BlockFluidIS;
import com.sparta.is.core.reference.Materials;
import com.sparta.is.core.utils.helpers.LogHelper;
import com.sparta.is.init.ModFluids;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Random;

public class BlockFluidCrystalline extends BlockFluidIS
{
    public static final int LEVELS = 6;
    private static boolean effect = true;
    private static boolean enableSourceDissipate = true;
    private static boolean enableSourceFloat = true;
    private static int maxHeight = 120;

    public BlockFluidCrystalline(Fluid fluid)
    {
        super(fluid, Materials.crystallineFluidMaterial, "is", "crystalline");
        setQuantaPerBlock(LEVELS);
        setTickRate(8);

        setHardness(1.0F);
        setLightOpacity(0);
        setParticleColor(0.65f, 0.65f, 0.65f);
    }

    private boolean shouldSourceBlockDissipate(World world, BlockPos pos)
    {

        int y = pos.getY();
        return enableSourceDissipate && (y + densityDir > maxHeight || y + densityDir > world.getHeight() || y + densityDir > maxHeight * 0.8F && ! canDisplace(world, pos.add(0, densityDir, 0)));
    }

    private boolean shouldSourceBlockFloat(World world, BlockPos pos)
    {

        IBlockState state = world.getBlockState(pos.add(0, densityDir, 0));
        return enableSourceFloat && state.getBlock() == this && getMetaFromState(state) != 0;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {

        if ( ! effect )
        {
            return;
        }
        if ( entity instanceof EntityLivingBase )
        {
            if ( entity.motionX > 0.1 )
            {
                entity.motionX = 0.1;
            }
            if ( entity.motionZ > 0.1 )
            {
                entity.motionZ = 0.1;
            }
            if ( entity.motionY < - 0.2 )
            {
                entity.motionY *= 0.5;
                if ( entity.fallDistance > 20 )
                {
                    entity.fallDistance = 20;
                }
                else
                {
                    entity.fallDistance *= 0.5;
                }
            }
        }
        else if ( entity instanceof IProjectile )
        {
            entity.motionX *= world.rand.nextGaussian() * 1.5;
            entity.motionZ *= world.rand.nextGaussian() * 1.5;
        }
        if ( ServerHelper.isClientWorld(world) )
        {
            return;
        }
        if ( world.getTotalWorldTime() % 8 == 0 && entity instanceof EntityLivingBase && ! ((EntityLivingBase) entity).isEntityUndead() )
        {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 6 * 20, 0));
        }
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
    {

        return ModFluids.fluidCrystalline.getLuminosity();
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {

        if ( getMetaFromState(state) == 0 )
        {
            if ( shouldSourceBlockDissipate(world, pos) )
            {
                world.setBlockToAir(pos);
                return;
            }
            if ( rand.nextInt(3) == 0 )
            {
                if ( shouldSourceBlockFloat(world, pos) )
                {
                    world.setBlockState(pos.add(0, densityDir, 0), this.getDefaultState(), 3);
                    world.setBlockToAir(pos);
                    return;
                }
            }
        }
        else if ( pos.getY() > maxHeight )
        {
            world.setBlockToAir(pos);
            return;
        }
        super.updateTick(world, pos, state, rand);
    }

    @Override
    protected void flowIntoBlock(World world, BlockPos pos, int meta)
    {

        if ( pos.getY() > maxHeight )
        {
            return;
        }
        super.flowIntoBlock(world, pos, meta);
    }

    /* IInitializer */
    @Override
    public boolean register()
    {
        LogHelper.info("Registering Crystalline fluid...");
        this.setRegistryName("fluid_crystalline");
        ForgeRegistries.BLOCKS.register(this);
        ItemBlock itemBlock = new ItemBlock(this);
        itemBlock.setRegistryName(this.getRegistryName());
        ForgeRegistries.ITEMS.register(itemBlock);

//        config();

        return true;
    }
}