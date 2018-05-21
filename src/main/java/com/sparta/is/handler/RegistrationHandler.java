package com.sparta.is.handler;

import com.sparta.is.armor.ArmorIS;
import com.sparta.is.block.BlockIS;
import com.sparta.is.init.ModBlocks;
import com.sparta.is.init.ModEntities;
import com.sparta.is.init.ModItems;
import com.sparta.is.item.base.ItemIS;
import com.sparta.is.item.base.ItemISMelee;
import com.sparta.is.item.base.ItemISRange;
import com.sparta.is.utils.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class RegistrationHandler
{
    public RegistrationHandler()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public final void onModelRegistry(ModelRegistryEvent event)
    {
        LogHelper.info("Registering Models...");
        LogHelper.info("\tRegistering Item Models...");
        for(ItemIS item : ModItems.getItems())
        {
            LogHelper.info("\t\t" + item.getUnlocalizedName());
            item.registerVariants();
        }

        LogHelper.info("\tRegistering Melee Equalizer Models...");
        for( ItemISMelee item : ModItems.getMeleeItems())
        {

            LogHelper.info("\t\t" + item.getUnlocalizedName());
            item.registerVariants();
        }

        LogHelper.info("\tRegistering Ranged Equalizers Models...");
        for ( ItemISRange item : ModItems.getRangeItems() )
        {

            LogHelper.info("\t\t" + item.getUnlocalizedName());
            item.registerVariants();
        }

        LogHelper.info("\tRegistering Unit Models...");
        for( ArmorIS item : ModItems.getUnits())
        {

            LogHelper.info("\t\t" + item.getUnlocalizedName());
            item.registerVariants();
        }

        LogHelper.info("\tRegistering Tab Label Item Model...");
        ModelLoader.setCustomModelResourceLocation(ModItems.tabLabelItem, 0, new ModelResourceLocation(ModItems.tabLabelItem.getRegistryName().toString()));

        LogHelper.info("\tRegistering Tabane Spawn Egg Model...");
        ModelLoader.setCustomModelResourceLocation(ModItems.tabaneSpawnEgg, 0, new ModelResourceLocation(ModItems.tabaneSpawnEgg.getRegistryName().toString()));

        LogHelper.info("\tRegistering Block Models...");
        for(BlockIS block : ModBlocks.getBlocks())
        {
            LogHelper.info("\t\t" + block.getUnlocalizedName());
            block.registerVariants();
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        LogHelper.info("Registering blocks...");
        for ( BlockIS block : ModBlocks.getBlocks() )
        {
            LogHelper.info("\t" + block.getUnlocalizedName());
            event.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        LogHelper.info("Registering items...");
        for(ItemIS item : ModItems.getItems())
        {
            LogHelper.info("\t" + item.getUnlocalizedName());
            event.getRegistry().register(item);
        }

        LogHelper.info("Registering Melee Equalizers...");
        for(ItemISMelee item : ModItems.getMeleeItems())
        {
            LogHelper.info("\t" + item.getUnlocalizedName());
            event.getRegistry().register(item);
        }

        LogHelper.info("Registering Ranged Equalizers...");
        for ( ItemISRange item : ModItems.getRangeItems() )
        {
            LogHelper.info("\t" + item.getUnlocalizedName());
            event.getRegistry().register(item);
        }

        LogHelper.info("Registering misc. items...");
        event.getRegistry().register(ModItems.tabaneSpawnEgg);
        event.getRegistry().register(ModItems.tabLabelItem);

        LogHelper.info("Registering IS Units...");
        for(ArmorIS item : ModItems.getUnits())
        {
            LogHelper.info("\t" + item.getUnlocalizedName());
            event.getRegistry().register(item);
        }
    }

    @SubscribeEvent
    public final void registerEntities(RegistryEvent.Register<EntityEntry> event)
    {
        LogHelper.info("Registering entities...");
        ModEntities.init();
    }

}
