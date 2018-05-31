package com.sparta.is.item;

import com.sparta.is.armor.UnitKuroAkiko;
import com.sparta.is.core.armor.ArmorIS;
import com.sparta.is.core.item.ItemISMelee;
import com.sparta.is.core.reference.Messages;
import com.sparta.is.core.reference.Names;
import com.sparta.is.core.utils.helpers.LogHelper;
import com.sparta.is.core.utils.helpers.StringHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.List;

public class ItemElucidator extends ItemISMelee
{
    public int maxEnergy;
    public int maxTransfer;
    public int energyPerUse;
    private boolean displayOnce = false;
    private static final String[] VARIANTS = {"normal"};

    public ItemElucidator()
    {
        super(Names.Weapons.ELUCIDATOR, VARIANTS);
        maxEnergy = 50000;
        this.maxTransfer = 300;
        this.energyPerUse = 50;
        this.setFull3D();
    }

    @Override
    public void addInformation(ItemStack itemStack, World worldIn, List<String> toolTip, ITooltipFlag ttFlag)
    {

        toolTip.add(StringHelper.RED + "Kuro Akiko's Primary Weapon");

        if( StringHelper.displayShiftForDetail && !StringHelper.isShiftKeyDown())
        {
            toolTip.add(StringHelper.shiftForDetails());
        }
        if(!StringHelper.isShiftKeyDown())
        {
            return;
        }
        if(itemStack.getTagCompound() != null)
        {
//            EnergyHelper.setDefaultEnergyTag(itemStack, 0);

            toolTip.add(StringHelper.localize("info.is.charge") + ": " + itemStack.getTagCompound().getInteger("Energy") + " / " + maxEnergy + "RF");
        }

        toolTip.add(" ");
        toolTip.add(StringHelper.ORANGE + energyPerUse + " " + StringHelper.localize("info.is.tool.energyPerUse") + StringHelper.END);

    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand)
    {
        if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != ItemStack.EMPTY)
        {
            ItemStack itemStack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

            if(itemStack.getItem() instanceof UnitKuroAkiko )
            {
                if(((UnitKuroAkiko) itemStack.getItem()).getState().getMeta() == 0)
                {
                    LogHelper.info("Use Action Failed");
                    return EnumActionResult.FAIL;
                }
            }
        }
        return EnumActionResult.PASS;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != ItemStack.EMPTY)
        {
            ItemStack itemStack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

            if(itemStack.getItem() instanceof UnitKuroAkiko)
            {
                if(((UnitKuroAkiko) itemStack.getItem()).getState().getMeta() == 0)
                {
                    LogHelper.info("Attack Action Failed");
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase entity, EntityLivingBase player)
    {
        EntityPlayer entityPlayer = (EntityPlayer) player;
        double shieldEnergy = 0.0;

        if(entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != ItemStack.EMPTY)
        {
            Item item = entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();

            if(item instanceof UnitKuroAkiko )
            {
                if(((UnitKuroAkiko) item).getState().getMeta() == 0)
                {
                    return true;
                }
            }
        }

        if(!entityPlayer.capabilities.isCreativeMode)
        {
            if(!(entity instanceof EntityPlayer))
            {
//                entity.attackEntityFrom(DamageSourceHelper.sword, Materials.Weapons.IS_SWORD.getDamageVsEntity());
            }
            else
            {
                //TODO: Add shield energy reduction method
//                entity.attackEntityFrom(DamageSourceHelper.sword, Materials.Weapons.IS_SWORD.getDamageVsEntity());
            }
        }

        return false;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer entityPlayer, EnumHand hand)
    {
        // displayOnce prevents the right click message from appearing twice per click.
        if(!displayOnce)
        {
            displayOnce = true;
            if ( entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != ItemStack.EMPTY )
            {
                /*
                 * Checks if the Player has the correct unit equipped
                 */

                if ( entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof UnitKuroAkiko )
                {
                    /*
                     * Checks if Kuro Akiko is in Standby mode
                     */
                    Item unit = entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();
                    if ( ((UnitKuroAkiko) unit).getState().getMeta() == 0 )
                    {
                        entityPlayer.sendMessage(new TextComponentTranslation(Messages.ItemUse.SWORD_SWING_FAILED, "the Elucidator"));
                    }
                    else
                    {
                        return new ActionResult(EnumActionResult.PASS, entityPlayer.getHeldItemMainhand());
                    }
                }
                else
                {
                    /*
                     * Player tried to use weapon with different unit
                     */
                    if ( entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ArmorIS )
                    {
                        entityPlayer.sendMessage(new TextComponentTranslation(Messages.ItemUse.INVALID_UNIT, "The Elucidator", entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getDisplayName()));
                    }
                    else
                    {
                        entityPlayer.sendMessage(new TextComponentTranslation(Messages.ItemUse.SWORD_SWING_FAILED, "the Elucidator"));
                    }
                }
            }
            else
            {

                entityPlayer.sendMessage(new TextComponentTranslation(Messages.ItemUse.SWORD_SWING_FAILED, entityPlayer.getHeldItemMainhand().getDisplayName()));
            }
        }
        else
        {
            displayOnce = false;
        }

        return new ActionResult(EnumActionResult.PASS, entityPlayer.getHeldItemMainhand());
    }
}
