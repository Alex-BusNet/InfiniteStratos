package com.sparta.is.entity.driveables.types;

import com.sparta.is.armor.models.ModelAttachment;
import com.sparta.is.item.ItemAttachment;
import com.sparta.is.core.reference.EnumAttachmentType;
import com.sparta.is.core.reference.EnumFireMode;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

public class AttachmentType extends InfoType implements IScope
{
    public static ArrayList<AttachmentType> attachments = new ArrayList<AttachmentType>();
    public EnumAttachmentType type = EnumAttachmentType.GENERIC;
    public boolean silencer = false;
    public boolean flashlight = false;
    public float flashlightRange = 10f;
    public int flashlightStrength = 12;

    public float spreadMultiplier = 1f;
    public float recoilMultiplier = 1f;
    public float damageMultiplier = 1f;
    public float bulletSpeedMultiplier = 1f;
    public float reloadTimeMultiplier = 1f;
    public EnumFireMode modeOverride = null;

    public float zoomLevel = 1f;
    public float FOVZoomLevel = 1f;
    public String zoomOverlay;
    public boolean hasScopeOverlay = false;

    @SideOnly(Side.CLIENT)
    public ModelAttachment model;
    public float modelScale = 1f;

    public int maxStackSize = 1;

    public AttachmentType(TypeFile file)
    {
        super(file);
        attachments.add(this);
    }

    public static AttachmentType getFromNBT(NBTTagCompound tags)
    {
        ItemStack stack = new ItemStack(tags);
        if(stack != null && stack.getItem() instanceof ItemAttachment )
            return ((ItemAttachment)stack.getItem()).type;
        return null;
    }

    @Override
    public float getZoomFactor()
    {
        return zoomLevel;
    }

    @Override
    public boolean hasZoomOverlay()
    {
        return hasScopeOverlay;
    }

    @Override
    public String getZoomOverlay()
    {
        return zoomOverlay;
    }

    @Override
    public float getFOVFactor()
    {
        return FOVZoomLevel;
    }

    public static AttachmentType getAttachment(String s)
    {
        for(AttachmentType attachment : attachments)
        {
            if(attachment.shortName.equals(s))
                return attachment;
        }
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBase getModel()
    {
        return model;
    }

}
