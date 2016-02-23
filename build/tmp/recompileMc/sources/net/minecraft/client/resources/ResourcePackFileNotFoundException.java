package net.minecraft.client.resources;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.io.FileNotFoundException;

@SideOnly(Side.CLIENT)
public class ResourcePackFileNotFoundException extends FileNotFoundException
{
    public ResourcePackFileNotFoundException(File p_i1294_1_, String p_i1294_2_)
    {
        super(String.format("\'%s\' in ResourcePack \'%s\'", new Object[] {p_i1294_2_, p_i1294_1_}));
    }
}