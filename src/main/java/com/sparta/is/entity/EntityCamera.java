package com.sparta.is.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.world.World;
import org.lwjgl.util.vector.Vector3f;

public class EntityCamera extends EntityLivingBase
{
    public EntityUnitController controller;

    public EntityCamera(World world)
    {
        super(world);
        setSize(0f, 0f);
    }

    @Override
    public Iterable<ItemStack> getArmorInventoryList()
    {
        return null;
    }

    @Override
    public ItemStack getItemStackFromSlot(EntityEquipmentSlot slotIn)
    {
        return null;
    }

    @Override
    public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack)
    {

    }

    public EntityCamera(World world, EntityUnitController uc)
    {
        this(world);
        controller = uc;
        setPosition(uc.posX, uc.posY, uc.posZ);
    }

    @Override
    public void onUpdate()
    {
        //super.onUpdate();

        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;

        Vector3f cameraPosition = new Vector3f();//-1F, 0.5F, 0F);
        //cameraPosition.scale(driveable.getDriveableType().cameraDistance);
        cameraPosition = controller.axes.findLocalVectorGlobally(cameraPosition);

        //Lerp it
        double dX = controller.posX + cameraPosition.x - posX;
        double dY = controller.posY + cameraPosition.y - posY;
        double dZ = controller.posZ + cameraPosition.z - posZ;

        float lerpAmount = 0.1F;

        setPosition(posX + dX * lerpAmount, posY + dY * lerpAmount, posZ + dZ * lerpAmount);

        rotationYaw = controller.axes.getYaw() - 90F;
        rotationPitch = controller.axes.getPitch();

        for(; rotationYaw - prevRotationYaw >= 180F; rotationYaw -= 360F) ;
        for(; rotationYaw - prevRotationYaw < -180F; rotationYaw += 360F) ;
    }

    @Override
    public EnumHandSide getPrimaryHand()
    {
        return null;
    }

}
