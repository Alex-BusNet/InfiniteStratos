package com.sparta.is.init;

import com.sparta.is.InfiniteStratos;
import com.sparta.is.item.base.ItemIS;
import com.sparta.is.item.base.ItemISAdamantine;
import com.sparta.is.item.base.ItemYukihiraNigata;
import com.sparta.is.reference.Names;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

public class ModItems
{
    public static final List<ItemIS> ITEMS = new ArrayList<>();

    public static Item yukihira;
    public static Item adamantineItem;

    public static void register()
    {
        yukihira = registerItem(new ItemYukihiraNigata(), Names.Weapons.YUKIHIRA_NIGATA);
        adamantineItem = registerItem(new ItemISAdamantine(), Names.Materials.IS_ARMOR);
    }

//    @SideOnly(Side.CLIENT)
//    public static void initModelsAndVariants()
//    {
//        ITEMS.forEach(ItemIS::initModelsAndVariants);
//    }
//
//    @SideOnly(Side.CLIENT)
//    public static void registerItemColor()
//    {
//        for(ItemIS itemIS : ITEMS)
//        {
//            if(itemIS instanceof IItemColor)
//            {
//                FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler(new IItemColor()
//                {
//                    @Override
//                    public int getColorFromItemstack(ItemStack stack, int tintIndex)
//                    {
//                        return ((IItemColor) itemIS).getColorFromItemstack(stack, tintIndex);
//                    }
//                }, itemIS);
//            }
//        }
//    }

    private static Item registerItem(Item item, String name)
    {
        if(item.getRegistryName() == null)
        {
            item.setRegistryName(name);
        }
        GameRegistry.register(item);

        InfiniteStratos.proxy.getClientProxy().registerItemRenderer(item, 0, name);

        return item;
    }

}
