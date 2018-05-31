package com.sparta.is.core.handler;

import com.sparta.is.armor.raytracing.PlayerSnapshot;
import com.sparta.is.entity.driveables.RotatedAxes;
import com.sparta.is.entity.driveables.types.EqualizerMeleeType;
import com.sparta.is.entity.driveables.types.InfoType;
import com.sparta.is.core.item.ItemISRange;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.util.vector.Vector3f;

//import com.sparta.is.armor.raytracing.PlayerSnapshot;
//import com.sparta.is.entity.driveables.RotatedAxes;
//import com.sparta.is.entity.driveables.types.EqualizerMeleeType;
//import com.sparta.is.entity.driveables.types.IScope;
//import net.minecraft.client.settings.GameSettings;

public class PlayerData
{
    /** Their username */
    public String username;

    //Movement related fields
    /** Roll variables */
    public float prevRotationRoll, rotationRoll;
    /** Snapshots for bullet hit detection. Array size is set to number of snapshots required. When a new one is taken,
     * each snapshot is moved along one place and new one is added at the start, so that when the array fills up, the oldest one is lost */
    public PlayerSnapshot[] snapshots;

    //Gun related fields
    /** The slotID of the gun being used by the off-hand. 0 = no slot. 1 ~ 9 = hotbar slots */
    public int offHandGunSlot = 0;
    /** The off hand gun stack. For viewing other player's off hand weapons only (since you don't know what is in their inventory and hence just the ID is insufficient) */
    @SideOnly(Side.CLIENT)
    public ItemStack offHandGunStack;
    /** Stops player shooting immediately after swapping weapons */
    public int shootClickDelay;
    /** The speed of the minigun the player is using */
    public float minigunSpeed = 0F;
    /** Sound delay parameters */
    public int loopedSoundDelay;
    /** Sound delay parameters */
    public boolean shouldPlayCooldownSound, shouldPlayWarmupSound;
    /** Melee weapon custom hit simulation */
    public int meleeProgress, meleeLength;

    /** Tickers to stop shooting too fast */
    public float shootTimeRight, shootTimeLeft;
    /** True if this player is shooting */
    public boolean isShootingRight, isShootingLeft;
    /** Reloading booleans */
    public boolean reloadingRight, reloadingLeft;
    /** When the player shoots a burst fire weapon, one shot is fired immediately and this counter keeps track of how many more should be fired */
    public int burstRoundsRemainingLeft = 0, burstRoundsRemainingRight = 0;

    // Handed getters and setters
    public float GetShootTime(boolean bOffHand) { return bOffHand ? shootTimeLeft : shootTimeRight; }
    public void SetShootTime(boolean bOffHand, float set) { if(bOffHand) shootTimeLeft = set; else shootTimeRight = set; }

    public int GetBurstRoundsRemaining(boolean bOffHand) { return bOffHand ? burstRoundsRemainingLeft : burstRoundsRemainingRight; }
    public void SetBurstRoundsRemaining(boolean bOffHand, int set) { if(bOffHand) burstRoundsRemainingLeft = set; else burstRoundsRemainingRight = set; }

    public Vector3f[] lastMeleePositions;

    //Teams related fields
    /** Gametype variables */
    public int score, kills, deaths;
    /** Zombies variables */
    public int zombieScore;
    /** Gametype variable for Nerf */
    public boolean out;
    /** The player's vote for the next round from 1 ~ 5. 0 is not yet voted */
    public int vote;
    /** Keeps the player out of having to rechose their team each round */
    public boolean builder;
    /** Save the player's skin here, to replace after having done a swap for a certain class override */
    @SideOnly(Side.CLIENT)
    public ResourceLocation skin;

    public PlayerData(String name)
    {
        username = name;
        snapshots = new PlayerSnapshot[20];
    }

    public void tick(EntityPlayer player)
    {
        if(player.world.isRemote)
            clientTick(player);
        if(shootTimeRight > 0)
            shootTimeRight--;
        if(shootTimeRight == 0)
            reloadingRight = false;

        if(shootTimeLeft > 0)
            shootTimeLeft--;
        if(shootTimeLeft == 0)
            reloadingLeft = false;

        if(shootClickDelay > 0)
            shootClickDelay--;

        if(loopedSoundDelay > 0)
        {
            loopedSoundDelay--;
            if(loopedSoundDelay == 0 && !isShootingRight)
                shouldPlayCooldownSound = true;
        }

        //Move all snapshots along one place
        System.arraycopy(snapshots, 0, snapshots, 1, snapshots.length - 2 + 1);
        //Take new snapshot
        snapshots[0] = new PlayerSnapshot(player);
    }



    public void clientTick(EntityPlayer player)
    {
        if(player.getActiveItemStack() == ItemStack.EMPTY
                || !(player.getActiveItemStack().getItem() instanceof ItemISRange)
                || player.getActiveItemStack() == offHandGunStack)
        {
            offHandGunSlot = 0;
            offHandGunStack = null;
        }
    }

    public void playerKilled()
    {
        isShootingRight = isShootingLeft = false;
    }

    public void doMelee(EntityPlayer player, int meleeTime, InfoType iType)
    {
        EqualizerMeleeType type = (EqualizerMeleeType) iType;
        meleeLength = meleeTime;
        lastMeleePositions = new Vector3f[type.meleePath.size()];

        for(int k = 0; k < type.meleeDamagePoints.size(); k++)
        {
            Vector3f meleeDamagePoint = type.meleeDamagePoints.get(k);
            //Do a raytrace from the prev pos to the current pos and attack anything in the way
            Vector3f nextPos = type.meleePath.get(0);
            Vector3f nextAngles = type.meleePathAngles.get(0);
            RotatedAxes nextAxes = new RotatedAxes(-nextAngles.y, -nextAngles.z, nextAngles.x);

            Vector3f nextPosInPlayerCoords = new RotatedAxes(player.rotationYaw + 90F, player.rotationPitch, 0F).findLocalVectorGlobally(nextAxes.findLocalVectorGlobally(meleeDamagePoint));
            Vector3f.add(nextPos, nextPosInPlayerCoords, nextPosInPlayerCoords);

            if(player != FMLClientHandler.instance().getClient().player)
                nextPosInPlayerCoords.y += 1.6F;

            lastMeleePositions[k] = new Vector3f((float)player.posX + nextPosInPlayerCoords.x, (float)player.posY + nextPosInPlayerCoords.y, (float)player.posZ + nextPosInPlayerCoords.z);
        }
    }
}
