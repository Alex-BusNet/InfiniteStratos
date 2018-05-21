package com.sparta.is.armor;

import com.sparta.is.network.Network;
import com.sparta.is.network.message.MessageUnitSettings;
import com.sparta.is.reference.Key;
import com.sparta.is.reference.Materials;
import com.sparta.is.reference.Names;
import com.sparta.is.settings.UnitSettings;
import com.sparta.is.utils.*;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.ISpecialArmor;

import java.util.List;

public class UnitKuroAkiko extends ArmorIS implements ISpecialArmor, IKeyBound, IOwnable
{
    public static final ISpecialArmor.ArmorProperties SWORD = new ISpecialArmor.ArmorProperties(0, 0.2D, Integer.MAX_VALUE);
    private static final String[] VARIANTS = {"kuroakiko"/*"kuroakiko_standby", "kuroakiko_partial_deploy", "kuroakiko_full_deploy*/};

    protected String ownerName;
    private int equalizers = 5;
    private static int state = 0; // 0 = Standby, 1 = Partial deploy, 2 = Full deploy, 3 = Second Shift

    public int maxEnergy = 50000;
    public int maxTransfer = 200;
    public double absorbRatio = 0.9D;
    public int energyPerDamage = 100;

    private String[] textures = {"is:textures/items/kuroakiko.png", "is:textures/models/kuroakiko_1.png"};

    public UnitKuroAkiko()
    {
        super(Names.Units.KURO_AKIKO, Materials.Armor.IS_ARMOR, EntityEquipmentSlot.CHEST, 5, VARIANTS);
        this.setShieldCapacity(50000);
        this.setRemainingShieldCapacity();
        setArmorTextures(textures);
    }

    public int getState()
    {
        return state;
    }

    @Override
    public String[] getVariants()
    {
        return VARIANTS;
    }

    @Override
    public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
    {
        return SWORD;
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
    {
        return 0;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
    {

    }

    @Override
    public void doKeyBindingAction(EntityPlayer entityPlayer, Key key, boolean falling)
    {
        if(key != Key.UNKNOWN)
        {
            NBTTagCompound playerCustomData = EntityHelper.getCustomEntityData(entityPlayer);
            UnitSettings unitSettings = new UnitSettings();
            unitSettings.readFromNBT(playerCustomData);

            if(key == Key.STANDBY && (state == 1 || state == 2))
            {
                if(!entityPlayer.getEntityWorld().isRemote)
                {
                    entityPlayer.capabilities.allowFlying = false;

                    if(entityPlayer.capabilities.isFlying && !entityPlayer.capabilities.isCreativeMode)
                    {
                        entityPlayer.capabilities.isFlying = false;
                    }

                    entityPlayer.capabilities.setFlySpeed(0.05F);
                    entityPlayer.capabilities.setPlayerWalkSpeed(0.1F);

                    entityPlayer.sendPlayerAbilities();
                }

                state = 0;
                unitSettings.setDeployedState(0);
                entityPlayer.sendMessage(new TextComponentTranslation("Unit in Standby"));
            }
            else if (key == Key.PARTIAL_DEPLOY  && state != 1 && state != 2)
            {
                if(!entityPlayer.getEntityWorld().isRemote)
                {
                    entityPlayer.capabilities.allowFlying = false;

                    if ( entityPlayer.capabilities.isFlying && ! entityPlayer.capabilities.isCreativeMode )
                    {
                        entityPlayer.capabilities.isFlying = false;
                    }

                    entityPlayer.capabilities.setFlySpeed(0.05F);
                    entityPlayer.capabilities.setPlayerWalkSpeed(0.1F);

                    entityPlayer.sendPlayerAbilities();
                }

                state = 1;
                unitSettings.setDeployedState(1);
                entityPlayer.sendMessage(new TextComponentTranslation("Unit Partially Deployed"));
            }
            else if (key == Key.FULL_DEPLOY && state != 2)
            {
                if(!entityPlayer.getEntityWorld().isRemote)
                {
                    entityPlayer.capabilities.allowFlying = true;
                    entityPlayer.capabilities.setFlySpeed(0.1F);
                    entityPlayer.capabilities.setPlayerWalkSpeed(0.3F);

                    if ( falling )
                    {
                        entityPlayer.capabilities.isFlying = true;
                    }
                    else
                    {
                        entityPlayer.capabilities.isFlying = false;
                    }

                    entityPlayer.sendPlayerAbilities();
                }

                state = 2;
                unitSettings.setDeployedState(2);
                entityPlayer.sendMessage(new TextComponentTranslation("Unit Fully Deployed"));
            }

            unitSettings.writeToNBT(playerCustomData);
            EntityHelper.saveCustomEntityData(entityPlayer, playerCustomData);

            if(entityPlayer.getEntityWorld().isRemote)
            {
                Network.INSTANCE.sendTo(new MessageUnitSettings(unitSettings), (EntityPlayerMP) entityPlayer);
            }
        }
    }

    @Override
    public ItemMeshDefinition getCustomMeshDefinition()
    {
        return itemStack -> ResourceLocationHelper.getModelResourceLocation(VARIANTS[0]);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check)
    {
        list.add(StringHelper.RED + "Black Crystallite" + StringHelper.END);
        list.add(StringHelper.ORANGE + getRemainingShieldCapacity() + " / " + getShieldCapacity() + " " + StringHelper.localize("info.is.tool.energyPerUse") + StringHelper.END);

        switch ( state )
        {
            case 0:
                list.add(StringHelper.LIGHT_BLUE + "Currently in Stand by" + StringHelper.END);
                break;
            case 1:
                list.add(StringHelper.LIGHT_BLUE + "Currently Partially Deployed" + StringHelper.END);
                break;
            case 2:
                list.add(StringHelper.LIGHT_BLUE + "Currently Fully Deployed" + StringHelper.END);
                break;
            default:
                break;
        }
    }
}
