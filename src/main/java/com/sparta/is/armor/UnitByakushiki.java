package com.sparta.is.armor;

import com.sparta.is.InfiniteStratos;
import com.sparta.is.network.Network;
import com.sparta.is.network.message.MessageUnitSettings;
import com.sparta.is.reference.EnumUnitState;
import com.sparta.is.reference.Key;
import com.sparta.is.reference.Materials;
import com.sparta.is.reference.Names;
import com.sparta.is.settings.UnitSettings;
import com.sparta.is.utils.*;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.ItemMeshDefinition;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class UnitByakushiki extends ArmorIS implements ISpecialArmor, IKeyBound, IOwnable
{
    public static final ArmorProperties SWORD = new ArmorProperties(0, 0.2D, Integer.MAX_VALUE);
    private static final String[] VARIANTS = {"normal"/*, "standby", "partial_deploy", "full_deploy, "setsura"*/};

    protected String ownerName;
    private int equalizers = 4;
    private static EnumUnitState state = EnumUnitState.STANDBY_STATE; // 0 = Standby, 1 = Partial deploy, 2 = Full deploy, 3 = Second Shift

    public int maxEnergy = 20000;
    public int maxTransfer = 200;
    public double absorbRatio = 0.9D;
    public int energyPerDamage = 100;

    private String[] textures = {"is:textures/models/byakushiki_0.png", "is:textures/models/byakushiki_1.png", "is:textures/armor/byakushiki_armor.png"};

    public UnitByakushiki()
    {
        super(Names.Units.BYAKUSHIKI, Materials.Armor.IS_ARMOR, EntityEquipmentSlot.CHEST, 4, VARIANTS);
        this.setShieldCapacity(20000);
        this.setRemainingShieldCapacity();
        setArmorTextures(textures);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped defaultModel)
    {
        if(itemStack != null)
        {
            if(itemStack.getItem() instanceof UnitByakushiki)
            {
                if(state != EnumUnitState.STANDBY_STATE)
                {
                    ModelBiped armorModel = InfiniteStratos.proxy.getClientProxy().getArmorModel(this);

                    if(state == EnumUnitState.FULL_DEPLOY_STATE)
                    {
                        armorModel.bipedHead.showModel = true;
                        armorModel.bipedBody.showModel = true;
                        armorModel.bipedLeftArm.showModel = true;
                        armorModel.bipedLeftLeg.showModel = true;
                        armorModel.bipedRightLeg.showModel = true;
                    }

                    armorModel.bipedRightArm.showModel = true;

                    armorModel.setModelAttributes(defaultModel);

                    return armorModel;
                }

                return null;
            }
        }

        return null;
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
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
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
    public String getArmorTexture(ItemStack itemStack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        if(state == EnumUnitState.STANDBY_STATE)
            return textures[0];
        else
            return textures[2];
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
                    if(!entityPlayer.capabilities.isCreativeMode)
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
                    if(!entityPlayer.capabilities.isCreativeMode)
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
    public ItemMeshDefinition getCustomMeshDefinition()
    {
        return itemStack -> ResourceLocationHelper.getModelResourceLocation(Names.Units.BYAKUSHIKI, VARIANTS[0]);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> toolTip, ITooltipFlag ttflag)
    {
        toolTip.add(StringHelper.RED + "Type \"White\"" + StringHelper.END);
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
