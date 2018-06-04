package com.sparta.is.block;

import cofh.core.render.IModelRegister;
import cofh.core.util.core.IInitializer;
import com.sparta.is.core.InfiniteStratos;
import com.sparta.is.core.block.BlockIS;
import com.sparta.is.core.creativetab.CreativeTab;
import com.sparta.is.core.reference.Names;
import com.sparta.is.core.reference.Reference;
import com.sparta.is.core.utils.helpers.LogHelper;
import com.sparta.is.core.utils.interfaces.IForgeable;
import com.sparta.is.init.ModFluids;
import com.sparta.is.item.ItemOre;
import com.sparta.is.item.base.ItemMaterials;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static cofh.core.util.helpers.ItemHelper.registerWithHandlers;
import static net.minecraftforge.fml.common.registry.GameRegistry.addSmelting;

public class BlockOre extends BlockIS implements IInitializer, IModelRegister, IForgeable
{
    public static final PropertyEnum<OreVariant> VARIANT = PropertyEnum.create("variant", OreVariant.class);

    public BlockOre()
    {
        super("is", Material.ROCK);
        setUnlocalizedName("ore");
        setCreativeTab(CreativeTab.IS_TAB);
        setHardness(3.0f);
        setResistance(5.0f);
        setSoundType(SoundType.STONE);

        setDefaultState(getDefaultState().withProperty(VARIANT, OreVariant.ADAMANTINE_ORE));

        setHarvestLevel("pickaxe", 2);
        setHarvestLevel("pixaxe", 3, getStateFromMeta(OreVariant.ADAMANTINE_ORE.getMetadata()));
        setHarvestLevel("pickaxe", 3, getStateFromMeta(OreVariant.CRYSTALLINE_ORE.getMetadata()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels()
    {
        for(int i = 0; i < OreVariant.values().length; i++)
        {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), i, new ModelResourceLocation(Reference.MOD_ID + ":" + BASE_NAME, "variant=" + OreVariant.byMetadata(i).getName()));
        }
    }

    @Override
    public boolean register()
    {
        this.setRegistryName("ore");
        ForgeRegistries.BLOCKS.register(this);

        ItemOre itemOreBlock = new ItemOre(this);
        itemOreBlock.setRegistryName(this.getRegistryName());
        ForgeRegistries.ITEMS.register(itemOreBlock);

        oreAdamantine = new ItemStack(this, 1, OreVariant.ADAMANTINE_ORE.getMetadata());
        oreCrystalline = new ItemStack(this, 1, OreVariant.CRYSTALLINE_ORE.getMetadata());

        registerWithHandlers("oreAdamantine", oreAdamantine);
        registerWithHandlers("oreCrystalline", oreCrystalline);

        InfiniteStratos.proxy.getClientProxy().addIModel(this);

        return true;
    }

    @Override
    public boolean initialize()
    {
        LogHelper.info("Adding Ore Smelting Recipes...");
        addSmelting(oreAdamantine, ItemMaterials.adamantineIngot, 0.6f);
        addSmelting(oreCrystalline, ItemMaterials.crystallineIngot, 0.6f);

        return true;
    }

    @Override
    public void registerBlockWithTCon()
    {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("fluid", ModFluids.fluidAdamantine.getName());
        tag.setString("ore", Names.Blocks.ADAMANTINE_ORE);
        tag.setBoolean("toolforge", false);

        FMLInterModComms.sendMessage("tconstruct", "integrateSmeltery", tag);

        tag = new NBTTagCompound();
        tag.setString("fluid", ModFluids.fluidCrystalline.getName());
        tag.setString("ore", Names.Blocks.CRYSTALLINE_ORE);
        tag.setBoolean("toolforge", false);

        FMLInterModComms.sendMessage("tconstruct", "integrateSmeltery", tag);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        for ( int i = 0; i < BlockOre.OreVariant.METADATA_LOOKUP.length; i++) {
            items.add(new ItemStack(this, 1, i));
        }
    }

    /* TYPE METHODS */
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, OreVariant.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(VARIANT).getMetadata();
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(VARIANT).getMetadata();
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return state.getValue(VARIANT).getLight();
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List<ItemStack> itemStacks)
    {
        for (int i = 0; i < OreVariant.METADATA_LOOKUP.length; i++)
        {
            itemStacks.add(new ItemStack(this, 1, i));
        }
    }

    public enum OreVariant implements IStringSerializable
    {
        ADAMANTINE_ORE(0, "adamantine"),
        CRYSTALLINE_ORE(1, "crystalline");

        private final int metadata;
        private final String name;
        private final int light;
        private final EnumRarity rarity;
        protected static final OreVariant[] METADATA_LOOKUP = values();

        OreVariant(int metadata, String name, int light, EnumRarity rarity)
        {
            this.metadata = metadata;
            this.name = name;
            this.light = light;
            this.rarity = rarity;
        }

        OreVariant(int metadata, String name, int light)
        {
            this(metadata, name, light, EnumRarity.COMMON);
        }

        OreVariant(int metadata, String name)
        {
            this(metadata, name, 0);
        }

        public int getMetadata()
        {
            return metadata;
        }

        @Override
        public String getName()
        {
            return name;
        }

        public int getLight()
        {
            return light;
        }

        public EnumRarity getRarity()
        {
            return rarity;
        }

        public static OreVariant byMetadata(int metadata)
        {
            if(metadata < 0 || metadata >= METADATA_LOOKUP.length)
            {
                metadata = 0;
            }

            return METADATA_LOOKUP[metadata];
        }

        static {
            for(OreVariant type : values())
            {
                METADATA_LOOKUP[type.getMetadata()] = type;
            }
        }
    }

    public static ItemStack oreAdamantine;
    public static ItemStack oreCrystalline;
}
