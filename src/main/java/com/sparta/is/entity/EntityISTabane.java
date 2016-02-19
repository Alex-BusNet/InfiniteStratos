package com.sparta.is.entity;

import com.sparta.is.item.ItemISCore;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.List;
import java.util.Random;

public class EntityISTabane extends EntityIS implements VillagerRegistry.IVillageCreationHandler
{
    public EntityISTabane(World world)
    {
        super(world);
        this.setProfession(5);
        this.setCustomNameTag("Tabane Shinonono");
        this.setAlwaysRenderNameTag(true);
//        this.setPosition(5, 65, 80);
    }

    @Override
    public void entityInit()
    {
        super.entityInit();
//        dataWatcher.addObject(29, new Integer(0));
    }

    public boolean isAIEnabled()
    {
        return true;
    }

    @Override
    public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
    {
        recipeList.add(0, ItemISCore.class);
        villager.setRecipes(recipeList);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData iEntityLivingData)
    {
        return super.onSpawnWithEgg(iEntityLivingData);
    }

    @Override
    public void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3F);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readEntityFromNBT(nbtTagCompound);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeEntityToNBT(nbtTagCompound);
    }

    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    @Override
    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i)
    {
        return null;
    }

    @Override
    public Class<?> getComponentClass()
    {
        return null;
    }

    @Override
    public StructureVillagePieces.Village buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List<StructureComponent> pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5)
    {
        return null;
    }
}
