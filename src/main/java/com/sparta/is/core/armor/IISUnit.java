package com.sparta.is.core.armor;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public interface IISUnit
{
    List<String> getExtraInfo(ItemStack unit, NBTTagCompound unitTag);

    boolean isHidden();

    String getTooltip(NBTTagCompound modifierTag, boolean detailed);

    void updateNBT(NBTTagCompound modifierTag);

    /** Used for specific modifiers that need a texture variant for each material */
    @SideOnly(Side.CLIENT)
    boolean hasTexturePerMaterial();

    boolean equalModifier(NBTTagCompound modifierTag1, NBTTagCompound modifierTag2);

    default boolean hasItemsToApplyWith()
    {
        return true;
    }
}
