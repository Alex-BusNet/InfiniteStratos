package com.sparta.is.item;

import com.sparta.is.armor.ArmorIS;
import com.sparta.is.armor.UnitByakushiki;
import com.sparta.is.item.base.ItemISMelee;
import com.sparta.is.network.Network;
import com.sparta.is.network.message.MessageOneOffSettings;
import com.sparta.is.reference.Key;
import com.sparta.is.reference.Materials;
import com.sparta.is.reference.Messages;
import com.sparta.is.reference.Names;
import com.sparta.is.settings.OneOffSettings;
import com.sparta.is.settings.UnitSettings;
import com.sparta.is.utils.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.List;

public class ItemYukihiraNigata extends ItemISMelee implements IOwnable, IKeyBound, IEnergy
{
    private int oneOff = 0;
    public int maxEnergy;
    public int maxTransfer;
    public int energyPerUse;
    public int energyPerUseOneOff;
    private static final String[] VARIANTS = {"yukihiranigata", "reiraku"};

    //This should be removed. It isn't a good idea to have a copy of the unit exist within the sword object
    private UnitByakushiki byakushiki = new UnitByakushiki();

    public int damage = 1;
    public int damageOneOff = 0;

    public ItemYukihiraNigata()
    {
        super(Names.Weapons.YUKIHIRA_NIGATA, VARIANTS);
        this.isFull3D();
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
    public ItemMeshDefinition getCustomMeshDefinition()
    {
        return itemStack -> (oneOff == 1) ? ResourceLocationHelper.getModelResourceLocation(VARIANTS[1])
                : ResourceLocationHelper.getModelResourceLocation(VARIANTS[0]);
    }

    @Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase entity, EntityLivingBase player)
    {
        EntityPlayer entityPlayer = (EntityPlayer) player;
        double shieldEnergy = 0.0;

        if(entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != ItemStack.EMPTY)
        {
            Item item = entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();

            if(item instanceof ArmorIS )
            {
                useEnergy(itemStack, false);
            }
        }

        if(!entityPlayer.capabilities.isCreativeMode || useEnergy(itemStack, false) == getEnergyPerUse(itemStack))
        {
            if(!(entity instanceof EntityPlayer))
            {
                entity.attackEntityFrom(DamageSourceHelper.sword, Materials.Weapons.IS_SWORD.getDamageVsEntity());
            }
            else
            {
                if(oneOff == 1)
                {
                    DamageSourceHelper.sword.setBarrierVoid(true);
                }

                //TODO: Add shield energy reduction method
                entity.attackEntityFrom(DamageSourceHelper.sword, Materials.Weapons.IS_SWORD.getDamageVsEntity());
            }
        }

        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState block, BlockPos pos, EntityLivingBase entityLiving)
    {
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
            list.add(StringHelper.RED + "and deals damage directly to pilot." + StringHelper.END);
            list.add(StringHelper.RED + "Consumes lots of energy very quickly" + StringHelper.END);

        }

        list.add(" ");
        list.add(StringHelper.ORANGE + getEnergyPerUse(itemStack) + " " + StringHelper.localize("info.is.tool.energyPerUse") + StringHelper.END);

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer entityPlayer, EnumHand hand)
    {
        if (entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != ItemStack.EMPTY)
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
                    entityPlayer.sendMessage(new TextComponentTranslation(Messages.ItemUse.SWORD_SWING_FAILED, entityPlayer.getHeldItemMainhand().getItem().getItemStackDisplayName(entityPlayer.getHeldItemMainhand())));
                }
                else
                {
                    entityPlayer.getHeldItemMainhand().setItemDamage(getMetadata(0)); //, this.getMaxItemUseDuration(itemStack));
                }
            }
            else
            {
                /*
                 * Player tried to use weapon with different unit
                 */
                if (entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ArmorIS)
                {
                    entityPlayer.sendMessage(new TextComponentTranslation(Messages.ItemUse.INVALID_UNIT,  entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getDisplayName()));
                }
                else
                {
                    entityPlayer.sendMessage(new TextComponentTranslation(Messages.ItemUse.SWORD_SWING_FAILED, entityPlayer.getHeldItemMainhand().getItem().getItemStackDisplayName(entityPlayer.getHeldItemMainhand())));
                }
            }
        }
        else
        {
            entityPlayer.sendMessage(new TextComponentTranslation(Messages.ItemUse.SWORD_SWING_FAILED, entityPlayer.getHeldItemMainhand().getItem().getItemStackDisplayName(entityPlayer.getHeldItemMainhand())));
        }

        return new ActionResult(EnumActionResult.PASS, entityPlayer.getHeldItem(hand));
    }

//    @Override
//    public void getSubItems(Item item, CreativeTab tabs, List list)
//    {
////        list.add(EnergyHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), 0));
//        list.add(EnergyHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), maxEnergy));
//    }


    @Override
    public void doKeyBindingAction(EntityPlayer entityPlayer, Key key, boolean falling)
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
                if(entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != ItemStack.EMPTY)
                {
                    if(entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof UnitByakushiki)
                    {
                        if(byakushiki.getState() != 0)
                        {
                            oneOff = 1;
                            oneOffSettings.setOneOff(1);
                            entityPlayer.sendMessage(new TextComponentTranslation(Messages.ItemUse.ONE_OFF_ACTIVE));
                            setEmpoweredState(entityPlayer.getHeldItemMainhand(), true);
                        }
                        else
                        {
                            entityPlayer.sendMessage(new TextComponentTranslation(Messages.ItemUse.INVALID_ACTION));
                        }
                    }
                    else
                    {
                        entityPlayer.sendMessage(new TextComponentTranslation(Messages.ItemUse.INVALID_UNIT, entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getDisplayName()));
                    }
                }
            }
            else if ( key == Key.ONE_OFF_ABILITY && oneOff != 0 )
            {
                oneOff = 0;
                oneOffSettings.setOneOff(0);
                entityPlayer.sendMessage(new TextComponentTranslation(Messages.ItemUse.ONE_OFF_DEACTIVE));
                setEmpoweredState(entityPlayer.getHeldItemMainhand(), false);
            }

            unitSettings.writeToNBT(playerCustomData);
            oneOffSettings.writeToNBT(playerCustomData);
            EntityHelper.saveCustomEntityData(entityPlayer, playerCustomData);

            if(entityPlayer.getEntityWorld().isRemote)
            {
                Network.INSTANCE.sendTo(new MessageOneOffSettings(oneOffSettings), (EntityPlayerMP) entityPlayer);
            }
        }
    }

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
    public int getStoredEnergy(ItemStack container)
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

    public int getOneOff()
    {
        return oneOff;
    }

    public boolean isEmpowered(ItemStack stack)
    {
        return (stack.getTagCompound() == null) ? false : stack.getTagCompound().getBoolean("oneOffActive");
    }


    public boolean setEmpoweredState(ItemStack stack, boolean state)
    {
        if(getStoredEnergy(stack) > getEnergyPerUse(stack))
        {
            stack.getTagCompound().setBoolean("oneOffActive", true);
            return true;
        }
        stack.getTagCompound().setBoolean("oneOffActive", false);
        return false;
    }
}
