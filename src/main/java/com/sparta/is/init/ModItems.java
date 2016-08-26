package com.sparta.is.init;

import com.sparta.is.item.*;
import com.sparta.is.reference.Names;
import com.sparta.is.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final List<ItemIS> ITEMS = new ArrayList<>();


    public static final ItemISEqualizer yukihiraNigata = new ItemYukihira();

    public static final ItemISEqualizer elucidator = new ItemElucidator();

    public static final ItemIS yukihiraBlade = new ItemYukihiraBlade();
    public static final ItemIS yukihiraHilt = new ItemYukihiraHilt();

    public static final ItemIS isCore = new ItemISCore();
    public static final ItemIS isProcessor = new ItemISProcessor();
    public static final ItemIS absoluteDefense = new ItemISDefense();

    public static final Item tabLabel = new ItemTabLabel();

    public static final ItemISUnit testUnit = new ItemISTestUnit();

    public static final ItemTabaneSpawnEgg tabaneSpawnEgg = new ItemTabaneSpawnEgg(0xFFA8DE, 0xCF5DA3);

    public static void init()
    {
        GameRegistry.registerItem(yukihiraNigata, Names.Weapons.YUKIHIRA_NIGATA);
        GameRegistry.registerItem(yukihiraBlade, Names.Parts.YUKIHIRA_BLADE);
        GameRegistry.registerItem(yukihiraHilt, Names.Parts.YUKIHIRA_HILT);
        GameRegistry.registerItem(isCore, Names.Parts.IS_CORE);
        GameRegistry.registerItem(isProcessor, Names.Parts.IS_PROCESSOR);
        GameRegistry.registerItem(absoluteDefense, Names.Parts.ABSOLUTE_DEFENSE);
        GameRegistry.registerItem(testUnit, Names.Units.TEST_UNIT);
        GameRegistry.registerItem(tabaneSpawnEgg, Names.Items.TABANE_SPAWN_EGG);
        GameRegistry.registerItem(elucidator, Names.Weapons.ELUCIDATOR);
        GameRegistry.registerItem(tabLabel, Names.Empty.TAB_LABEL);
    }

    public static void registerRenders()
    {
        registerRender(yukihiraNigata);
        registerRender(elucidator);
    }

    private static void registerRender(Item item)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

    private static void registerRender(Item item, int maxMeta)
    {
        for (int i = 0; i < maxMeta; i++)
        {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, i, new ModelResourceLocation(Reference.MOD_ID + ":" + item .getUnlocalizedName().substring(5), "inventory"));
        }
    }

    @SideOnly(Side.CLIENT)
    public static void initModelsAndVariants()
    {
        ITEMS.forEach(ItemIS::initModelsAndVariants);
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemColors()
    {
        for(ItemIS itemIS : ITEMS)
        {
            if(itemIS instanceof IItemColor )
            {
                FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler(new IItemColor()
                {
                    @Override
                    public int getColorFromItemstack(ItemStack stack, int tintIndex)
                    {
                        return ((IItemColor) itemIS).getColorFromItemstack(stack, tintIndex);
                    }
                }, itemIS);
            }
        }
    }







}
