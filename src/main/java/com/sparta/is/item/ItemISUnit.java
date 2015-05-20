package com.sparta.is.item;

import com.sparta.is.client.settings.KeyBindings;
import com.sparta.is.creativetab.CreativeTabIS;
import com.sparta.is.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemISUnit extends Item
{
    public ItemISUnit()
    {
        super();
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabIS.IS_TAB);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedLocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedLocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    protected String getUnwrappedLocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    public int setDeployedState;

    public int DeploymentMode(KeyBinding keyPressed)
    {
        if(keyPressed == KeyBindings.standbyKey)
        {
            if(setDeployedState != 0)
            {
                setDeployedState = 0;
            }
            else
            {
                return setDeployedState;
            }
        }
        else if(keyPressed == KeyBindings.partialDeployKey)
        {
            if (setDeployedState != 1)
            {
                setDeployedState = 1;
            }
            else
            {
                return setDeployedState;
            }
        }
        else if(keyPressed ==KeyBindings.fullDeployKey)
        {
            if(setDeployedState != 2)
            {
                setDeployedState = 2;
            }
            else
            {
                return setDeployedState;
            }
        }
        else
        {
            return keyPressed.getKeyCode();
        }

        return setDeployedState;
    }

}
