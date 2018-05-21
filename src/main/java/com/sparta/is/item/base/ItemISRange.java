package com.sparta.is.item.base;

import com.sparta.is.armor.raytracing.Raytracer;
import com.sparta.is.client.render.model.GunAnimations;
import com.sparta.is.client.render.model.InstantBulletRenderer;
import com.sparta.is.entity.driveables.EntityBullet;
import com.sparta.is.entity.driveables.ShotData;
import com.sparta.is.entity.driveables.types.*;
import com.sparta.is.handler.PlayerData;
import com.sparta.is.handler.PlayerEventHandler;
import com.sparta.is.item.ItemBullet;
import com.sparta.is.reference.EnumFireMode;
import com.sparta.is.reference.EnumSecondaryFunction;
import com.sparta.is.utils.ClientSideUtils;
import com.sparta.is.utils.InventoryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemISRange extends ItemIS
{

    private static final int CLIENT_TO_SERVER_UPDATE_INTERVAL = 1;
    private static final int SERVER_TO_CLIENT_UPDATE_INTERVAL = 2;

    private EqualizerRangeType type;

    public EqualizerRangeType getType() { return type; }

    public InfoType getInfoType() { return type; }

    private static boolean rightMouseHeld;
    private static boolean lastRightMouseHeld;
    private static boolean leftMouseHeld;
    private static boolean lastLeftMouseHeld;

    private static boolean GetMouseHeld(boolean isOffHand) { return isOffHand ? leftMouseHeld : rightMouseHeld; }
    private static boolean GetLastMouseHeld(boolean isOffHand) { return isOffHand ? lastLeftMouseHeld : lastRightMouseHeld; }

    private static List<ShotData> shotsFiredClient = new ArrayList<ShotData>(), shotsFiredServer = new ArrayList<ShotData>();

    public ItemISRange(EqualizerRangeType type)
    {
        super("Generic Range Equalizer");
        this.type = type;
        type.item = this;
    }

    public ItemStack getBulletItemStack(ItemStack gun, int id)
    {
        //If the gun has no tags, give it some
        if(!gun.hasTagCompound())
        {
            gun.setTagCompound(new NBTTagCompound());
            return null;
        }
        //If the gun has no ammo tags, give it some
        if(!gun.getTagCompound().hasKey("ammo"))
        {
            NBTTagList ammoTagsList = new NBTTagList();
            for(int i = 0; i < type.ammoItemsInGun; i++)
            {
                ammoTagsList.appendTag(new NBTTagCompound());
            }
            gun.getTagCompound().setTag("ammo", ammoTagsList);
            return null;
        }
        //Take the list of ammo tags
        NBTTagList ammoTagsList = gun.getTagCompound().getTagList("ammo", Constants.NBT.TAG_COMPOUND);
        //Get the specific ammo tags required
        NBTTagCompound ammoTags = ammoTagsList.getCompoundTagAt(id);
        return new ItemStack(ammoTags);
    }

    /** Set the bullet item stack stored in the gun's NBT data (the loaded magazine / bullets) */
    public void setBulletItemStack(ItemStack gun, ItemStack bullet, int id)
    {
        //If the gun has no tags, give it some
        if(!gun.hasTagCompound())
        {
            gun.setTagCompound(new NBTTagCompound());
        }
        //If the gun has no ammo tags, give it some
        if(!gun.getTagCompound().hasKey("ammo"))
        {
            NBTTagList ammoTagsList = new NBTTagList();
            for(int i = 0; i < type.ammoItemsInGun; i++)
            {
                ammoTagsList.appendTag(new NBTTagCompound());
            }
            gun.getTagCompound().setTag("ammo", ammoTagsList);
        }
        //Take the list of ammo tags
        NBTTagList ammoTagsList = gun.getTagCompound().getTagList("ammo", Constants.NBT.TAG_COMPOUND);
        //Get the specific ammo tags required
        NBTTagCompound ammoTags = ammoTagsList.getCompoundTagAt(id);
        //Represent empty slots by nulltypes
        if(bullet == null)
        {
            ammoTags = new NBTTagCompound();
        }
        //Set the tags to match the bullet stack
        bullet.writeToNBT(ammoTags);
    }

    /** Method for dropping items on reload and on shoot */
    public static void dropItem(World world, Entity entity, String itemName)
    {
        if (itemName != null)
        {
            int damage = 0;
            if (itemName.contains("."))
            {
                damage = Integer.parseInt(itemName.split("\\.")[1]);
                itemName = itemName.split("\\.")[0];
            }
            ItemStack dropStack = InfoType.getRecipeElement(itemName, damage);
            entity.entityDropItem(dropStack, 0.5F);
        }
    }

    @SideOnly(Side.CLIENT)
    public void onUpdateClient(ItemStack gunstack, int gunSlot, World world, Entity entity, boolean isOffHand, boolean hasOffHand)
    {
        if(!(entity instanceof EntityPlayer))
        {
            return;
        }
        // Get useful objects
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = (EntityPlayer)entity;
        PlayerData data = PlayerEventHandler.getPlayerData(player, Side.CLIENT);

        // Play idle sounds
//        if (soundDelay <= 0 && type.idleSound != null)
//        {
//            PacketPlaySound.sendSoundPacket(entity.posX, entity.posY, entity.posZ, FlansMod.soundRange, entity.dimension, type.idleSound, false);
//            soundDelay = type.idleSoundLength;
//        }

        // This code is not for deployables
        if (type.deployable)
            return;

        // Do not shoot ammo bags, flags or dropped gun items
//        if(mc.objectMouseOver != null && (mc.objectMouseOver.entityHit instanceof EntityFlagpole || mc.objectMouseOver.entityHit instanceof EntityFlag || mc.objectMouseOver.entityHit instanceof EntityGunItem || (mc.objectMouseOver.entityHit instanceof EntityGrenade && ((EntityGrenade)mc.objectMouseOver.entityHit).type.isDeployableBag)))
//            return;

        // If we have an off hand item, then disable our secondary functions
        boolean secondaryFunctionsEnabled = true;

        // Update off hand cycling. Controlled by the main gun, since it is always around.
        if(!isOffHand && type.oneHanded)
        {
            //Cycle selection
            int dWheel = Mouse.getDWheel();
            if( Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.getKeyCode()) && dWheel != 0)
            {
                data.cycleOffHandItem(player, dWheel);
            }
        }

        if(type.usableByPlayers)
        {
            boolean needsToReload = needsToReload(gunstack);
            boolean shouldShootThisTick = false;
            switch(type.getFireMode(gunstack))
            {
                case BURST:
                {
                    if(data.GetBurstRoundsRemaining(isOffHand) > 0)
                    {
                        shouldShootThisTick = true;
                    }
                    // Fallthrough to semi auto
                }
                case SEMI_AUTO:
                {
                    if(GetMouseHeld(isOffHand) && !GetLastMouseHeld(isOffHand))
                    {
                        shouldShootThisTick = true;
                    }
                    else needsToReload = false;
                    break;
                }
                case FULL_AUTO:
                {
                    shouldShootThisTick = GetMouseHeld(isOffHand);
                    if(!shouldShootThisTick)
                    {
                        needsToReload = false;
                    }
                    break;
                }
                default:
                    needsToReload = false;
                    break;
            }

            // Do reload if we pressed fire.
            if(needsToReload)
            {
                if(Reload(gunstack, world, player, player.inventory, isOffHand, hasOffHand, false, player.capabilities.isCreativeMode))
                {
                    //Set player shoot delay to be the reload delay
                    //Set both gun delays to avoid reloading two guns at once
                    data.shootTimeRight = data.shootTimeLeft = (int)type.getReloadTime(gunstack);

                    GunAnimations animations = ClientSideUtils.getGunAnimations(player, isOffHand);

                    int pumpDelay = type.model == null ? 0 : type.model.pumpDelayAfterReload;
                    int pumpTime = type.model == null ? 1 : type.model.pumpTime;
                    animations.doReload(type.reloadTime, pumpDelay, pumpTime);

                    if(isOffHand)
                    {
                        data.reloadingLeft = true;
                        data.burstRoundsRemainingLeft = 0;
                    }
                    else
                    {
                        data.reloadingRight = true;
                        data.burstRoundsRemainingRight = 0;
                    }
                    //Send reload packet to server
                    FlansMod.getPacketHandler().sendToServer(new PacketReload(isOffHand, false));
                }
            }
            // Fire!
            else if(shouldShootThisTick)
            {
                float shootTime = data.GetShootTime(isOffHand);

                // For each
                while(shootTime <= 0.0f)
                {
                    // Add the delay for this shot and shoot it!
                    shootTime += type.delayBetweenShots;

                    ItemStack shootableStack = getBestNonEmptyShootableStack(gunstack);
                    ItemShootable shootableItem = (ItemShootable)shootableStack.getItem();
                    ShootableType shootableType = shootableItem.type;
                    // Instant bullets. Do a raytrace
                    if(type.bulletSpeed == 0.0f)
                    {
                        for(int i = 0; i < type.numBullets * shootableType.numBullets; i++)
                        {
                            Vector3f rayTraceOrigin = new Vector3f((float)player.getPositionEyes(0.0f).xCoord, (float)player.getPositionEyes(0.0f).yCoord, (float)player.getPositionEyes(0.0f).zCoord);
                            Vector3f rayTraceDirection = new Vector3f((float)player.getLookVec().xCoord, (float)player.getLookVec().yCoord, (float)player.getLookVec().zCoord);

                            float spread = 0.005f * type.getSpread(gunstack) * shootableType.bulletSpread;

                            rayTraceDirection.x += (float)world.rand.nextGaussian() * spread;
                            rayTraceDirection.y += (float)world.rand.nextGaussian() * spread;
                            rayTraceDirection.z += (float)world.rand.nextGaussian() * spread;

                            rayTraceDirection.scale(500.0f);

                            List<Raytracer.BulletHit> hits = Raytracer.Raytrace(world, player, false, null, rayTraceOrigin, rayTraceDirection, 0);
                            Entity victim = null;
                            Vector3f hitPos = Vector3f.add(rayTraceOrigin, rayTraceDirection, null);
                            Raytracer.BulletHit firstHit = null;
                            if(!hits.isEmpty())
                            {
                                firstHit = hits.get(0);
                                hitPos = Vector3f.add(rayTraceOrigin, (Vector3f)rayTraceDirection.scale(firstHit.intersectTime), null);
                                victim = firstHit.GetEntity();
                            }

                            Vector3f gunOrigin = Raytracer.GetPlayerMuzzlePosition(player, isOffHand);

//                            if(FlansMod.DEBUG)
//                            {
//                                world.spawnEntityInWorld(new EntityDebugDot(world, gunOrigin, 100, 1.0f, 1.0f, 1.0f));
//                            }

                            ShotData shotData = new ShotData.InstantShotData(gunSlot, type, shootableType, player, gunOrigin, firstHit, hitPos, type.getDamage(gunstack), i < type.numBullets * shootableType.numBullets - 1);
                            shotsFiredClient.add(shotData);
                        }
                    }
                    // Else, spawn an entity
                    else
                    {
                        ShotData shotData = new ShotData.SpawnEntityShotData(gunSlot, type, shootableType, player, new Vector3f((float)player.getLookVec().xCoord, (float)player.getLookVec().yCoord, (float)player.getLookVec().zCoord));
                        shotsFiredClient.add(shotData);
                    }

                    // Now do client side things
                    GunAnimations animations = ClientSideUtils.getGunAnimations(player, isOffHand);

                    int pumpDelay = type.model == null ? 0 : type.model.pumpDelay;
                    int pumpTime = type.model == null ? 1 : type.model.pumpTime;
                    animations.doShoot(pumpDelay, pumpTime);
                    ClientSideUtils.playerRecoil += type.getRecoil(gunstack);

                    // Update burst fire
                    if(type.getFireMode(gunstack) == EnumFireMode.BURST)
                    {
                        int burstRoundsRemaining = data.GetBurstRoundsRemaining(isOffHand);

                        if(burstRoundsRemaining > 0)
                            burstRoundsRemaining--;
                        else burstRoundsRemaining = type.roundPerBurst;

                        data.SetBurstRoundsRemaining(isOffHand, burstRoundsRemaining);
                    }
                }

                data.SetShootTime(isOffHand, shootTime);
            }

            Vector3f gunOrigin = Raytracer.GetPlayerMuzzlePosition(player, isOffHand);

//            if(FlansMod.DEBUG)
//            {
//                world.spawnEntity(new EntityDebugDot(world, gunOrigin, 100, 1.0f, 1.0f, 1.0f));
//            }

            // Now send shooting data to the server
            if(!shotsFiredClient.isEmpty() && player.ticksExisted % CLIENT_TO_SERVER_UPDATE_INTERVAL == 0)
            {
                FlansMod.getPacketHandler().sendToServer(new PacketShotData(shotsFiredClient));
                shotsFiredClient.clear();
            }

            // Check for scoping in / out
            IScope currentScope = type.getScope(gunstack);
            if(!isOffHand && !hasOffHand && leftMouseHeld && !lastLeftMouseHeld
                    && (type.secondaryFunction == EnumSecondaryFunction.ADS_ZOOM || type.secondaryFunction == EnumSecondaryFunction.ZOOM) )
            {
                ClientSideUtils.SetScope(currentScope);
            }
        }

        // And finally do sounds
//        if (soundDelay > 0)
//        {
//            soundDelay--;
//        }
    }

    public void ServerHandleShotData(ItemStack gunstack, int gunSlot, World world, Entity entity, boolean isOffHand, ShotData shotData)
    {
        // Get useful things
        if(!(entity instanceof EntityPlayerMP))
        {
            return;
        }
        EntityPlayerMP player = (EntityPlayerMP)entity;
        PlayerData data = PlayerEventHandler.getPlayerData(player, Side.SERVER);
        if(data == null)
        {
            return;
        }


        boolean isExtraBullet = shotData instanceof ShotData.InstantShotData ? ((ShotData.InstantShotData)shotData).isExtraBullet : false;

        //Go through the bullet stacks in the gun and see if any of them are not null
        int bulletID = 0;
        ItemStack bulletStack = null;
        for(; bulletID < type.ammoItemsInGun; bulletID++)
        {
            ItemStack checkingStack = getBulletItemStack(gunstack, bulletID);
            if(checkingStack != null && checkingStack.getItem() != null && checkingStack.getItemDamage() < checkingStack.getMaxDamage())
            {
                bulletStack = checkingStack;
                break;
            }
        }

        // We have no bullet stack. So we need to reload. The player will send us a message requesting we do a reload
        if(bulletStack == null)
        {
            return;
        }

        if(bulletStack.getItem() instanceof ItemShootable)
        {
            ShootableType bullet = ((ItemShootable)bulletStack.getItem()).type;

            if(!isExtraBullet)
            {
                // Drop item on shooting if bullet requires it
                if(bullet.dropItemOnShoot != null && !player.capabilities.isCreativeMode)
                    dropItem(world, player, bullet.dropItemOnShoot);
                // Drop item on shooting if gun requires it
//                if(type.dropItemOnShoot != null) && !entityplayer.capabilities.isCreativeMode)
//                    dropItem(world, player, type.dropItemOnShoot);

                if(type.knockback > 0)
                {
                    //TODO : Apply knockback
                }

                //Damage the bullet item
                bulletStack.setItemDamage(bulletStack.getItemDamage() + 1);

                //Update the stack in the gun
                setBulletItemStack(gunstack, bulletStack, bulletID);

//                if(type.consumeGunUponUse && gunSlot != -1)
//                    player.inventory.setInventorySlotContents(gunSlot, null);
            }

            // Spawn an entity, classic style
            if(shotData instanceof ShotData.SpawnEntityShotData )
            {
                // Play a sound if the previous sound has finished
//                if (soundDelay <= 0 && type.shootSound != null)
//                {
//                    AttachmentType barrel = type.getBarrel(gunstack);
//                    boolean silenced = barrel != null && barrel.silencer;
//                    //world.playSoundAtEntity(entityplayer, type.shootSound, 10F, type.distortSound ? 1.0F / (world.rand.nextFloat() * 0.4F + 0.8F) : 1.0F);
//                    PacketPlaySound.sendSoundPacket(player.posX, player.posY, player.posZ, FlansMod.soundRange, player.dimension, type.shootSound, type.distortSound, silenced);
//                    soundDelay = type.shootSoundLength;
//                }

                //Shoot
                // Spawn the bullet entities
                for (int k = 0; k < type.numBullets * bullet.numBullets; k++)
                {
                    // Actually shoot the bullet
                    ((ItemShootable)bulletStack.getItem()).Shoot(world,
                            new Vector3f((float)player.posX, (float)player.posY + player.getEyeHeight(), (float)player.posZ),
                            new Vec3d(player.getLookVec().xCoord, player.getLookVec().yCoord, player.getLookVec().zCoord),
                            type.getDamage(gunstack),
                            (player.isSneaking() ? 0.7F : 1F) * type.getSpread(gunstack) * bullet.bulletSpread,
                            type.getBulletSpeed(gunstack),
                            type,
                            player);
                }
            }
            // Do a raytrace check on what they've sent us.
            else if(shotData instanceof ShotData.InstantShotData )
            {
                ShotData.InstantShotData instantData = (ShotData.InstantShotData) shotData;
                //if(stuff)
                //{
                //	calculate our own raytrace to verify they're not cheating
                //}
                // else
                {
                    // Take a point halfway along. Then make the radius encapsulate both ends and then some
                    Vector3f targetPoint = Vector3f.add(instantData.origin, instantData.hitPos, null);
                    targetPoint.scale(0.5f);
                    float radius = Vector3f.sub(instantData.origin, instantData.hitPos, null).length();
                    radius += 50.0f;

                    DoInstantShot(world, player, type, (BulletType)bullet, instantData.origin, instantData.hitPos, instantData.hitData, type.getDamage(gunstack));

                    shotsFiredServer.add(shotData);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    private void PlayShotSound(World world, boolean silenced, float x, float y, float z)
    {
        FMLClientHandler.instance().getClient().getSoundHandler().playSound(
                new PositionedSoundRecord(FlansModResourceHandler.getSound(type.shootSound),
                        silenced ? 5F : 10F,
                        (type.distortSound ? 1.0F / (world.rand.nextFloat() * 0.4F + 0.8F) : 1.0F) * (silenced ? 2F : 1F),
                        x, y, z));
    }

    public void DoInstantShot(World world, Entity shooter, InfoType shotFrom, BulletType shotType, Vector3f origin, Vector3f hit, Raytracer.BulletHit hitData, float damage)
    {
        if( EntityBullet.OnHit(world, origin, hit, shooter, shotFrom, shotType, null, damage, hitData))
        {
            EntityBullet.OnDetonate(world, hit, shooter, null, shotFrom, shotType);
        }

        if(world.isRemote)
        {
            // Play a sound if the previous sound has finished
            if (soundDelay <= 0 && type.shootSound != null)
            {
                //AttachmentType barrel = type.getBarrel(gunstack);
                boolean silenced = false;//barrel != null && barrel.silencer;

                PlayShotSound(world, silenced, (float)shooter.posX, (float)shooter.posY, (float)shooter.posZ);

                soundDelay = type.shootSoundLength;
            }

//            if(FlansMod.DEBUG)
//            {
//                world.spawnEntityInWorld(new EntityDebugVector(world, origin, Vector3f.sub(hit, origin, null), 100, 0.5f, 0.5f, 1.0f));
//            }

            InstantBulletRenderer.AddTrail(new InstantBulletRenderer.InstantShotTrail(origin, hit, (BulletType)shotType));

            if(hitData instanceof Raytracer.BlockHit )
            {
                Raytracer.BlockHit blockHit = (Raytracer.BlockHit)hitData;

                BlockPos blockPos = blockHit.raytraceResult.getBlockPos();
                IBlockState blockState = world.getBlockState(blockHit.raytraceResult.getBlockPos());

                Vec3i normal = blockHit.raytraceResult.sideHit.getDirectionVec();

                if(blockState != null)
                {
                    for(int i = 0; i < 2; i++)
                    {
                        Particle fx = Minecraft.getMinecraft().effectRenderer.spawnEffectParticle(
                                EnumParticleTypes.BLOCK_CRACK.getParticleID(), hit.x, hit.y, hit.z, 0.0f, 0.0f, 0.0f,
                                Block.getIdFromBlock(blockState.getBlock()));

                        double scale = world.rand.nextGaussian() * 0.1d + 0.5d;

                        fx.motionX = (double)normal.getX() * scale + world.rand.nextGaussian() * 0.025d;
                        fx.motionY = (double)normal.getY() * scale + world.rand.nextGaussian() * 0.025d;
                        fx.motionZ = (double)normal.getZ() * scale + world.rand.nextGaussian() * 0.025d;

                        if(Minecraft.getMinecraft().gameSettings.fancyGraphics)
                            fx.renderDistanceWeight = 100D;
                    }
                }
            }

            if(world.isRemote)
            {
                if(shooter == Minecraft.getMinecraft().player)
                {
                    if(hitData instanceof Raytracer.EntityHit || hitData instanceof Raytracer.PlayerBulletHit || hitData instanceof Raytracer.DriveableHit )
                    {
                        // Add a hit marker
                        ClientSideUtils.AddHitMarker();
                    }
                }
            }
        }
        else
        {

        }
    }

    public void onUpdateServer(ItemStack itemstack, int gunSlot, World world, Entity entity, boolean isOffHand, boolean hasOffHand)
    {
        if(!(entity instanceof EntityPlayerMP))
        {
            return;
        }
        EntityPlayerMP player = (EntityPlayerMP)entity;
        PlayerData data = PlayerEventHandler.getPlayerData(player);
        if(data == null)
            return;

        if(player.inventory.getCurrentItem() != itemstack)
        {
            //If the player is no longer holding a gun, emulate a release of the shoot button
            if(player.inventory.getCurrentItem() == ItemStack.EMPTY || player.inventory.getCurrentItem().getItem() == null || !(player.inventory.getCurrentItem().getItem() instanceof ItemISRange))
            {
                data.isShootingRight = data.isShootingLeft = false;
                data.offHandGunSlot = 0;
                (new PacketSelectOffHandGun(0)).handleServerSide(player);
            }
            return;
        }

        if(!shotsFiredServer.isEmpty())// && entity.ticksExisted % SERVER_TO_CLIENT_UPDATE_INTERVAL == 0)
        {
            FlansMod.getPacketHandler().sendToDimension(new PacketShotData(shotsFiredServer), player.dimension );
            shotsFiredServer.clear();
        }
    }

    /** Generic update method. If we have an off hand weapon, it will also make calls for that
     *  Passes on to onUpdateEach */
    @Override
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag)
    {
        if(entity instanceof EntityPlayer && ((EntityPlayer)entity).inventory.getCurrentItem() == itemstack)
        {
            if(world.isRemote)
            {
                // Get button presses. Do this before splitting into each hand. Prevents second pass wiping the data
                lastRightMouseHeld = rightMouseHeld;
                lastLeftMouseHeld = leftMouseHeld;
                rightMouseHeld = Mouse.isButtonDown(1);
                leftMouseHeld = Mouse.isButtonDown(0);
            }

            boolean hasOffHand = false;
            EntityPlayer player = (EntityPlayer)entity;
            PlayerData data = PlayerEventHandler.getPlayerData(player, Side.CLIENT);

            if(type.oneHanded)
            {
                // If the offhand item is this item, select none
                if(data.offHandGunSlot == player.inventory.currentItem + 1)
                    data.offHandGunSlot = 0;

                if(data.offHandGunSlot != 0)
                {
                    hasOffHand = true;
                    ItemStack offHandGunStack = player.inventory.getStackInSlot(data.offHandGunSlot - 1);
                    if(offHandGunStack != ItemStack.EMPTY && offHandGunStack.getItem() instanceof ItemISRange)
                    {
                        EqualizerRangeType offHandGunType = ((ItemISRange)offHandGunStack.getItem()).type;
                        ((ItemISRange)offHandGunStack.getItem()).onUpdateEach(offHandGunStack, data.offHandGunSlot - 1, world, entity, true, hasOffHand);
                    }
                }
            }

            onUpdateEach(itemstack, player.inventory.currentItem, world, entity, false, hasOffHand);
        }
    }

    /** Called once for each weapon we are weilding */
    private void onUpdateEach(ItemStack itemstack, int gunSlot, World world, Entity entity, boolean isOffHand, boolean hasOffHand)
    {
        if(world.isRemote)
            onUpdateClient(itemstack, gunSlot, world, entity, isOffHand, hasOffHand);
        else onUpdateServer(itemstack, gunSlot, world, entity, isOffHand, hasOffHand);
    }

    public boolean Reload(ItemStack gunstack, World world, Entity entity, IInventory inventory, boolean isOffHand, boolean hasOffHand, boolean forceReload, boolean isCreative)
    {
        //Deployable guns cannot be reloaded in the inventory
        if(type.deployable)
            return false;
        //If you cannot reload half way through a clip, reject the player for trying to do so
        if(forceReload && !type.canForceReload)
            return false;

        //For playing sounds afterwards
        boolean reloadedSomething = false;
        //Check each ammo slot, one at a time
        for(int i = 0; i < type.ammoItemsInGun; i++)
        {
            //Get the stack in the slot
            ItemStack bulletStack = getBulletItemStack(gunstack, i);

            //If there is no magazine, if the magazine is empty or if this is a forced reload
            if(bulletStack == null || bulletStack.getItemDamage() == bulletStack.getMaxDamage() || forceReload)
            {
                //Iterate over all inventory slots and find the magazine / bullet item with the most bullets
                int bestSlot = -1;
                int bulletsInBestSlot = 0;
                for (int j = 0; j < inventory.getSizeInventory(); j++)
                {
                    ItemStack item = inventory.getStackInSlot(j);
                    if (item != null && item.getItem() instanceof ItemShootable && type.isAmmo(((ItemShootable)(item.getItem())).type))
                    {
                        int bulletsInThisSlot = item.getMaxDamage() - item.getItemDamage();
                        if(bulletsInThisSlot > bulletsInBestSlot)
                        {
                            bestSlot = j;
                            bulletsInBestSlot = bulletsInThisSlot;
                        }
                    }
                }
                //If there was a valid non-empty magazine / bullet item somewhere in the inventory, load it
                if(bestSlot != -1)
                {
                    ItemStack newBulletStack = inventory.getStackInSlot(bestSlot);
                    ShootableType newBulletType = ((ItemShootable)newBulletStack.getItem()).type;

                    //Unload the old magazine (Drop an item if it is required and the player is not in creative mode)
                    if(bulletStack != null && bulletStack.getItem() instanceof ItemShootable && ((ItemShootable)bulletStack.getItem()).type.dropItemOnReload != null && !isCreative && bulletStack.getItemDamage() == bulletStack.getMaxDamage())
                    {
                        if(!world.isRemote)
                            dropItem(world, entity, ((ItemShootable)bulletStack.getItem()).type.dropItemOnReload);
                    }

                    //The magazine was not finished, pull it out and give it back to the player or, failing that, drop it
                    if(bulletStack != null && bulletStack.getItemDamage() < bulletStack.getMaxDamage())
                    {
                        if(! InventoryHelper.addItemStackToInventory(inventory, bulletStack, isCreative))
                        {
                            if(!world.isRemote)
                                entity.entityDropItem(bulletStack, 0.5F);
                        }
                    }

                    //Load the new magazine
                    ItemStack stackToLoad = newBulletStack.copy();
                    stackToLoad.setCount(1);
                    setBulletItemStack(gunstack, stackToLoad, i);

                    //Remove the magazine from the inventory
                    if(!isCreative)
                        newBulletStack.setCount(newBulletStack.getCount() - 1);
                    if(newBulletStack.getCount() <= 0)
                        newBulletStack = null;
                    inventory.setInventorySlotContents(bestSlot, newBulletStack);


                    //Tell the sound player that we reloaded something
                    reloadedSomething = true;
                }
            }
        }
        return reloadedSomething;
    }

    private boolean needsToReload(ItemStack stack)
    {
        for(int i = 0; i < type.ammoItemsInGun; i++)
        {
            ItemStack bulletStack = getBulletItemStack(stack, i);
            if(bulletStack != null && bulletStack.getItem() != null && bulletStack.getItemDamage() < bulletStack.getMaxDamage())
            {
                return false;
            }
        }
        return true;
    }

    public boolean CanReload(ItemStack gunstack, IInventory inventory)
    {
        for(int i = 0; i < inventory.getSizeInventory(); i++)
        {
            ItemStack stack = inventory.getStackInSlot(i);
            if(type.isAmmo(stack))
            {
                return true;
            }
        }
        return false;
    }

    private ItemStack getBestNonEmptyShootableStack(ItemStack stack)
    {
        for(int i = 0; i < type.ammoItemsInGun; i++)
        {
            ItemStack shootableStack = getBulletItemStack(stack, i);
            if(shootableStack != null && shootableStack.getItem() != null && shootableStack.getItemDamage() < shootableStack.getMaxDamage())
            {
                return shootableStack;
            }
        }
        return null;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List lines, boolean advancedTooltips)
    {
        if(type.description != null)
        {
            Collections.addAll(lines, type.description.split("_"));
        }
        if(type.showDamage)
            lines.add("\u00a79Damage" + "\u00a77: " + type.getDamage(stack));
//        if(type.showRecoil)
//            lines.add("\u00a79Recoil" + "\u00a77: " + type.getRecoil(stack));
        if(type.showSpread)
            lines.add("\u00a79Accuracy" + "\u00a77: " + type.getSpread(stack));
        if(type.showReloadTimes)
            lines.add("\u00a79Reload Time" + "\u00a77: " + type.getReloadTime(stack) / 20 + "s");
        for(AttachmentType attachment : type.getCurrentAttachments(stack))
        {
            if(type.showAttachments)
            {
                String line = attachment.name;
                lines.add(line);
            }
        }
        for(int i = 0; i < type.ammoItemsInGun; i++)
        {
            ItemStack bulletStack = getBulletItemStack(stack, i);
            if(bulletStack != null && bulletStack.getItem() instanceof ItemBullet)
            {
                BulletType bulletType = ((ItemBullet)bulletStack.getItem()).type;
                //String line = bulletType.name + (bulletStack.getMaxDamage() == 1 ? "" : " " + (bulletStack.getMaxDamage() - bulletStack.getItemDamage()) + "/" + bulletStack.getMaxDamage());
                String line = bulletType.name + " " + (bulletStack.getMaxDamage() - bulletStack.getItemDamage()) + "/" + bulletStack.getMaxDamage();
                lines.add(line);
            }
        }
    }

    @Override
    /** Make sure client and server side NBTtags update */
    public boolean getShareTag()
    {
        return true;
    }

    public DamageSource getMeleeDamage(EntityPlayer attacker)
    {
        return new EntityDamageSourceGun(type.shortName, attacker, attacker, type, false);
    }

    //Stop damage being done to entities when scoping etc.
    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        return type.secondaryFunction != EnumSecondaryFunction.MELEE;
    }

    @Override
    public boolean isFull3D()
    {
        return true;
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
        if (type.meleeSound != null)
            PacketPlaySound.sendSoundPacket(entityLiving.posX, entityLiving.posY, entityLiving.posZ, FlansMod.soundRange, entityLiving.dimension, type.meleeSound, true);
        //Do custom melee code here
        if(type.secondaryFunction == EnumSecondaryFunction.CUSTOM_MELEE)
        {
            //Do animation
            if(entityLiving.world.isRemote)
            {
                GunAnimations animations = ClientSideUtils.getGunAnimations(entityLiving, false);
                animations.doMelee(type.meleeTime);
            }
            //Do custom melee hit detection
            if(entityLiving instanceof EntityPlayer)
            {
                PlayerData data = PlayerEventHandler.getPlayerData((EntityPlayer)entityLiving);
                data.doMelee((EntityPlayer)entityLiving, type.meleeTime, type);
            }
        }
        return type.secondaryFunction != EnumSecondaryFunction.MELEE;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player)
    {
        return true;
    }

    public boolean isItemStackDamageable()
    {
        return false;
    }

}
