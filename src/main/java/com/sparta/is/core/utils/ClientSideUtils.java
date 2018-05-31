package com.sparta.is.core.utils;

import com.sparta.is.client.gui.component.GuiDriveableController;
import com.sparta.is.client.render.model.GunAnimations;
import com.sparta.is.core.utils.helpers.LogHelper;
import com.sparta.is.entity.driveables.IControllable;
import com.sparta.is.entity.driveables.types.IScope;
import com.sparta.is.entity.driveables.types.InfoType;
import com.sparta.is.core.item.ItemISRange;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;

public class ClientSideUtils
{
    /** Whether the player is in mouse control mode */
    public static boolean controlModeMouse = true;
    /** A delayer on the mouse control switch */
    public static int controlModeSwitchTimer = 20;

    private static Minecraft minecraft = Minecraft.getMinecraft();
    /** The recoil applied to the player view by shooting */
    public static float playerRecoil;
    /** The amount of compensation to apply to the recoil in order to bring it back to normal */
    public static float antiRecoil;

    //Scope variables
    /** A delayer on the scope button to avoid repeat presses */
    public static int scopeTime;
    /** The scope that is currently being looked down */
    public static IScope currentScope = null;
    /** The transition variable for zooming in / out with a smoother. 0 = unscoped, 1 = scoped */
    public static float zoomProgress = 0F, lastZoomProgress = 0F;
    /** The zoom level of the last scope used, for transitioning out of being scoped, even after the scope is forgotten */
    public static float lastZoomLevel = 1F, lastFOVZoomLevel = 1F;

    public static float originalMouseSensitivity = 0.5F;
    /** The player's original FOV */
    public static float originalFOV = 90F;
    /** The original third person mode, before being hacked */
    public static int originalThirdPerson = 0;

    public static int hitMarkerTime = 0;

    public static HashMap<EntityLivingBase, GunAnimations> gunAnimationsRight = new HashMap<EntityLivingBase, GunAnimations>(), gunAnimationsLeft = new HashMap<EntityLivingBase, GunAnimations>();

    public static void tick()
    {
        if (minecraft.player == null || minecraft.world == null)
            return;

        if(minecraft.player.getRidingEntity() instanceof IControllable && minecraft.currentScreen == null)
            minecraft.displayGuiScreen(new GuiDriveableController((IControllable)minecraft.player.getRidingEntity()));

        // Guns
        if(scopeTime > 0)
            scopeTime--;
        if (playerRecoil > 0)
            playerRecoil *= 0.8F;
        if(hitMarkerTime > 0)
            hitMarkerTime--;
        minecraft.player.rotationPitch -= playerRecoil;
        antiRecoil += playerRecoil;

        minecraft.player.rotationPitch += antiRecoil * 0.2F;
        antiRecoil *= 0.8F;

        //Update gun animations for the gun in hand
        for(GunAnimations g : gunAnimationsRight.values())
        {
            g.update();
        }
        for(GunAnimations g : gunAnimationsLeft.values())
        {
            g.update();
        }

        for(Object obj : minecraft.world.playerEntities)
        {
            EntityPlayer player = (EntityPlayer)obj;
            ItemStack currentItem = player.getActiveItemStack();
            if(currentItem != ItemStack.EMPTY && currentItem.getItem() instanceof ItemISRange)
            {
                if(player == minecraft.player && minecraft.gameSettings.thirdPersonView == 0)
                {
                    player.setHeldItem(player.getActiveHand(), ItemStack.EMPTY);
                }
                else
                {
                    player.setHeldItem(player.getActiveHand(), currentItem);
                }
            }
        }

        //If the currently held item is not a gun or is the wrong gun, unscope
        Item itemInHand = null;
        ItemStack itemstackInHand = minecraft.player.inventory.getCurrentItem();
        if (itemstackInHand != ItemStack.EMPTY)
            itemInHand = itemstackInHand.getItem();
        if (currentScope != null)
        {
            GameSettings gameSettings = FMLClientHandler.instance().getClient().gameSettings;

            // If we've opened a GUI page, or we switched weapons, close the current scope
            if(FMLClientHandler.instance().getClient().currentScreen != null
                    || itemInHand == null
                    || !(itemInHand instanceof ItemISRange)
                    || ((ItemISRange)itemInHand).getType().getCurrentScope(itemstackInHand) != currentScope)
            {
                currentScope = null;
                minecraft.gameSettings.fovSetting = originalFOV;
                minecraft.gameSettings.mouseSensitivity = originalMouseSensitivity;
                minecraft.gameSettings.thirdPersonView = originalThirdPerson;
            }
        }

        //Calculate new zoom variables
        lastZoomProgress = zoomProgress;
        if(currentScope == null)
        {
            zoomProgress *= 0.66F;
        }
        else
        {
            zoomProgress = 1F - (1F - zoomProgress) * 0.66F;
        }

        if (minecraft.player.getRidingEntity() instanceof IControllable)
        {
//            inPlane = true;
            try
            {
                ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, minecraft.entityRenderer, ((IControllable)minecraft.player.getRidingEntity()).getCameraDistance(), "thirdPersonDistance", "q", "field_78490_B");
            } catch (Exception e)
            {
                LogHelper.info(LogHelper.MOD_MARKER, "I forgot to update obfuscated reflection D:");
                throw new RuntimeException(e);
            }
        }
//        else if(inPlane)
//        {
//            try
//            {
//                ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, minecraft.entityRenderer, 4.0F, "thirdPersonDistance", "q", "field_78490_B");
//            } catch (Exception e)
//            {
//                log("I forgot to update obfuscated reflection D:");
//                throw new RuntimeException(e);
//            }
//            inPlane = false;
//        }
        if (controlModeSwitchTimer > 0)
            controlModeSwitchTimer--;
    }

    public static void SetScope(IScope scope)
    {
        GameSettings gameSettings = FMLClientHandler.instance().getClient().gameSettings;

        if(scopeTime <= 0 && FMLClientHandler.instance().getClient().currentScreen == null)
        {
            if(currentScope == null)
            {
                currentScope = scope;
                lastZoomLevel = scope.getZoomFactor();
                lastFOVZoomLevel = scope.getFOVFactor();
                float f = originalMouseSensitivity = gameSettings.mouseSensitivity;
                gameSettings.mouseSensitivity = f / (float) Math.sqrt(scope.getZoomFactor());
                originalThirdPerson = gameSettings.thirdPersonView;
                gameSettings.thirdPersonView = 0;
                originalFOV = gameSettings.fovSetting;
            }
            else
            {
                currentScope = null;
                gameSettings.mouseSensitivity = originalMouseSensitivity;
                gameSettings.thirdPersonView = originalThirdPerson;
                gameSettings.fovSetting = originalFOV;
            }
            scopeTime = 10;
        }
    }

    @SideOnly(Side.CLIENT)
    public static Particle getParticle(String s, World w, double x, double y, double z)
    {
        Minecraft mc = Minecraft.getMinecraft();
        //return mc.renderGlobal.doSpawnParticle(s, x, y, z, 0.01D, 0.01D, 0.01D);

        int particleID = 0;
        int[] data = new int[0];

        if(s.equals("hugeexplosion")) 		particleID = EnumParticleTypes.EXPLOSION_HUGE.getParticleID();
        else if(s.equals("largeexplode"))	particleID = EnumParticleTypes.EXPLOSION_LARGE.getParticleID();
        else if(s.equals("explode"))		particleID = EnumParticleTypes.EXPLOSION_NORMAL.getParticleID();
        else if(s.equals("fireworksSpark"))	particleID = EnumParticleTypes.FIREWORKS_SPARK.getParticleID();
        else if(s.equals("bubble"))			particleID = EnumParticleTypes.WATER_BUBBLE.getParticleID();
        else if(s.equals("splash"))			particleID = EnumParticleTypes.WATER_SPLASH.getParticleID();
        else if(s.equals("wake"))			particleID = EnumParticleTypes.WATER_WAKE.getParticleID();
        else if(s.equals("drop"))			particleID = EnumParticleTypes.WATER_DROP.getParticleID();
        else if(s.equals("suspended"))		particleID = EnumParticleTypes.SUSPENDED.getParticleID();
        else if(s.equals("depthsuspend"))	particleID = EnumParticleTypes.SUSPENDED_DEPTH.getParticleID();
        else if(s.equals("townaura"))		particleID = EnumParticleTypes.TOWN_AURA.getParticleID();
        else if(s.equals("crit"))			particleID = EnumParticleTypes.CRIT.getParticleID();
        else if(s.equals("magicCrit"))		particleID = EnumParticleTypes.CRIT_MAGIC.getParticleID();
        else if(s.equals("smoke"))			particleID = EnumParticleTypes.SMOKE_NORMAL.getParticleID();
        else if(s.equals("largesmoke"))		particleID = EnumParticleTypes.SMOKE_LARGE.getParticleID();
        else if(s.equals("spell"))			particleID = EnumParticleTypes.SPELL.getParticleID();
        else if(s.equals("instantSpell"))	particleID = EnumParticleTypes.SPELL_INSTANT.getParticleID();
        else if(s.equals("mobSpell"))		particleID = EnumParticleTypes.SPELL_MOB.getParticleID();
        else if(s.equals("mobSpellAmbient"))particleID = EnumParticleTypes.SPELL_MOB_AMBIENT.getParticleID();
        else if(s.equals("witchMagic"))		particleID = EnumParticleTypes.SPELL_WITCH.getParticleID();
        else if(s.equals("dripWater"))		particleID = EnumParticleTypes.DRIP_WATER.getParticleID();
        else if(s.equals("dripLava"))		particleID = EnumParticleTypes.DRIP_LAVA.getParticleID();
        else if(s.equals("angryVillager"))	particleID = EnumParticleTypes.VILLAGER_ANGRY.getParticleID();
        else if(s.equals("happyVillager"))	particleID = EnumParticleTypes.VILLAGER_HAPPY.getParticleID();
        else if(s.equals("note"))			particleID = EnumParticleTypes.NOTE.getParticleID();
        else if(s.equals("portal"))			particleID = EnumParticleTypes.PORTAL.getParticleID();
        else if(s.equals("enchantmenttable"))particleID = EnumParticleTypes.ENCHANTMENT_TABLE.getParticleID();
        else if(s.equals("flame"))			particleID = EnumParticleTypes.FLAME.getParticleID();
        else if(s.equals("lava"))			particleID = EnumParticleTypes.LAVA.getParticleID();
        else if(s.equals("footstep"))		particleID = EnumParticleTypes.FOOTSTEP.getParticleID();
        else if(s.equals("cloud"))			particleID = EnumParticleTypes.CLOUD.getParticleID();
        else if(s.equals("reddust"))		particleID = EnumParticleTypes.REDSTONE.getParticleID();
        else if(s.equals("snowballpoof"))	particleID = EnumParticleTypes.SNOWBALL.getParticleID();
        else if(s.equals("snowshovel"))		particleID = EnumParticleTypes.SNOW_SHOVEL.getParticleID();
        else if(s.equals("slime"))			particleID = EnumParticleTypes.SLIME.getParticleID();
        else if(s.equals("heart"))			particleID = EnumParticleTypes.HEART.getParticleID();
        else if(s.equals("barrier"))		particleID = EnumParticleTypes.BARRIER.getParticleID();
        else if(s.contains("_"))
        {
            int k;
            String[] split = s.split("_", 3);



            if(split[0].equals("iconcrack"))
            {
                data = new int[] { Item.getIdFromItem(InfoType.getRecipeElement(split[1], 0).getItem()) };
                particleID = EnumParticleTypes.ITEM_CRACK.getParticleID();
            }
            else
            {
                data = new int[] { Block.getIdFromBlock(Block.getBlockFromItem(InfoType.getRecipeElement(split[1], 0).getItem())) };

                if(split[0].equals("blockcrack"))
                {
                    particleID = EnumParticleTypes.BLOCK_CRACK.getParticleID();
                }
                else if(split[0].equals("blockdust"))
                {
                    particleID = EnumParticleTypes.BLOCK_DUST.getParticleID();
                }
            }
        }


        Particle fx = mc.effectRenderer.spawnEffectParticle(particleID, x, y, z, 0D, 0D, 0D, data);

//        if(mc.gameSettings.fancyGraphics)
//            fx.renderDistanceWeight = 200D;

        return fx;
    }

    public static GunAnimations getGunAnimations(EntityLivingBase living, boolean offHand)
    {
        GunAnimations animations = null;
        if(offHand)
        {
            if(ClientSideUtils.gunAnimationsLeft.containsKey(living))
                animations = ClientSideUtils.gunAnimationsLeft.get(living);
            else
            {
                animations = new GunAnimations();
                ClientSideUtils.gunAnimationsLeft.put(living, animations);
            }
        }
        else
        {
            if(ClientSideUtils.gunAnimationsRight.containsKey(living))
                animations = ClientSideUtils.gunAnimationsRight.get(living);
            else
            {
                animations = new GunAnimations();
                ClientSideUtils.gunAnimationsRight.put(living, animations);
            }
        }
        return animations;
    }

    public static void AddHitMarker()
    {
        hitMarkerTime = 20;
    }
}
