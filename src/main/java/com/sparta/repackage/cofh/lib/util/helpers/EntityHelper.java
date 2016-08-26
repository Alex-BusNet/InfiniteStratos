package com.sparta.repackage.cofh.lib.util.helpers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;

/**
 * This class contains various helper functions related to Entities.
 *
 * @author King Lemming
 *
 */
public class EntityHelper {

	private EntityHelper() {

	}

	public static int getEntityFacingCardinal(EntityLivingBase living) {

		int quadrant = com.sparta.repackage.cofh.lib.util.helpers.MathHelper.floor(living.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		switch (quadrant) {
		case 0:
			return 2;
		case 1:
			return 5;
		case 2:
			return 3;
		default:
			return 4;
		}
	}

	public static EnumFacing getEntityFacingForgeDirection(EntityLivingBase living) {

		return EnumFacing.VALUES[getEntityFacingCardinal(living)];
	}

//	public static void transferEntityToDimension(Entity entity, int dimension, ServerConfigurationManager manager) {
//
//		if (entity instanceof EntityPlayerMP) {
//			transferPlayerToDimension((EntityPlayerMP) entity, dimension, manager);
//			return;
//		}
//		WorldServer worldserver = entity.getServer().worldServerForDimension(entity.dimension);
//		entity.dimension = dimension;
//		WorldServer worldserver1 = entity.getServer().worldServerForDimension(entity.dimension);
//		worldserver.removeEntityDangerously(entity);
//		if (entity.isRidingOrBeingRiddenBy(entity)) {
//			entity.riddenByEntity.mountEntity(null);
//		}
//		if (entity.ridingEntity != null) {
//			entity.mountEntity(null);
//		}
//		entity.isDead = false;
//		transferEntityToWorld(entity, worldserver, worldserver1);
//	}

	public static void transferEntityToWorld(Entity entity, WorldServer oldWorld, WorldServer newWorld) {

		WorldProvider pOld = oldWorld.provider;
		WorldProvider pNew = newWorld.provider;
		double moveFactor = pOld.getMovementFactor() / pNew.getMovementFactor();
		double x = entity.posX * moveFactor;
		double z = entity.posZ * moveFactor;

		oldWorld.theProfiler.startSection("placing");
		x = MathHelper.clamp(x, -29999872, 29999872);
		z = MathHelper.clamp(z, -29999872, 29999872);

		if (entity.isEntityAlive()) {
			entity.setLocationAndAngles(x, entity.posY, z, entity.rotationYaw, entity.rotationPitch);
			newWorld.spawnEntityInWorld(entity);
			newWorld.updateEntityWithOptionalForce(entity, false);
		}

		oldWorld.theProfiler.endSection();

		entity.setWorld(newWorld);
	}

//	public static void transferPlayerToDimension(EntityPlayerMP player, int dimension, ServerConfigurationManager manager) {
//
//		int oldDim = player.dimension;
//		WorldServer worldserver = manager.getServerInstance().worldServerForDimension(player.dimension);
//		player.dimension = dimension;
//		WorldServer worldserver1 = manager.getServerInstance().worldServerForDimension(player.dimension);
//		player.playerNetServerHandler.sendPacket(new S07PacketRespawn(player.dimension, player.worldObj.getDifficulty(), player.worldObj.getWorldInfo()
//				.getTerrainType(), player.theItemInWorldManager.getGameType()));
//		worldserver.removePlayerEntityDangerously(player);
//		if (player.riddenByEntity != null) {
//			player.riddenByEntity.mountEntity(null);
//		}
//		if (player.ridingEntity != null) {
//			player.mountEntity(null);
//		}
//		player.isDead = false;
//		transferEntityToWorld(player, worldserver, worldserver1);
//		manager.preparePlayer(player, worldserver);
//		player.playerNetServerHandler.setPlayerLocation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
//		player.theItemInWorldManager.setWorld(worldserver1);
//		manager.updateTimeAndWeatherForPlayer(player, worldserver1);
//		manager.syncPlayerInventory(player);
//		Iterator<PotionEffect> iterator = player.getActivePotionEffects().iterator();
//
//		while (iterator.hasNext()) {
//			PotionEffect potioneffect = iterator.next();
//			player.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(player.getEntityId(), potioneffect));
//		}
//		FMLCommonHandler.instance().firePlayerChangedDimensionEvent(player, oldDim, dimension);
//	}

}
