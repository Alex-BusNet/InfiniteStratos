package com.sparta.is.core.data;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerData
{
    public static PlayerSave getDataFromPlayer(EntityPlayer player){
        WorldData worldData = WorldData.get(player.getEntityWorld());
        ConcurrentHashMap<UUID, PlayerSave> data = worldData.playerSaveData;
        UUID id = player.getUniqueID();

        if(data.containsKey(id)){
            PlayerSave save = data.get(id);
            if(save != null && save.id != null && save.id.equals(id)){
                return save;
            }
        }

        //Add Data if none is existant
        PlayerSave save = new PlayerSave(id);
        data.put(id, save);
        worldData.markDirty();
        return save;
    }

    public static class PlayerSave{

        public UUID id;

        public boolean bookGottenAlready;
        public boolean didBookTutorial;
        public boolean hasBatWings;
        public boolean shouldDisableBatWings;
        public int batWingsFlyTime;

        public List<String> completedTrials = new ArrayList<String>();


        public PlayerSave(UUID id){
            this.id = id;
        }

        public void readFromNBT(NBTTagCompound compound, boolean savingToFile){
            this.bookGottenAlready = compound.getBoolean("BookGotten");
            this.didBookTutorial = compound.getBoolean("DidTutorial");

            this.hasBatWings = compound.getBoolean("HasBatWings");
            this.batWingsFlyTime = compound.getInteger("BatWingsFlyTime");

            NBTTagList trials = compound.getTagList("Trials", 8);
            this.loadTrials(trials);

            if(!savingToFile){
                this.shouldDisableBatWings = compound.getBoolean("ShouldDisableWings");
            }
        }

        public void writeToNBT(NBTTagCompound compound, boolean savingToFile){
            compound.setBoolean("BookGotten", this.bookGottenAlready);
            compound.setBoolean("DidTutorial", this.didBookTutorial);

            compound.setBoolean("HasBatWings", this.hasBatWings);
            compound.setInteger("BatWingsFlyTime", this.batWingsFlyTime);

            compound.setTag("Trials", this.saveTrials());

            if(!savingToFile){
                compound.setBoolean("ShouldDisableWings", this.shouldDisableBatWings);
            }
        }

        public NBTTagList saveTrials(){
            NBTTagList trials = new NBTTagList();
            for(String trial : this.completedTrials){
                trials.appendTag(new NBTTagString(trial));
            }
            return trials;
        }

        public void loadTrials(NBTTagList trials){
            this.completedTrials.clear();

            for(int i = 0; i < trials.tagCount(); i++){
                String strg = trials.getStringTagAt(i);
                if(strg != null && !strg.isEmpty()){
                    this.completedTrials.add(strg);
                }
            }
        }
    }
}
