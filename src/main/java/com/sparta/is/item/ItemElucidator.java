package com.sparta.is.item;

import com.sparta.is.armor.UnitKuroAkiko;
import com.sparta.is.reference.Names;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemElucidator extends ItemISEqualizer /*implements IEnergyContainerItem*/
{
    private UnitKuroAkiko kuroAkiko = new UnitKuroAkiko();
    public int maxEnergy;
    public int maxTransfer;
    public int energyPerUse;

    public ItemElucidator()
    {
        super();
        this.setUnlocalizedName(Names.Weapons.ELUCIDATOR);
//        this.setTextureName(Names.Weapons.ELUCIDATOR);
        maxEnergy = kuroAkiko.getShieldCapacity();
        this.maxTransfer = 300;
        this.energyPerUse = 50;
    }

//    protected int useEnergy(ItemStack stack, boolean simulate)
//    {
//        int unbreakingLevel = MathHelper.clampI(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack), 0, 4);
//        return extractEnergy(stack, energyPerUse * (5 - unbreakingLevel) / 5, simulate);
//    }
//
//    protected int getEnergyPerUse(ItemStack stack)
//    {
//        int unbreakingLevel = MathHelper.clampI(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack), 0, 4);
//        return energyPerUse * (5 - unbreakingLevel) / 5;
//    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean isCurrentItem)
    {

    }

//    @Override
//    public boolean hitEntity(ItemStack itemStack, EntityLivingBase entity, EntityLivingBase player)
//    {
//        EntityPlayer entityPlayer = (EntityPlayer) player;
//        double shieldEnergy = 0.0;
//
//        if(entityPlayer.getCurrentArmor(2) != null)
//        {
//            Item item = entityPlayer.getCurrentArmor(2).getItem();
//
//            if(item instanceof ArmorIS )
//            {
//                useEnergy(itemStack, false);
//            }
//        }
//
//        if(!entityPlayer.capabilities.isCreativeMode || useEnergy(itemStack, false) == getEnergyPerUse(itemStack))
//        {
//            if(!(entity instanceof EntityPlayer))
//            {
//                entity.attackEntityFrom(DamageSourceHelper.sword, Material.Weapons.IS_SWORD.getDamageVsEntity());
//            }
//            else
//            {
//                //TODO: Add shield energy reduction method
//                entity.attackEntityFrom(DamageSourceHelper.sword, Material.Weapons.IS_SWORD.getDamageVsEntity());
//            }
//        }
//
//        return true;
//    }

//    @Override
//    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean par4)
//    {
//
//        list.add(StringHelper.RED + "Kuro Akiko's Primary Weapon");
//        if( StringHelper.displayShiftForDetail && !StringHelper.isShiftKeyDown())
//        {
//            list.add(StringHelper.shiftForDetails());
//        }
//        if(!StringHelper.isShiftKeyDown())
//        {
//            return;
//        }
//        if(itemStack.stackTagCompound == null)
//        {
//            EnergyHelper.setDefaultEnergyTag(itemStack, 0);
//        }
//        list.add(StringHelper.localize("info.is.charge") + ": " + itemStack.stackTagCompound.getInteger("Energy") + " / " + maxEnergy + "RF");
//        list.add(" ");
//        list.add(StringHelper.ORANGE + getEnergyPerUse(itemStack) + " " + StringHelper.localize("info.is.tool.energyPerUse") + StringHelper.END);
//
//    }

//    @Override
//    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
//    {
//        if (entityPlayer.getCurrentArmor(2) != null)
//        {
//            /*
//             * Checks if the Player has the correct unit equipped
//             */
//
//            if (entityPlayer.getCurrentArmor(2).getItem().getUnlocalizedName().equals(kuroAkiko.getUnlocalizedName()))
//            {
//                /*
//                 * Checks if Kuro Akiko is in Standby mode
//                 */
//                if (kuroAkiko.getState() == 0)
//                {
//                    entityPlayer.addChatComponentMessage(new TextComponentTranslation(Messages.ItemUse.SWORD_SWING_FAILED, "the Elucidator"));
//                }
//                else
//                {
//                    entityPlayer.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
//                    return itemStack;
//                }
//            }
//            else
//            {
//                /*
//                 * Player tried to use weapon with different unit
//                 */
//                if (entityPlayer.getCurrentArmor(2).getItem() instanceof ArmorIS)
//                {
//                    entityPlayer.addChatComponentMessage(new TextComponentTranslation(Messages.ItemUse.INVALID_UNIT, "The Elucidator", entityPlayer.getCurrentArmor(2).getItem().getItemStackDisplayName(entityPlayer.getCurrentArmor(2))));
//                }
//                else
//                {
//                    entityPlayer.addChatComponentMessage(new TextComponentTranslation(Messages.ItemUse.SWORD_SWING_FAILED, "The Elucidator"));
//                }
//            }
//        }
//        else
//        {
//
//            entityPlayer.addChatComponentMessage(new TextComponentTranslation(Messages.ItemUse.SWORD_SWING_FAILED, itemStack));
//        }
//
//        return itemStack;
//    }

//    @Override
//    public void getSubItems(Item item, CreativeTabs tabs, List list)
//    {
//        list.add(EnergyHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), maxEnergy));
//    }
//
//
//    /* IEnergyContainerItem */
//    @Override
//    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate)
//    {
//        return 0;
//    }
//
//    @Override
//    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate)
//    {
//        return 0;
//    }
//
//    @Override
//    public int getEnergyStored(ItemStack container)
//    {
//        return 0;
//    }
//
//    @Override
//    public int getMaxEnergyStored(ItemStack container)
//    {
//        return 0;
//    }
}
