package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

public class S41PacketServerDifficulty implements Packet<INetHandlerPlayClient>
{
    private EnumDifficulty difficulty;
    private boolean difficultyLocked;

    public S41PacketServerDifficulty()
    {
    }

    public S41PacketServerDifficulty(EnumDifficulty difficultyIn, boolean lockedIn)
    {
        this.difficulty = difficultyIn;
        this.difficultyLocked = lockedIn;
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(INetHandlerPlayClient handler)
    {
        handler.handleServerDifficulty(this);
    }

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.difficulty = EnumDifficulty.getDifficultyEnum(buf.readUnsignedByte());
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer buf) throws IOException
    {
        buf.writeByte(this.difficulty.getDifficultyId());
    }

    @SideOnly(Side.CLIENT)
    public boolean isDifficultyLocked()
    {
        return this.difficultyLocked;
    }

    @SideOnly(Side.CLIENT)
    public EnumDifficulty getDifficulty()
    {
        return this.difficulty;
    }
}