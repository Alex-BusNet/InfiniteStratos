package com.sparta.is.entity;

import com.sparta.is.entity.driveables.types.InfoType;
import com.sparta.is.handler.PlayerEventHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class EntityDamageSourceGun extends EntityDamageSourceIndirect
{
    public InfoType weapon;
    public EntityPlayer shooter;
    public boolean headshot;

    public EntityDamageSourceGun(String s, Entity entity, EntityPlayer player, InfoType wep, boolean head)
    {
        super(s, entity, player);
        weapon = wep;
        shooter = player;
        headshot = head;
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase living)
    {
        if(!(living instanceof EntityPlayer) || shooter == null || PlayerEventHandler.getPlayerData(shooter) == null)
        {
            return super.getDeathMessage(living);
        }
        EntityPlayer player = (EntityPlayer)living;


//        FlansMod.getPacketHandler().sendToDimension(new PacketKillMessage(headshot, weapon, (killedTeam == null ? "f" : killedTeam.textColour) + player.getName(),  (killerTeam == null ? "f" : killerTeam.textColour) + shooter.getName()), living.dimension);

        return new TextComponentTranslation("#flansmod");//flanDeath." + weapon.shortName + "." + (killedTeam == null ? "f" : killedTeam.textColour) + player.getCommandSenderName() + "." + (killerTeam == null ? "f" : killerTeam.textColour) + shooter.getCommandSenderName());
    }
}
