package com.sparta.is.item;

import com.sparta.is.armor.ArmorIS;
import com.sparta.is.armor.UnitByakushiki;
import com.sparta.is.network.PacketHandler;
import com.sparta.is.network.message.MessageOneOffSettings;
import com.sparta.is.reference.Key;
import com.sparta.is.reference.Material;
import com.sparta.is.reference.Messages;
import com.sparta.is.reference.Names;
import com.sparta.is.settings.OneOffSettings;
import com.sparta.is.settings.UnitSettings;
import com.sparta.is.utility.DamageSourceHelper;
import com.sparta.is.utility.EntityHelper;
import com.sparta.is.utility.IKeyBound;
import com.sparta.repackage.cofh.api.energy.IEnergyContainerItem;
import com.sparta.repackage.cofh.lib.util.helpers.EnergyHelper;
import com.sparta.repackage.cofh.lib.util.helpers.MathHelper;
import com.sparta.repackage.cofh.lib.util.helpers.StringHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.List;

public class ItemYukihira extends ItemISEqualizer implements IKeyBound,IEnergyContainerItem
{
    private UnitByakushiki byakushiki = new UnitByakushiki();
    private int oneOff = 0;
    public int maxEnergy;
    public int maxTransfer;
    public int energyPerUse;
    public int energyPerUseOneOff;

    public int damage = 1;
    public int damageOneOff = 0;

//    IIcon yukihiraIcon;
//    IIcon reirakuIcon;

    private static final String TEX_LOC = "is:textures/items";

    public ItemYukihira()
    {
        super();
        this.setUnlocalizedName(Names.Weapons.YUKIHIRA_NIGATA);
//        this.setTextureName(Names.Weapons.YUKIHIRA_NIGATA);
        maxEnergy = byakushiki.getShieldCapacity();
        maxTransfer = 300;
        energyPerUse = 50;
        energyPerUseOneOff = 150;
    }

    public static void ReirakuByakuya(ItemStack itemStack, World world, Entity entity)
    {
        if(entity instanceof EntityPlayer)
        {
//            ((EntityPlayer) entity).capabilities.allowFlying = true;
//            ((EntityPlayer) entity).capabilities.setPlayerWalkSpeed(0.6F);
//            ((EntityPlayer) entity).capabilities.setFlySpeed(0.2F);
            ((EntityPlayer) entity).capabilities.disableDamage = true;
        }
    }

    protected int useEnergy(ItemStack stack, boolean simulate)
    {
        int unbreakingLevel = MathHelper.clamp(EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(34), stack), 0, 4);
        return extractEnergy(stack, isEmpowered(stack) ? energyPerUseOneOff * (5 - unbreakingLevel) / 5 : energyPerUse * (5 - unbreakingLevel) / 5, simulate);
    }

    protected int getEnergyPerUse(ItemStack stack)
    {
        int unbreakingLevel = MathHelper.clamp(EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(34), stack), 0, 4);
        return (isEmpowered(stack) ? energyPerUseOneOff : energyPerUse) * (5 - unbreakingLevel) / 5;
    }

    @Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase entity, EntityLivingBase player)
    {
        EntityPlayer entityPlayer = (EntityPlayer) player;
        double shieldEnergy = 0.0;

        if(entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != null)
        {
            Item item = entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();

            if(item instanceof ArmorIS)
            {
                useEnergy(itemStack, false);
            }
        }

        if(!entityPlayer.capabilities.isCreativeMode || useEnergy(itemStack, false) == getEnergyPerUse(itemStack))
        {
            if(!(entity instanceof EntityPlayer))
            {
                entity.attackEntityFrom(DamageSourceHelper.sword, Material.Weapons.IS_SWORD.getDamageVsEntity());
            }
            else
            {
                if(oneOff == 1)
                {
                    DamageSourceHelper.sword.setBarrierVoid(true);
                }

                //TODO: Add shield energy reduction method
                entity.attackEntityFrom(DamageSourceHelper.sword, Material.Weapons.IS_SWORD.getDamageVsEntity());
            }
        }

        return true;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean par4)
    {
        if(oneOff == 0)
        {
            list.add(StringHelper.LIGHT_BLUE + "\"Second Snowflake\"");
        }
        else if(oneOff == 1)
        {
            list.add(StringHelper.LIGHT_BLUE + "\"White Night of Downfall\"");
        }

        if( StringHelper.displayShiftForDetail && !StringHelper.isShiftKeyDown())
        {
            list.add(StringHelper.shiftForDetails());
        }
        if(!StringHelper.isShiftKeyDown())
        {
            return;
        }
        if(itemStack.getTagCompound() == null)
        {
            EnergyHelper.setDefaultEnergyTag(itemStack, 0);
        }
        list.add(StringHelper.localize("info.is.charge") + ": " + itemStack.getTagCompound().getInteger("Energy") + " / " + maxEnergy + "RF");

        if(oneOff == 0)
        {
            list.add(StringHelper.RED + "Has the One-Off Ability, 'Barrier-Void Attack'");
        }
        else if(oneOff == 1)
        {
            list.add(StringHelper.RED + "Bypasses an IS Unit's absolute defense" + StringHelper.END);
            list.add(StringHelper.RED + "Dealing damage directly to pilot." + StringHelper.END);
            list.add(StringHelper.RED + "Consumes lots of energy very quickly" + StringHelper.END);

        }

        list.add(" ");
        list.add(StringHelper.ORANGE + getEnergyPerUse(itemStack) + " " + StringHelper.localize("info.is.tool.energyPerUse") + StringHelper.END);

    }

//    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        if (entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != null)
        {
            /*
             * Checks if the Player has the correct unit equipped
             */

            if (entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem().getUnlocalizedName().equals(byakushiki.getUnlocalizedName()))
            {
                /*
                 * Checks if Byakushiki is in Standby mode
                 */
                if (byakushiki.getState() == 0)
                {
                    entityPlayer.addChatComponentMessage(new TextComponentTranslation(Messages.ItemUse.SWORD_SWING_FAILED, new Object[]{itemStack.getItem()}));
                }
                else
                {
                    entityPlayer.getHeldItemMainhand().setItemDamage(itemStack.getMetadata()); //, this.getMaxItemUseDuration(itemStack));
                    return itemStack;
                }
            }
            else
            {
                /*
                 * Player tried to use weapon with different unit
                 */
                if (entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ArmorIS)
                {
                    entityPlayer.addChatComponentMessage(new TextComponentTranslation(Messages.ItemUse.INVALID_UNIT, "The Yukihira Nigata", entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getUnlocalizedName()));
                }
                else
                {
                    entityPlayer.addChatComponentMessage(new TextComponentTranslation(Messages.ItemUse.SWORD_SWING_FAILED, new Object[]{itemStack.getItem()}));
                }
            }
        }
        else
        {

            entityPlayer.addChatComponentMessage(new TextComponentTranslation(Messages.ItemUse.SWORD_SWING_FAILED, new Object[]{itemStack.getItem()}));
        }

        return itemStack;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
//        list.add(EnergyHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), 0));
        list.add(EnergyHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), maxEnergy));
    }


    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean isCurrentItem)
    {
        if ( oneOff == 1 && byakushiki.getState() == 2)
        {
            this.setUnlocalizedName(Names.One_Off.REIRAKU_BYAKUYA);
//            this.setTextureName(TEX_LOC + Names.One_Off.REIRAKU_BYAKUYA);
            ReirakuByakuya(itemStack, world, entity);
        }
        else
        {
            this.setUnlocalizedName(Names.Weapons.YUKIHIRA_NIGATA);
//            this.setTextureName(TEX_LOC + Names.Weapons.YUKIHIRA_NIGATA);

            if(byakushiki.getState() == 2)
            {
//                ((EntityPlayer) entity).capabilities.allowFlying = true;
//                ((EntityPlayer) entity).capabilities.setPlayerWalkSpeed(0.3F);
//                ((EntityPlayer) entity).capabilities.setFlySpeed(0.1F);
            }
        }
    }

    @Override
    public void doKeyBindingAction(EntityPlayer entityPlayer, ItemStack itemStack, Key key, boolean falling)
    {
        if(key != Key.UNKNOWN)
        {
            NBTTagCompound playerCustomData = EntityHelper.getCustomEntityData(entityPlayer);
            OneOffSettings oneOffSettings = new OneOffSettings();
            oneOffSettings.readFromNBT(playerCustomData);
            UnitSettings unitSettings = new UnitSettings();
            unitSettings.readFromNBT(playerCustomData);

            if ( key == Key.ONE_OFF_ABILITY && oneOff != 1)
            {
                if(entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != null)
                {
                    if(entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof UnitByakushiki)
                    {
                        if(byakushiki.getState() != 0)
                        {
                            oneOff = 1;
                            oneOffSettings.setOneOff(1);
                            entityPlayer.addChatComponentMessage(new TextComponentTranslation(Messages.ItemUse.ONE_OFF_ACTIVE, "Reiraku Byakuya"));
                            setEmpoweredState(itemStack, true);
                        }
                        else
                        {
                            entityPlayer.addChatComponentMessage(new TextComponentTranslation(Messages.ItemUse.INVALID_ACTION));
                        }
                    }
                    else
                    {
                        entityPlayer.addChatComponentMessage(new TextComponentTranslation(Messages.ItemUse.INVALID_UNIT, new Object[]{itemStack.getItem()}, entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getUnlocalizedName()));
                    }
                }
            }
            else if ( key == Key.ONE_OFF_ABILITY_OFF && oneOff != 0 )
            {
                oneOff = 0;
                oneOffSettings.setOneOff(0);
                entityPlayer.addChatComponentMessage(new TextComponentTranslation(Messages.ItemUse.ONE_OFF_DEACTIVE, "Reiraku Byakuya"));
                setEmpoweredState(itemStack, false);
            }

            unitSettings.writeToNBT(playerCustomData);
            oneOffSettings.writeToNBT(playerCustomData);
            EntityHelper.saveCustomEntityData(entityPlayer, playerCustomData);
            PacketHandler.INSTANCE.sendTo(new MessageOneOffSettings(oneOffSettings), (EntityPlayerMP) entityPlayer);
        }
    }
//
//    @Override
//    public IIcon getIconIndex(ItemStack stack)
//    {
//        return getIcon(stack, 0);
//    }
//
//    @Override
//    public IIcon getIcon(ItemStack itemStack, int pass)
//    {
//        return oneOff == 0 ? this.yukihiraIcon : this.reirakuIcon;
//    }

//    @Override
//    @SideOnly(Side.CLIENT)
//    public void registerIcons(IIconRegister iconRegister)
//    {
//        this.yukihiraIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
//        this.reirakuIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_oneOff");
//        LogHelper.debug("Yukihira icon loc:" + yukihiraIcon.getIconName());
//        LogHelper.debug("Reiraku icon loc:" + reirakuIcon.getIconName());
//    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate)
    {
        if(container.getTagCompound() == null)
        {
            EnergyHelper.setDefaultEnergyTag(container, 100);
        }

        int stored = container.getTagCompound().getInteger("Energy");
        int receive = Math.min(maxReceive, Math.min(maxEnergy - stored, maxTransfer));

        if(!simulate)
        {
            stored += receive;
            container.getTagCompound().setInteger("Energy", stored);
        }

        return receive;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate)
    {
        if(container.getTagCompound() == null)
        {
            EnergyHelper.setDefaultEnergyTag(container, 0);
        }

        if(container.getTagCompound().hasKey("Unbreakable"))
        {
            container.getTagCompound().removeTag("Unbreakable");
        }
        int stored = container.getTagCompound().getInteger("Energy");
        int extract = Math.min(maxExtract, stored);

        if(!simulate)
        {
            stored -= extract;
            container.getTagCompound().setInteger("Energy", stored);

            if(stored == 0)
            {
                container.getTagCompound().setInteger("oneOffActive", 0);
                oneOff = 0;
            }
        }

        return extract;
    }

    @Override
    public int getEnergyStored(ItemStack container)
    {
        if(container.getTagCompound() == null)
        {
            EnergyHelper.setDefaultEnergyTag(container, 0);
        }

        return container.getTagCompound().getInteger("Energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack container)
    {
        return maxEnergy;
    }

//    @Override
    public boolean isEmpowered(ItemStack stack)
    {
        return stack.getTagCompound() == null ? false : stack.getTagCompound().getBoolean("oneOffActive");
    }

//    @Override
    public boolean setEmpoweredState(ItemStack stack, boolean state)
    {
        if(getEnergyStored(stack) > getEnergyPerUse(stack))
        {
            stack.getTagCompound().setBoolean("oneOffActive", true);
            return true;
        }
        stack.getTagCompound().setBoolean("oneOffActive", false);
        return false;
    }

//    @Override
    public void onStateChange(EntityPlayer player, ItemStack stack)
    {

    }

    public int getOneOff()
    {
        return oneOff;
    }
}
