package com.sparta.is.core.armor;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.sparta.is.armor.IArmorVariantHolder;
import com.sparta.is.core.creativetab.CreativeTab;
import com.sparta.is.core.network.Network;
import com.sparta.is.core.network.message.MessageUnitSettings;
import com.sparta.is.core.reference.EnumUnitState;
import com.sparta.is.core.reference.Messages;
import com.sparta.is.core.reference.Reference;
import com.sparta.is.core.settings.UnitSettings;
import com.sparta.is.core.utils.ItemStackUtils;
import com.sparta.is.core.utils.helpers.EntityHelper;
import com.sparta.is.core.utils.helpers.ResourceLocationHelper;
import com.sparta.is.core.utils.interfaces.IISVariant;
import com.sparta.is.core.utils.interfaces.IModifyable;
import com.sparta.is.init.ModItems;
import gnu.trove.map.hash.TIntObjectHashMap;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.UsernameCache;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

public class ArmorIS extends ItemArmor implements IModifyable, IArmorVariantHolder, IISVariant
{
    protected UUID ownerUUID = getOwnerUUID();
    public String repairIngot = "";
    public String[] textures = new String[2];
    protected Multimap<String, AttributeModifier> properties = HashMultimap.create();
    protected boolean showInCreative = true;
    protected final String modifyType;
    public EnumUnitState state = EnumUnitState.STANDBY_STATE;
    private final String[] VARIANTS;
    private String ownerName = "Unknown";
    private int TOTAL_EQUALIZERS = 4;
    private String BASE_NAME;
    private EntityEquipmentSlot currentSlot = null;

    private static int totalShieldCapacity;
    private static int remainingShieldCapacity;

    public ArmorIS(String name, ArmorMaterial armorMaterial, EntityEquipmentSlot slot, int numberOfEqualizers, String ... variants)
    {
        super(armorMaterial, 0 , slot);
        this.setCreativeTab(CreativeTab.IS_TAB);
        this.setMaxStackSize(1);
        this.modifyType = "Armor";
        BASE_NAME = name;
        this.setRegistryName(name);
        this.setHasSubtypes(true);

        TOTAL_EQUALIZERS = numberOfEqualizers;

        if(variants.length < 0)
        {
            VARIANTS = new String[] {"normal"};
        }
        else
        {
            VARIANTS = variants;
        }

        ModItems.registerArmor(this);
    }

//    @Override
//    public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity)
//    {
//        return true;
//    }

    @Override
    public EntityEquipmentSlot getEquipmentSlot()
    {
        return EntityEquipmentSlot.CHEST;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped defaultModel)
    {
        return super.getArmorModel(entityLiving, itemStack, armorSlot, defaultModel);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("armor.%s:%s", Reference.MOD_ID, BASE_NAME);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("armor.%s:%s", Reference.MOD_ID, BASE_NAME);
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entityPlayer, EnumHand handIn)
    {
        ItemStack itemStack = entityPlayer.getHeldItem(handIn);

        if(!world.isRemote)
        {
            if(!ItemStackUtils.hasOwnerUUID(itemStack))
            {
                NBTTagCompound nbtTagCompound = EntityHelper.getCustomEntityData(entityPlayer);
                UnitSettings unitSettings = new UnitSettings();
                unitSettings.readFromNBT(nbtTagCompound);

                unitSettings.setTotalEqualizers(TOTAL_EQUALIZERS);
                unitSettings.setDeployedState(getState().getMeta());
                unitSettings.setOwnerName(entityPlayer.getDisplayNameString());
                unitSettings.setUnitName(itemStack.getDisplayName());

                ItemStackUtils.setOwner(itemStack, entityPlayer);
                this.setOwnerName(entityPlayer.getDisplayNameString());

                unitSettings.writeToNBT(nbtTagCompound);
                entityPlayer.sendMessage(new TextComponentTranslation(Messages.UnitControl.OWNER_SET_TO_SELF, itemStack.getDisplayName()));
                EntityHelper.saveCustomEntityData(entityPlayer, nbtTagCompound);

                if(entityPlayer.getEntityWorld().isRemote)
                {
                    Network.INSTANCE.sendTo(new MessageUnitSettings(unitSettings), (EntityPlayerMP) entityPlayer);
                }
            }
            else if(ItemStackUtils.hasOwnerUUID(itemStack))
            {
                if(entityPlayer.getUniqueID() != getOwnerUUID())
                {
                    entityPlayer.sendMessage(new TextComponentTranslation(Messages.UnitControl.INVALID_OWNER, ItemStackUtils.getOwnerName(itemStack)));
                }
                else if(entityPlayer.getUniqueID() == ItemStackUtils.getOwnerUUID(itemStack))
                {
                    entityPlayer.sendMessage(new TextComponentTranslation(Messages.UnitControl.ALREADY_OWNER, itemStack.getDisplayName()));
                }
            }

            return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
        }

        return new ActionResult<>(EnumActionResult.FAIL, itemStack);
    }

    protected String getUnwrappedLocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    public ArmorIS setArmorTextures(String[] textures)
    {
        this.textures = textures;
        return this;
    }

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        if(state == EnumUnitState.STANDBY_STATE) { return textures[1]; }
        else if(state == EnumUnitState.PARTIAL_DEPLOY_STATE) { if(textures[1] != null) return textures[1]; else return textures[0]; }
        else if(state == EnumUnitState.FULL_DEPLOY_STATE) { if(textures[2] != null) return textures[2]; else if(textures[1] != null) return textures[1]; else return textures[0]; }
        else { return null; }
    }

    public int getShieldCapacity()
    {
        return totalShieldCapacity;
    }

    public void setShieldCapacity(int capacity)
    {
        totalShieldCapacity = capacity;
    }

    public void lowerShieldEnergy(ItemStack itemStack)
    {
        remainingShieldCapacity -= 10;
    }

    public int getRemainingShieldCapacity()
    {
        return remainingShieldCapacity;
    }

    public void setRemainingShieldCapacity()
    {
        remainingShieldCapacity = totalShieldCapacity;
    }

    public EnumUnitState getState()
    {
        return state;
    }

    public void setState(EnumUnitState newState)
    {
        state = newState;
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
    public String getBaseTagName()
    {
        return "ISUnit";
    }

    @Override
    public String getModifyType()
    {
        return modifyType;
    }

    @Override
    public String[] getTraits()
    {
        return new String[] {"armor"};
    }

    @Override
    public ArmorIS getArmor()
    {
        return this;
    }

    @Override
    public String[] getVariants()
    {
        return new String[0];
    }

    @Override
    public ItemMeshDefinition getCustomMeshDefinition()
    {
        return itemstack -> ResourceLocationHelper.getModelResourceLocation(BASE_NAME, VARIANTS[Math.abs(state.getMeta() % VARIANTS.length)]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addModelVariants(TIntObjectHashMap<ModelResourceLocation> variants)
    {
        int st = 0;
        for(String s : VARIANTS)
        {
            addVariant(variants, st++, s);
        }
    }

    @Override
    public void addVariant(TIntObjectHashMap<ModelResourceLocation> variants, int meta, String suffix)
    {
        variants.put(meta, ResourceLocationHelper.getModelResourceLocation(this.getRegistryName(), suffix));
    }

    @Override
    public void registerVariants()
    {
        TIntObjectHashMap<ModelResourceLocation> variants = new TIntObjectHashMap<>();
        addModelVariants(variants);
        for(int key : variants.keys())
        {
            ModelResourceLocation variant = variants.get(key);
            ModelLoader.setCustomModelResourceLocation(this, key, variant);
        }
    }
}
