package com.sparta.is.core.tileentity;

import com.sparta.is.core.config.values.ConfigIntValues;
import com.sparta.is.core.network.PacketHandler;
import com.sparta.is.core.network.PacketServerToClient;
import com.sparta.is.core.reference.Names;
import com.sparta.is.core.reference.Reference;
import com.sparta.is.core.utils.WorldUtil;
import com.sparta.is.core.utils.helpers.LogHelper;
import com.sparta.is.core.utils.interfaces.ISharingEnergyProvider;
import com.sparta.is.core.utils.interfaces.ISharingFluidHandler;
import com.sparta.is.tileentity.TileEntityISUnitStation;
import com.sparta.is.tileentity.TileEntityItemDisplay;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TileEntityIS extends TileEntity implements ITickable
{
    protected String customName;
    public boolean isRedstonePowered;
    public boolean isPulseMode;
    public boolean stopFromDropping;
    protected int ticksElapsed;
    protected TileEntity[] tilesAround = new TileEntity[6];
    protected boolean hasSavedDataOnChangeOrWorldStart;
    protected EnumFacing orientation;

    public TileEntityIS(String name)
    {
        orientation = EnumFacing.SOUTH;
        customName = name;
    }

    public static void init()
    {
        LogHelper.info("Registering Tile Entities...");
        register(TileEntityItemDisplay.class);
        register(TileEntityISUnitStation.class);
    }

    private static void register(Class<? extends TileEntityIS> tileClass)
    {
        try
        {
            String name = Reference.MOD_ID + ":" + tileClass.newInstance().getCustomName();
            GameRegistry.registerTileEntity(tileClass, name);
        }
        catch(Exception e)
        {
            LogHelper.fatal("Tile entity registration failed!");
        }
    }

    public String getCustomName()
    {
        return customName;
    }

    public void setCustomName(String name)
    {
        customName = name;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        if (nbtTagCompound.hasKey(Names.NBT.CUSTOM_NAME))
        {
            this.customName = nbtTagCompound.getString(Names.NBT.CUSTOM_NAME);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        if (this.hasCustomName())
        {
            nbtTagCompound.setString(Names.NBT.CUSTOM_NAME, customName);
        }

        if( this.customName != null )
        {
            nbtTagCompound.setString( "customName", this.customName );
        }

        return nbtTagCompound;
    }

    @Override
    public final SPacketUpdateTileEntity getUpdatePacket()
    {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeSyncableNBT(tag, NBTType.SYNC);
        return new SPacketUpdateTileEntity(this.pos, -1, tag);
    }

    @Override
    public final void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        this.readSyncableNBT(pkt.getNbtCompound(), NBTType.SYNC);
    }

    @Override
    public final NBTTagCompound getUpdateTag()
    {
        NBTTagCompound compound = new NBTTagCompound();
        this.writeSyncableNBT(compound, NBTType.SYNC);
        return compound;
    }

    @Override
    public final void handleUpdateTag(NBTTagCompound compound)
    {
        this.readSyncableNBT(compound, NBTType.SYNC);
    }

    public final void sendUpdate()
    {
        if(this.world != null && !this.world.isRemote)
        {
            NBTTagCompound compound = new NBTTagCompound();
            this.writeSyncableNBT(compound, NBTType.SYNC);

            NBTTagCompound data = new NBTTagCompound();
            data.setTag("Data", compound);
            data.setInteger("X", this.pos.getX());
            data.setInteger("Y", this.pos.getY());
            data.setInteger("Z", this.pos.getZ());
            PacketHandler.theNetwork.sendToAllAround(new PacketServerToClient(data, PacketHandler.TILE_ENTITY_HANDLER), new NetworkRegistry.TargetPoint(this.world.provider.getDimension(), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 64));
        }
    }

    public void writeSyncableNBT(NBTTagCompound compound, NBTType type)
    {
        if(type != NBTType.SAVE_BLOCK) super.writeToNBT(compound);

        if(type == NBTType.SAVE_TILE)
        {
            compound.setBoolean("Redstone", this.isRedstonePowered);
            compound.setInteger("TicksElapsed", this.ticksElapsed);
            compound.setBoolean("StopDrop", this.stopFromDropping);
        }
        else if(type == NBTType.SYNC && stopFromDropping) compound.setBoolean("StopDrop", this.stopFromDropping);

        if(this.isRedstoneToggle() && (type != NBTType.SAVE_BLOCK || this.isPulseMode))
        {
            compound.setBoolean("IsPulseMode", this.isPulseMode);
        }
    }

    public void readSyncableNBT(NBTTagCompound compound, NBTType type)
    {
        if(type != NBTType.SAVE_BLOCK) super.readFromNBT(compound);

        if(type == NBTType.SAVE_TILE)
        {
            this.isRedstonePowered = compound.getBoolean("Redstone");
            this.ticksElapsed = compound.getInteger("TicksElapsed");
            this.stopFromDropping = compound.getBoolean("StopDrop");
        }
        else if(type == NBTType.SYNC) this.stopFromDropping = compound.getBoolean("StopDrop");

        if(this.isRedstoneToggle())
        {
            this.isPulseMode = compound.getBoolean("IsPulseMode");
        }
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
    {
        return !oldState.getBlock().isAssociatedBlock(newState.getBlock());
    }

    public String getNameForTranslation()
    {
        return "container." + Reference.MOD_ID + "." + this.customName + ".name";
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentTranslation(this.getNameForTranslation());
    }

    @Override
    public final void update()
    {
        this.updateEntity();
    }

    public int getComparatorStrength()
    {
        return 0;
    }

    public void updateEntity()
    {
        this.ticksElapsed++;

        if(!this.world.isRemote){
            if(this instanceof ISharingEnergyProvider )
            {
                ISharingEnergyProvider provider = (ISharingEnergyProvider)this;
                if(provider.doesShareEnergy())
                {
                    int total = provider.getEnergyToSplitShare();
                    if(total > 0)
                    {
                        EnumFacing[] sides = provider.getEnergyShareSides();

                        int amount = total/sides.length;
                        if(amount <= 0)
                        {
                            amount = total;
                        }

                        for(EnumFacing side : sides)
                        {
                            TileEntity tile = this.tilesAround[side.ordinal()];
                            if(tile != null && provider.canShareTo(tile))
                            {
                                WorldUtil.doEnergyInteraction(this, tile, side, amount);
                            }
                        }
                    }
                }
            }

            if(this instanceof ISharingFluidHandler ){
                ISharingFluidHandler handler = (ISharingFluidHandler)this;
                if(handler.doesShareFluid())
                {
                    int total = handler.getMaxFluidAmountToSplitShare();
                    if(total > 0)
                    {
                        EnumFacing[] sides = handler.getFluidShareSides();

                        int amount = total/sides.length;
                        if(amount <= 0)
                        {
                            amount = total;
                        }

                        for(EnumFacing side : sides)
                        {
                            TileEntity tile = this.tilesAround[side.ordinal()];
                            if(tile != null)
                            {
                                WorldUtil.doFluidInteraction(this, tile, side, amount);
                            }
                        }
                    }
                }
            }

            if(!this.hasSavedDataOnChangeOrWorldStart)
            {
                if(this.shouldSaveDataOnChangeOrWorldStart())
                {
                    this.saveDataOnChangeOrWorldStart();
                }

                this.hasSavedDataOnChangeOrWorldStart = true;
            }
        }
    }

    public void saveDataOnChangeOrWorldStart()
    {
        for(EnumFacing side : EnumFacing.values())
        {
            BlockPos pos = this.pos.offset(side);
            if(this.world.isBlockLoaded(pos))
            {
                this.tilesAround[side.ordinal()] = this.world.getTileEntity(pos);
            }
        }
    }

    public boolean shouldSaveDataOnChangeOrWorldStart()
    {
        return this instanceof ISharingEnergyProvider || this instanceof ISharingFluidHandler;
    }

    public void setRedstonePowered(boolean powered)
    {
        this.isRedstonePowered = powered;
        this.markDirty();
    }

    public boolean canPlayerUse(EntityPlayer player)
    {
        return player.getDistanceSq(this.getPos().getX()+0.5D, this.pos.getY()+0.5D, this.pos.getZ()+0.5D) <= 64 && !this.isInvalid() && this.world.getTileEntity(this.pos) == this;
    }

    protected boolean sendUpdateWithInterval()
    {
        if(this.ticksElapsed % ConfigIntValues.TILE_ENTITY_UPDATE_INTERVAL.getValue() == 0)
        {
            this.sendUpdate();
            return true;
        }
        else
        {
            return false;
        }
    }

    public EnumFacing getOrientation()
    {
        return orientation;
    }

    public void setOrientation(EnumFacing orientation)
    {
        this.orientation = orientation;
    }

    public void setOrientation(int orientation)
    {
        this.orientation = EnumFacing.getFront(orientation);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return this.getCapability(capability, facing) != null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            IItemHandler handler = this.getItemHandler(facing);
            if(handler != null)
            {
                return (T)handler;
            }
        }
        else if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
        {
            IFluidHandler tank = this.getFluidHandler(facing);
            if(tank != null)
            {
                return (T)tank;
            }
        }
        else if(capability == CapabilityEnergy.ENERGY)
        {
            IEnergyStorage storage = this.getEnergyStorage(facing);
            if(storage != null)
            {
                return (T)storage;
            }
        }
        return super.getCapability(capability, facing);
    }

    public IFluidHandler getFluidHandler(EnumFacing facing)
    {
        return null;
    }

    public IEnergyStorage getEnergyStorage(EnumFacing facing)
    {
        return null;
    }

    public IItemHandler getItemHandler(EnumFacing facing)
    {
        return null;
    }

    public boolean isRedstoneToggle()
    {
        return false;
    }

    public void activateOnPulse()
    {

    }

    public boolean respondsToPulses()
    {
        return this.isRedstoneToggle() && this.isPulseMode;
    }

    public enum NBTType
    {
        SAVE_TILE,
        SYNC,
        SAVE_BLOCK
    }

    @Override
    public boolean hasFastRenderer()
    {
        return false;
    }

    public boolean hasCustomName()
    {
        return customName != null && customName.length() > 0;
    }

}
