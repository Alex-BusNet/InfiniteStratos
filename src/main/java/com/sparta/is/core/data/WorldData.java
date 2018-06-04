package com.sparta.is.core.data;

import com.sparta.is.core.network.Network;
import com.sparta.is.core.reference.Reference;
import com.sparta.is.core.utils.helpers.LogHelper;
import io.netty.util.internal.ConcurrentSet;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.WorldSpecificSaveHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class WorldData extends WorldSavedData
{
    public static final String DATA_TAG = Reference.MOD_ID + "data";
    //TODO Remove this as well
    public static List<File> legacyLoadWorlds = new ArrayList<File>();
    private static WorldData data;
    public final ConcurrentSet<Network> laserRelayNetworks = new ConcurrentSet<Network>();
    public final ConcurrentHashMap<UUID, PlayerData.PlayerSave> playerSaveData = new ConcurrentHashMap<UUID, PlayerData.PlayerSave>();

    public WorldData(String name){
        super(name);
    }

    public static WorldData get(World world, boolean forceLoad){
        WorldData w = getInternal(world, forceLoad);
        if(w == null) LogHelper.error("What the hell how is this stupid thing null again AEWBFINCEMR");
        return w == null ? new WorldData(DATA_TAG) : w;
    }

    private static WorldData getInternal(World world, boolean forceLoad){
        if(forceLoad || data == null){
            if(!world.isRemote){
                WorldSavedData savedData = world.loadData(WorldData.class, DATA_TAG);

                if(!(savedData instanceof WorldData)){
                   LogHelper.info("No WorldData found, creating...");

                    WorldData newData = new WorldData(DATA_TAG);
                    world.setData(DATA_TAG, newData);
                    data = newData;
                }
                else{
                    data = (WorldData) savedData;
                    LogHelper.info("Successfully loaded WorldData!");
                }
            }
            else{
                data = new WorldData(DATA_TAG);
                LogHelper.info("Created temporary WorldData to cache data on the client!");
            }
        }
        return data;
    }

    public static void clear(){
        if(data != null){
            data = null;
            LogHelper.info("Unloaded WorldData!");
        }
    }

    public static WorldData get(World world){
        return get(world, false);
    }

    //TODO Remove old loading mechanic after a while because it's legacy
    public static void loadLegacy(World world){
        if(!world.isRemote && world instanceof WorldServer){
            int dim = world.provider.getDimension();
            ISaveHandler handler = new WorldSpecificSaveHandler((WorldServer)world, world.getSaveHandler());
            File dataFile = handler.getMapFileFromName(DATA_TAG+dim);
            legacyLoadWorlds.add(dataFile);
        }
    }

    //TODO Remove merging once removing old save handler
    private void readFromNBT(NBTTagCompound compound, boolean merge)
    {
        //Player Data
        if(!merge){
            this.playerSaveData.clear();
        }
        NBTTagList playerList = compound.getTagList("PlayerData", 10);
        for(int i = 0; i < playerList.tagCount(); i++){
            NBTTagCompound player = playerList.getCompoundTagAt(i);

            UUID id = player.getUniqueId("UUID");
            NBTTagCompound data = player.getCompoundTag("Data");

            PlayerData.PlayerSave save = new PlayerData.PlayerSave(id);
            save.readFromNBT(data, true);
            this.playerSaveData.put(id, save);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound){
        this.readFromNBT(compound, false);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        //Player Data
        NBTTagList playerList = new NBTTagList();
        for(PlayerData.PlayerSave save : this.playerSaveData.values()){
            NBTTagCompound player = new NBTTagCompound();
            player.setUniqueId("UUID", save.id);

            NBTTagCompound data = new NBTTagCompound();
            save.writeToNBT(data, true);
            player.setTag("Data", data);

            playerList.appendTag(player);
        }
        compound.setTag("PlayerData", playerList);

        return compound;
    }
}
