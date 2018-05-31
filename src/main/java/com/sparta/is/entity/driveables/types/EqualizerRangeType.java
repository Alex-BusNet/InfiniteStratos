package com.sparta.is.entity.driveables.types;

import com.sparta.is.armor.models.ModelEqualizerRange;
import com.sparta.is.item.ItemBullet;
import com.sparta.is.core.reference.EnumFireMode;
import com.sparta.is.core.reference.EnumSecondaryFunction;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EqualizerRangeType extends InfoType implements IScope
{
    public List<ShootableType> ammo  = new ArrayList<ShootableType>(), nonExplosiveAmmo = new ArrayList<ShootableType>();

    public boolean canForceReload = true;
    public int reloadTime;
    public int recoil;
    public float bulletSpeed = 0.0f;
    public float damage;
    public float bulletSpread = 0f;
    public int entitiesPerShot = 1;
    public boolean deployable = false;
    public float knockback = 0f;
    // Delay, in ticks, between shots
    public float delayBetweenShots = 1f;
    public int ammoItemsInGun = 1;
    public int numBullets;
    public EnumFireMode mode = EnumFireMode.FULL_AUTO;
    public int roundPerBurst = 3;
    public EnumSecondaryFunction secondaryFunction = EnumSecondaryFunction.ZOOM;
    public boolean oneHanded = false;
    public boolean usableByPlayers = false, usableByUnits = true;
    public int meleeTime = 1;

    public boolean shield = false;
    public Vector3f shieldOrigin, shieldDimensions;
    public float shieldDamageAbsorbtion = 0F;

    //Sounds
    /** The sound played upon shooting */
    public String shootSound;
    /** The length of the sound for looping sounds */
    public int shootSoundLength;
    /** Whether to distort the sound or not. Generally only set to false for looping sounds */
    public boolean distortSound = true;
    /** The sound to play upon reloading */
    public String reloadSound;

    //Looping sounds
    /** Whether the looping sounds should be used. Automatically set if the player sets any one of the following sounds */
    public boolean useLoopingSounds = false;
    /** Played when the player starts to hold shoot */
    public String warmupSound;
    public int warmupSoundLength = 20;
    /** Played in a loop until player stops holding shoot */
    public String loopedSound;
    public int loopedSoundLength = 20;
    /** Played when the player stops holding shoot */
    public String cooldownSound;


    /** The sound to play upon weapon swing */
    public String meleeSound;
    /** The sound to play while holding the weapon in the hand*/
    public String idleSound;
    public int idleSoundLength;

    //Modifiers
    /** Speeds up or slows down player movement when this item is held */
    public float moveSpeedModifier = 1F;
    /** Gives knockback resistance to the player */
    public float knockbackModifier = 0F;

    @SideOnly(Side.CLIENT)
    public ModelEqualizerRange model;

    // GUI Properties
    public boolean showAttachments;
    public boolean showDamage;
    public boolean showReloadTimes;
    public boolean showSpread;

    // Scope Properties
    public String defaultScopeTexture = " ";
    public boolean hasScopeOverlay = false;
    public float zoomLevel = 0f;
    public float FOVFactor = 0f;

    // Attachment Properties
    public ArrayList<AttachmentType> allowedAttachments = new ArrayList<AttachmentType>();
    public boolean allowAllAttachments = false;
    public boolean allowBarrelAttachments = false;
    public boolean allowGripAttachments = false;
    public boolean allowScopeAttachments = false;
    public boolean allowStockAttachments = false;
    public int numGenericAttachmentSlots = 0;

    public static HashMap<Integer, EqualizerRangeType> rangeTypes = new HashMap<Integer, EqualizerRangeType>();
    public static ArrayList<EqualizerRangeType> rangeList = new ArrayList<EqualizerRangeType>();

    public EqualizerRangeType(TypeFile file)
    {
        super(file);
    }

    public boolean isAmmo(ShootableType type)
    {
        return ammo.contains(type);
    }

    public boolean isAmmo(ItemStack stack)
    {
        if (stack == null)
            return false;
        else if(stack.getItem() instanceof ItemBullet )
        {
            return isAmmo(((ItemBullet)stack.getItem()).type);
        }
        return false;
    }

    public ArrayList<AttachmentType> getCurrentAttachments(ItemStack gun)
    {
        checkForTags(gun);
        ArrayList<AttachmentType> attachments = new ArrayList<AttachmentType>();
        NBTTagCompound attachmentTags = gun.getTagCompound().getCompoundTag("attachments");
        NBTTagList genericsList = attachmentTags.getTagList("generics", (byte)10); //TODO : Check this 10 is correct
        for(int i = 0; i < numGenericAttachmentSlots; i++)
        {
            appendToList(gun, "generic_" + i, attachments);
        }
        appendToList(gun, "barrel", attachments);
        appendToList(gun, "scope", attachments);
        appendToList(gun, "stock", attachments);
        appendToList(gun, "grip", attachments);
        return attachments;
    }
    private void appendToList(ItemStack gun, String name, ArrayList<AttachmentType> attachments)
    {
        AttachmentType type = getAttachment(gun, name);
        if(type != null) attachments.add(type);
    }

    //Attachment getter methods
    public AttachmentType getBarrel(ItemStack gun) { return getAttachment(gun, "barrel"); }
    public AttachmentType getScope(ItemStack gun) { return getAttachment(gun, "scope"); }
    public AttachmentType getStock(ItemStack gun) { return getAttachment(gun, "stock"); }
    public AttachmentType getGrip(ItemStack gun) { return getAttachment(gun, "grip"); }
    public AttachmentType getGeneric(ItemStack gun, int i) { return getAttachment(gun, "generic_" + i); }

    //Attachment ItemStack getter methods
//    public ItemStack getBarrelItemStack(ItemStack gun) { return getAttachmentItemStack(gun, "barrel"); }
//    public ItemStack getScopeItemStack(ItemStack gun) { return getAttachmentItemStack(gun, "scope"); }
//    public ItemStack getStockItemStack(ItemStack gun) { return getAttachmentItemStack(gun, "stock"); }
//    public ItemStack getGripItemStack(ItemStack gun) { return getAttachmentItemStack(gun, "grip"); }
//    public ItemStack getGenericItemStack(ItemStack gun, int i) { return getAttachmentItemStack(gun, "generic_" + i); }

    /** Generalised attachment getter method */
    public AttachmentType getAttachment(ItemStack gun, String name)
    {
        checkForTags(gun);
        return AttachmentType.getFromNBT(gun.getTagCompound().getCompoundTag("attachments").getCompoundTag(name));
    }

    /** Generalised attachment ItemStack getter method */
//    public ItemStack getAttachmentItemStack(ItemStack gun, String name)
//    {
//        checkForTags(gun);
//        return ItemStack.loadItemStackFromNBT(gun.getTagCompound().getCompoundTag("attachments").getCompoundTag(name));
//    }

    /** Method to check for null tags and assign default empty tags in that case */
    private void checkForTags(ItemStack gun)
    {
        //If the gun has no tags, give it some
        if(!gun.hasTagCompound())
        {
            gun.setTagCompound(new NBTTagCompound());
        }
        //If the gun has no attachment tags, give it some
        if(!gun.getTagCompound().hasKey("attachments"))
        {
            NBTTagCompound attachmentTags = new NBTTagCompound();
            for(int i = 0; i < numGenericAttachmentSlots; i++)
                attachmentTags.setTag("generic_" + i, new NBTTagCompound());
            attachmentTags.setTag("barrel", new NBTTagCompound());
            attachmentTags.setTag("scope", new NBTTagCompound());
            attachmentTags.setTag("stock", new NBTTagCompound());
            attachmentTags.setTag("grip", new NBTTagCompound());

            gun.getTagCompound().setTag("attachments", attachmentTags);
        }
    }

    public float getDamage(ItemStack stack)
    {
        float stackDamage = damage;
        for(AttachmentType attachment : getCurrentAttachments(stack))
        {
            stackDamage *= attachment.damageMultiplier;
        }
        return stackDamage;
    }

    /** Get the bullet spread of a specific gun, taking into account attachments */
    public float getSpread(ItemStack stack)
    {
        float stackSpread = bulletSpread;
        for(AttachmentType attachment : getCurrentAttachments(stack))
        {
            stackSpread *= attachment.spreadMultiplier;
        }
        return stackSpread;
    }

    /** Get the recoil of a specific gun, taking into account attachments */
    public float getRecoil(ItemStack stack)
    {
        float stackRecoil = recoil;
        for(AttachmentType attachment : getCurrentAttachments(stack))
        {
            stackRecoil *= attachment.recoilMultiplier;
        }
        return stackRecoil;
    }

    /** Get the bullet speed of a specific gun, taking into account attachments */
    public float getBulletSpeed(ItemStack stack)
    {
        float stackBulletSpeed = bulletSpeed;
        for(AttachmentType attachment : getCurrentAttachments(stack))
        {
            stackBulletSpeed *= attachment.bulletSpeedMultiplier;
        }
        return stackBulletSpeed;
    }

    /** Get the reload time of a specific gun, taking into account attachments */
    public float getReloadTime(ItemStack stack)
    {
        float stackReloadTime = reloadTime;
        for(AttachmentType attachment : getCurrentAttachments(stack))
        {
            stackReloadTime *= attachment.reloadTimeMultiplier;
        }
        return stackReloadTime;
    }

    /** Get the firing mode of a specific gun, taking into account attachments */
    public EnumFireMode getFireMode(ItemStack stack)
    {
        for(AttachmentType attachment : getCurrentAttachments(stack))
        {
            if(attachment.modeOverride != null)
                return attachment.modeOverride;
        }
        return mode;
    }

    /** Static String to GunType method */
    public static EqualizerRangeType getGun(String s)
    {
        return rangeTypes.get(s.hashCode());
    }

    public static EqualizerRangeType getGun(int hash)
    {
        return rangeTypes.get(hash);
    }

    @Override
    public float getZoomFactor()
    {
        return zoomLevel;
    }

    @Override
    public boolean hasZoomOverlay()
    {
        return hasScopeOverlay;
    }

    @Override
    public String getZoomOverlay()
    {
        return defaultScopeTexture;
    }

    @Override
    public float getFOVFactor()
    {
        return FOVFactor;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBase getModel()
    {
        return model;
    }

    public IScope getCurrentScope(ItemStack gunStack)
    {
        IScope attachedScope = getScope(gunStack);
        return attachedScope == null ? this : attachedScope;
    }
}
