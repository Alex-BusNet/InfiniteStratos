package com.sparta.is.core.network;

import com.sparta.is.core.data.PlayerData;
import com.sparta.is.core.data.WorldData;
import com.sparta.is.core.network.gui.IButtonReactor;
import com.sparta.is.core.network.gui.INumberReactor;
import com.sparta.is.core.network.gui.IStringReactor;
import com.sparta.is.core.tileentity.TileEntityIS;
import com.sparta.is.core.utils.helpers.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class PacketHandler
{
    public static final List<IDataHandler> DATA_HANDLERS = new ArrayList<IDataHandler>();

    public static final IDataHandler TILE_ENTITY_HANDLER = new IDataHandler()
    {
        @Override
        @SideOnly(Side.CLIENT)
        public void handleData(NBTTagCompound compound, MessageContext context){
            World world = Minecraft.getMinecraft().world;
            if(world != null){
                TileEntity tile = world.getTileEntity(new BlockPos(compound.getInteger("X"), compound.getInteger("Y"), compound.getInteger("Z")));
                if(tile instanceof TileEntityIS){
                    ((TileEntityIS)tile).readSyncableNBT(compound.getCompoundTag("Data"), TileEntityIS.NBTType.SYNC);
                }
            }
        }
    };

    public static final IDataHandler GUI_BUTTON_TO_TILE_HANDLER = new IDataHandler(){
        @Override
        public void handleData(NBTTagCompound compound, MessageContext context){
            World world = DimensionManager.getWorld(compound.getInteger("WorldID"));
            TileEntity tile = world.getTileEntity(new BlockPos(compound.getInteger("X"), compound.getInteger("Y"), compound.getInteger("Z")));

            if(tile instanceof IButtonReactor){
                IButtonReactor reactor = (IButtonReactor)tile;
                Entity entity = world.getEntityByID(compound.getInteger("PlayerID"));
                if(entity instanceof EntityPlayer ){
                    reactor.onButtonPressed(compound.getInteger("ButtonID"), (EntityPlayer)entity);
                }
            }
        }
    };

    public static final IDataHandler GUI_BUTTON_TO_CONTAINER_HANDLER = new IDataHandler(){
        @Override
        public void handleData(NBTTagCompound compound, MessageContext context){
            World world = DimensionManager.getWorld(compound.getInteger("WorldID"));
            Entity entity = world.getEntityByID(compound.getInteger("PlayerID"));
            if(entity instanceof EntityPlayer){
                Container container = ((EntityPlayer)entity).openContainer;
                if(container instanceof IButtonReactor){
                    ((IButtonReactor)container).onButtonPressed(compound.getInteger("ButtonID"), (EntityPlayer)entity);
                }
            }
        }
    };

    public static final IDataHandler GUI_NUMBER_TO_TILE_HANDLER = new IDataHandler(){
        @Override
        public void handleData(NBTTagCompound compound, MessageContext context){
            World world = DimensionManager.getWorld(compound.getInteger("WorldID"));
            TileEntity tile = world.getTileEntity(new BlockPos(compound.getInteger("X"), compound.getInteger("Y"), compound.getInteger("Z")));

            if(tile instanceof INumberReactor){
                INumberReactor reactor = (INumberReactor)tile;
                reactor.onNumberReceived(compound.getDouble("Number"), compound.getInteger("NumberID"), (EntityPlayer)world.getEntityByID(compound.getInteger("PlayerID")));
            }
        }
    };

    public static final IDataHandler GUI_STRING_TO_TILE_HANDLER = new IDataHandler(){
        @Override
        public void handleData(NBTTagCompound compound, MessageContext context){
            World world = DimensionManager.getWorld(compound.getInteger("WorldID"));
            TileEntity tile = world.getTileEntity(new BlockPos(compound.getInteger("X"), compound.getInteger("Y"), compound.getInteger("Z")));

            if(tile instanceof IStringReactor){
                IStringReactor reactor = (IStringReactor)tile;
                reactor.onTextReceived(compound.getString("Text"), compound.getInteger("TextID"), (EntityPlayer)world.getEntityByID(compound.getInteger("PlayerID")));
            }
        }
    };

    public static final IDataHandler SYNC_PLAYER_DATA = new IDataHandler(){
        @Override
        @SideOnly(Side.CLIENT)
        public void handleData(NBTTagCompound compound, MessageContext context){
            NBTTagCompound dataTag = compound.getCompoundTag("Data");
            EntityPlayer player = Minecraft.getMinecraft().player;

            if(player != null){
                PlayerData.getDataFromPlayer(player).readFromNBT(dataTag, false);

                if(compound.getBoolean("Log")){
                    LogHelper.info("Receiving (new or changed) Player Data for player "+player.getName()+".");
                }
            }
            else{
                LogHelper.error("Tried to receive Player Data for the current player, but he doesn't seem to be present!");
            }
        }
    };

    public static final IDataHandler PLAYER_DATA_TO_SERVER = new IDataHandler(){
        @Override
        public void handleData(NBTTagCompound compound, MessageContext context){
            World world = DimensionManager.getWorld(compound.getInteger("World"));
            EntityPlayer player = world.getPlayerEntityByUUID(compound.getUniqueId("UUID"));
            if(player != null){
                PlayerData.PlayerSave data = PlayerData.getDataFromPlayer(player);

                int type = compound.getInteger("Type");
                if(type == 1){
                    data.didBookTutorial = compound.getBoolean("DidBookTutorial");
                }
                else if(type == 2){
                    data.loadTrials(compound.getTagList("Trials", 8));

                    if(compound.getBoolean("Achievement")){
                        //TheAchievements.COMPLETE_TRIALS.get(player);
                    }
                }

                WorldData.get(world).markDirty();

                if(compound.getBoolean("Log")){
                    LogHelper.info("Receiving changed Player Data for player "+player.getName()+".");
                }
            }
            else{
                LogHelper.error("Tried to receive Player Data for UUID "+compound.getUniqueId("UUID")+", but he doesn't seem to be present!");
            }
        }
    };

    public static SimpleNetworkWrapper theNetwork;

    public static void init()
    {
        theNetwork = Network.INSTANCE;
        theNetwork.registerMessage(PacketServerToClient.Handler.class, PacketServerToClient.class, 0, Side.CLIENT);
        theNetwork.registerMessage(PacketClientToServer.Handler.class, PacketClientToServer.class, 1, Side.SERVER);

        DATA_HANDLERS.add(TILE_ENTITY_HANDLER);
        DATA_HANDLERS.add(GUI_BUTTON_TO_TILE_HANDLER);
        DATA_HANDLERS.add(GUI_STRING_TO_TILE_HANDLER);
        DATA_HANDLERS.add(GUI_NUMBER_TO_TILE_HANDLER);
        DATA_HANDLERS.add(SYNC_PLAYER_DATA);
        DATA_HANDLERS.add(GUI_BUTTON_TO_CONTAINER_HANDLER);
        DATA_HANDLERS.add(PLAYER_DATA_TO_SERVER);
    }
}
