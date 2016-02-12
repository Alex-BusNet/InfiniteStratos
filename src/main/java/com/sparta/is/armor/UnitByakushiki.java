package com.sparta.is.armor;

import com.sparta.is.InfiniteStratos;
import com.sparta.is.network.PacketHandler;
import com.sparta.is.network.message.MessageUnitSettings;
import com.sparta.is.reference.*;
import com.sparta.is.settings.UnitSettings;
import com.sparta.is.utility.*;
import com.sparta.repackage.cofh.api.energy.IEnergyContainerItem;
import com.sparta.repackage.cofh.lib.util.helpers.EnergyHelper;
import com.sparta.repackage.cofh.lib.util.helpers.MathHelper;
import com.sparta.repackage.cofh.lib.util.helpers.StringHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.UsernameCache;

import java.util.List;
import java.util.UUID;

public class UnitByakushiki extends ArmorIS implements IKeyBound, IOwnable, IEnergyContainerItem, ISpecialArmor
{
    public static final ArmorProperties SWORD = new ArmorProperties(0, 0.2D, Integer.MAX_VALUE);

    protected UUID ownerUUID = getOwnerUUID();
    protected String ownerName;
    private static int state = 0;
    private final int TOTAL_EQUALIZERS = 4;

    public int maxEnergy = 20000;
    public int maxTransfer = 200;
    public double absorbRatio = 0.9D;
    public int energyPerDamage = 100;

    public UnitByakushiki()
    {
        super(Material.Armor.IS_ARMOR, 1, "Armor");
        this.setUnlocalizedName(Names.Units.BYAKUSHIKI);
        this.setShieldCapacity(20000);
        this.setRemainingShieldCapacity();
        initState();
    }

    public UnitByakushiki setEnergyParams(int maxEnergy, int maxTransfer )
    {
        this.maxEnergy = maxEnergy;
        this.maxTransfer = maxTransfer;
        return this;
    }

    @Override
    public void setDamage(ItemStack itemStack, int damage)
    {
        super.setDamage(itemStack, 0);
    }

    @Override
    public int getMaxDamage(ItemStack stack)
    {
        return maxEnergy;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
        list.add(EnergyHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), maxEnergy));
    }

    protected int getAbsorptionRatio()
    {
        switch ( armorType )
        {
            case 0:
                return 0;
            case 1:
                return 100;
            case 2:
                return 0;
            case 3:
                return 0;
        }
        return 0;
    }

    protected int getEnergyPerDamage(ItemStack itemStack)
    {
        int unbreakingLevel = MathHelper.clampI(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, itemStack), 0, 4);
        return energyPerDamage * (5 - unbreakingLevel) /5;
    }

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, int slot, String type)
    {
        return Textures.Armor.BYAKUSHIKI.toString();
    }

    public UUID getOwnerUUID()
    {
        return ownerUUID;
    }

    public String getOwnerName()
    {
        if (ownerUUID != null)
        {
            return UsernameCache.getLastKnownUsername(ownerUUID);
        }

        return "Unknown";
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLivingBase, ItemStack itemStack, int armorSlot)
    {
        ModelBiped armorModel = null;

        if(itemStack != null)
        {
            if ( itemStack.getItem() instanceof UnitByakushiki )
            {
                int type = ((ItemArmor) itemStack.getItem()).armorType;

                if ( type == 1 || type == 3 )
                {
                    armorModel = InfiniteStratos.proxy.getArmorModel(0);
                }
                else
                {
                    armorModel = InfiniteStratos.proxy.getArmorModel(1);
                }
            }

            if ( armorModel != null )
            {
                armorModel.bipedHead.showModel = state == 2;
                armorModel.bipedHeadwear.showModel = state == 2;
                armorModel.bipedBody.showModel = state == 2;
                armorModel.bipedLeftArm.showModel = state == 2;
                armorModel.bipedHead.showModel = state == 2;
                armorModel.bipedLeftLeg.showModel = state == 2;
                armorModel.bipedRightLeg.showModel = state == 2;
                armorModel.bipedRightArm.showModel = (state == 1 || state == 2);

                armorModel.isSneak = entityLivingBase.isSneaking();
                armorModel.isRiding = entityLivingBase.isRiding();
                armorModel.isChild = entityLivingBase.isChild();
                armorModel.heldItemRight = entityLivingBase.getEquipmentInSlot(0) != null ? 1 : 0;

                EntityPlayer player = (EntityPlayer) entityLivingBase;
                ItemStack held_item = player.getHeldItem();

                if ( held_item != null )
                {
                    armorModel.heldItemRight = 1;

                    if ( player.getItemInUseCount() > 0 )
                    {
                        EnumAction action = held_item.getItemUseAction();

                        if ( action == EnumAction.bow )
                        {
                            armorModel.aimedBow = true;
                        }
                        else
                        {
                            armorModel.heldItemRight = 3;
                        }
                    }

                }

                return armorModel;
            }
        }
        return armorModel;
    }


    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
    {
//        super.onArmorTick(world, player, itemStack);

        //TODO: Create method to handle auto-deployment for player falling
        if(state != 2 && player.motionY < -1.0D && getRemainingShieldCapacity() > 0 && !player.capabilities.isFlying && !player.capabilities.isCreativeMode)
        {
            LogHelper.info("Before: " + player.motionY);
            player.motionY = 0.0D;
            LogHelper.info("After: " + player.motionY);
            doKeyBindingAction(player, itemStack, Key.FULL_DEPLOY, true);
        }


    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        if(!world.isRemote)
        {
            if(!ItemHelper.hasOwnerUUID(itemStack))
            {
                NBTTagCompound nbtTagCompound = EntityHelper.getCustomEntityData(entityPlayer);
                UnitSettings unitSettings = new UnitSettings();
                unitSettings.readFromNBT(nbtTagCompound);
                unitSettings.setOwnerName(entityPlayer.getDisplayName());
                unitSettings.setUnitName(itemStack.getUnlocalizedName());
                unitSettings.setTotalEqualizers(TOTAL_EQUALIZERS);

                ItemHelper.setOwner(itemStack, entityPlayer);
                this.setOwnerName(entityPlayer.getDisplayName());

                entityPlayer.addChatComponentMessage(new ChatComponentTranslation(Messages.OWNER_SET_TO_SELF, new Object[]{itemStack.func_151000_E()}));

                unitSettings.writeToNBT(nbtTagCompound);
                EntityHelper.saveCustomEntityData(entityPlayer, nbtTagCompound);
                PacketHandler.INSTANCE.sendTo(new MessageUnitSettings(unitSettings), (EntityPlayerMP) entityPlayer);
            }
            else if(ItemHelper.hasOwnerUUID(itemStack))
            {
                if(entityPlayer.getUniqueID() != getOwnerUUID())
                {
                    entityPlayer.addChatComponentMessage(new ChatComponentTranslation(Messages.INVALID_OWNER, ItemHelper.getOwnerName(itemStack)));
                }
                else if(entityPlayer.getUniqueID() == ItemHelper.getOwnerUUID(itemStack))
                {
                    entityPlayer.addChatComponentMessage(new ChatComponentTranslation(Messages.ALREADY_OWNER, itemStack.getUnlocalizedName()));
                }
            }
        }

        return itemStack;
    }

    public int getState()
    {
        return state;
    }


    private void initState()
    {
        NBTTagCompound playerCustomData = EntityHelper.getCustomEntityData(FMLClientHandler.instance().getClientPlayerEntity());
        UnitSettings unitSettings = new UnitSettings();
        unitSettings.readFromNBT(playerCustomData);
        state = unitSettings.getDeployedState();
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check)
    {
        list.add(StringHelper.RED + "Type \"White\"" + StringHelper.END);
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

    /* IOwnable */
    @Override
    public void doKeyBindingAction(EntityPlayer player, ItemStack itemStack, Key key, boolean falling)
    {

        if(key != Key.UNKNOWN)
        {
            NBTTagCompound playerCustomData = EntityHelper.getCustomEntityData(player);
            UnitSettings unitSettings = new UnitSettings();
            unitSettings.readFromNBT(playerCustomData);
            state = unitSettings.getDeployedState();

            if(key == Key.STANDBY && state != 0)
            {
                if(!player.getEntityWorld().isRemote)
                {
                    player.capabilities.allowFlying = false;

                    if(player.capabilities.isFlying && !player.capabilities.isCreativeMode)
                    {
                        player.capabilities.isFlying = false;
                    }

                    player.capabilities.setFlySpeed(0.05F);
                    player.capabilities.setPlayerWalkSpeed(0.1F);

                    player.sendPlayerAbilities();
                }

                state = 0;
                unitSettings.setDeployedState(0);
                player.addChatMessage(new ChatComponentText("Unit in Standby"));
            }
            else if (key == Key.PARTIAL_DEPLOY  && state != 1 && state != 2)
            {
                if(!player.getEntityWorld().isRemote)
                {
                    player.capabilities.allowFlying = false;

                    if ( player.capabilities.isFlying && ! player.capabilities.isCreativeMode )
                    {
                        player.capabilities.isFlying = false;
                    }

                    player.capabilities.setFlySpeed(0.05F);
                    player.capabilities.setPlayerWalkSpeed(0.1F);

                    player.sendPlayerAbilities();
                }

                state = 1;
                unitSettings.setDeployedState(1);
                player.addChatMessage(new ChatComponentText("Unit Partially Deployed"));
            }
            else if (key == Key.FULL_DEPLOY && state != 2)
            {
                if(!player.getEntityWorld().isRemote)
                {
                    player.capabilities.allowFlying = true;
                    player.capabilities.setFlySpeed(0.1F);
                    player.capabilities.setPlayerWalkSpeed(0.3F);

                    if ( falling )
                    {
                        player.capabilities.isFlying = true;
                    }
                    else
                    {
                        player.capabilities.isFlying = false;
                    }

                    player.sendPlayerAbilities();
                }

                state = 2;
                unitSettings.setDeployedState(2);
                player.addChatMessage(new ChatComponentText("Unit Fully Deployed"));
            }

            unitSettings.writeToNBT(playerCustomData);
            EntityHelper.saveCustomEntityData(player, playerCustomData);
            PacketHandler.INSTANCE.sendTo(new MessageUnitSettings(unitSettings), (EntityPlayerMP) player);
        }
    }

    protected int getBaseAbsorption()
    {
        return 20;
    }

    /* ISpecial Armor */
    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
    {
        if (source.damageType.equals("sword"))
        {
            return SWORD;
        }
        else if (source.isUnblockable())
        {
            int absorbMax = getEnergyPerDamage(armor) > 0 ? 25 * getEnergyStored(armor) / getEnergyPerDamage(armor) : 0;
            return new ArmorProperties(0, absorbRatio * getArmorMaterial().getDamageReductionAmount(armorType) * 0.025, absorbMax);
        }
        int absorbMax = getEnergyPerDamage(armor) > 0 ? 25 * getEnergyStored(armor) / getEnergyPerDamage(armor) : 0;
        return new ArmorProperties(0, absorbRatio * getArmorMaterial().getDamageReductionAmount(armorType) * 0.05, absorbMax);
        // 0.05 = 1 / 20 (max armor)
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
    {
        if (getEnergyStored(armor) >= getEnergyPerDamage(armor))
        {
            return Math.min(getBaseAbsorption(), 20) * getAbsorptionRatio() / 100;
        }
        return 0;
    }

    @Override
    public void damageArmor(EntityLivingBase entityLivingBase, ItemStack itemStack, DamageSource source, int damage, int slot)
    {
        if(source.damageType.equals("sword"))
        {
            boolean p = source.getEntity() == null;
            receiveEnergy(itemStack, damage * (p ? energyPerDamage / 2 : getEnergyPerDamage(itemStack)), false);
        }
        else
        {
            extractEnergy(itemStack, damage * getEnergyPerDamage(itemStack), false);
        }
    }

    /* IEnergyContainerItem */
    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate)
    {
        if(container.stackTagCompound == null)
        {
            EnergyHelper.setDefaultEnergyTag(container, 0);
        }
        int stored = container.stackTagCompound.getInteger("Energy");
        int receive = Math.min(maxReceive, Math.min(maxEnergy - stored, maxTransfer));

        if(!simulate)
        {
            stored += receive;
            container.stackTagCompound.setInteger("Energy", stored);
        }

        return receive;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate)
    {
        if (container.stackTagCompound == null)
        {
            EnergyHelper.setDefaultEnergyTag(container, 0);
        }
        int stored = container.stackTagCompound.getInteger("Energy");
        int extract = Math.min(maxExtract, stored);

        if (!simulate)
        {
            stored -= extract;
            container.stackTagCompound.setInteger("Energy", stored);
        }
        return extract;
    }

    @Override
    public int getEnergyStored(ItemStack container)
    {
        if(container.stackTagCompound == null)
        {
            EnergyHelper.setDefaultEnergyTag(container, 0);
        }

        return container.stackTagCompound.getInteger("Energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack container)
    {
        return maxEnergy;
    }
}
