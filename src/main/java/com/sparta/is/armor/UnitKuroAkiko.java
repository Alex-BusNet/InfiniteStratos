package com.sparta.is.armor;

import com.sparta.is.core.armor.ArmorIS;
import com.sparta.is.core.network.Network;
import com.sparta.is.core.network.message.MessageUnitSettings;
import com.sparta.is.core.reference.EnumUnitState;
import com.sparta.is.core.reference.Key;
import com.sparta.is.core.reference.Materials;
import com.sparta.is.core.reference.Names;
import com.sparta.is.core.settings.UnitSettings;
import com.sparta.is.core.utils.helpers.EntityHelper;
import com.sparta.is.core.utils.helpers.StringHelper;
import com.sparta.is.core.utils.interfaces.IKeyBound;
import com.sparta.is.core.utils.interfaces.IOwnable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

import java.util.List;

public class UnitKuroAkiko extends ArmorIS implements ISpecialArmor, IKeyBound, IOwnable
{
    public static final ISpecialArmor.ArmorProperties SWORD = new ISpecialArmor.ArmorProperties(0, 0.2D, Integer.MAX_VALUE);
    private static final String[] VARIANTS = {"normal"/*, "standby", "partial_deploy", "full_deploy*/};

    protected String ownerName;
    private int equalizers = 5;
    private static EnumUnitState state = EnumUnitState.STANDBY_STATE; // 0 = Standby, 1 = Partial deploy, 2 = Full deploy, 3 = Second Shift

    public int maxEnergy = 50000;
    public int maxTransfer = 200;
    public double absorbRatio = 0.9D;
    public int energyPerDamage = 100;

    private String[] textures = {"is:textures/items/kuroakiko.png", "is:textures/models/kuroakiko_0.png", "is:textures/models/kuroakiko_1.png"};

    public UnitKuroAkiko()
    {
        super(Names.Units.KURO_AKIKO, Materials.Armor.IS_ARMOR, EntityEquipmentSlot.CHEST, 5, VARIANTS);
        this.setShieldCapacity(50000);
        this.setRemainingShieldCapacity();
        setArmorTextures(textures);
    }

    @Override
    public EnumUnitState getState()
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
    public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity)
    {
        return true; //armorType == EntityEquipmentSlot.CHEST;
    }

    @Override
    public void doKeyBindingAction(EntityPlayer entityPlayer, Key key, boolean falling)
    {
        if(key != Key.UNKNOWN)
        {
            NBTTagCompound playerCustomData = EntityHelper.getCustomEntityData(entityPlayer);
            UnitSettings unitSettings = new UnitSettings();
            unitSettings.readFromNBT(playerCustomData);

            if(key == Key.STANDBY && (state != EnumUnitState.STANDBY_STATE))
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

                state = EnumUnitState.STANDBY_STATE;
                unitSettings.setDeployedState(0);
                entityPlayer.sendMessage(new TextComponentTranslation("Unit in Standby"));
            }
            else if (key == Key.PARTIAL_DEPLOY  && (state != EnumUnitState.PARTIAL_DEPLOY_STATE))
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

                state = EnumUnitState.PARTIAL_DEPLOY_STATE;
                unitSettings.setDeployedState(1);
                entityPlayer.sendMessage(new TextComponentTranslation("Unit Partially Deployed"));
            }
            else if (key == Key.FULL_DEPLOY && (state != EnumUnitState.FULL_DEPLOY_STATE))
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

                state = EnumUnitState.FULL_DEPLOY_STATE;
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
    public void addInformation(ItemStack stack, World worldIn, List<String> toolTip, ITooltipFlag ttflag)
    {
        toolTip.add(StringHelper.RED + "Black Crystallite" + StringHelper.END);
        toolTip.add(StringHelper.ORANGE + getRemainingShieldCapacity() + " / " + getShieldCapacity() + " " + StringHelper.localize("info.is.tool.energyPerUse") + StringHelper.END);

        switch ( state )
        {
            case STANDBY_STATE:
                toolTip.add(StringHelper.LIGHT_BLUE + "Currently in Stand by" + StringHelper.END);
                break;
            case PARTIAL_DEPLOY_STATE:
                toolTip.add(StringHelper.LIGHT_BLUE + "Currently Partially Deployed" + StringHelper.END);
                break;
            case FULL_DEPLOY_STATE:
                toolTip.add(StringHelper.LIGHT_BLUE + "Currently Fully Deployed" + StringHelper.END);
                break;
            default:
                break;
        }
    }
}
