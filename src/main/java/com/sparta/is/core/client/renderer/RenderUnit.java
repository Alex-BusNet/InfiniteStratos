package com.sparta.is.core.client.renderer;

import com.sparta.is.core.armor.ArmorIS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class RenderUnit extends LayerBipedArmor
{
    public RenderUnit(RenderLivingBase<?> renderIn)
    {
        super(renderIn);
    }

    @Override
    public void initArmor() {}

    @Override
    protected void setModelSlotVisible(ModelBiped p_188359_1_, EntityEquipmentSlot slotIn)
    {
        if((slotIn != EntityEquipmentSlot.MAINHAND) && (slotIn != EntityEquipmentSlot.OFFHAND))
        {
            for( ItemStack itemStack: Minecraft.getMinecraft().player.getArmorInventoryList())
            {
                if(itemStack.getItem() instanceof ArmorIS )
                {
                    if ( itemStack.getItem().isValidArmor(itemStack, slotIn, Minecraft.getMinecraft().player) )
                    {
                        p_188359_1_.bipedBody.showModel = true;
                        p_188359_1_.bipedHead.showModel = true;
                        p_188359_1_.bipedRightLeg.showModel = true;
                        p_188359_1_.bipedLeftLeg.showModel = true;
                        p_188359_1_.bipedRightArm.showModel = true;
                        p_188359_1_.bipedLeftArm.showModel = true;
                    }
                }
            }

        }
    }
}
