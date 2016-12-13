package com.sparta.is.block;

import com.sparta.is.creativetab.CreativeTab;
import com.sparta.is.init.ModBlocks;
import com.sparta.is.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockIS extends Block
{
    private final String BASE_NAME;

    public BlockIS(String name)
    {
        this(name, Material.WOOD);
    }

    public BlockIS(String name, Material material)
    {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(CreativeTab.IS_TAB);
        BASE_NAME = name;
        ModBlocks.register(this);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s:%s", Reference.MOD_ID, BASE_NAME);
    }

    @SideOnly(Side.CLIENT)
    public void initModelsAndVariants()
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName().toString()));
    }



}
