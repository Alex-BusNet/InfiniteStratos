package com.sparta.is.proxy;

import com.sparta.is.client.handler.DrawBlockHighlightEventHandler;
import com.sparta.is.client.handler.HUDTickHandler;
import com.sparta.is.client.handler.ItemTooltipEventHandler;
import com.sparta.is.client.handler.KeyInputEventHandler;
import com.sparta.is.client.render.Units.Byakushiki;
import com.sparta.is.client.render.entity.EntityRendererTabane;
import com.sparta.is.client.render.item.*;
import com.sparta.is.client.render.tileentity.TileEntityRendererAlchemyArray;
import com.sparta.is.client.render.tileentity.TileEntityRendererISUnitStation;
import com.sparta.is.client.render.tileentity.TileEntityRendererUnitStand;
import com.sparta.is.client.settings.KeyBindings;
import com.sparta.is.client.util.ClientSoundHelper;
import com.sparta.is.entity.EntityISTabane;
import com.sparta.is.init.ModBlocks;
import com.sparta.is.init.ModItems;
import com.sparta.is.init.ModUnits;
import com.sparta.is.reference.RenderIds;
import com.sparta.is.settings.ChalkSettings;
import com.sparta.is.settings.OneOffSettings;
import com.sparta.is.settings.UnitSettings;
import com.sparta.is.tileentity.TileEntityAlchemyArray;
import com.sparta.is.tileentity.TileEntityISStation;
import com.sparta.is.tileentity.TileEntityUnitStand;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy
{
    public ChalkSettings chalkSettings = new ChalkSettings();
    public UnitSettings unitSettings = new UnitSettings();
    public OneOffSettings oneOffSettings = new OneOffSettings();

    private static Byakushiki byakushiki = new Byakushiki(1.0f);
    private static Byakushiki byakushikiLegs = new Byakushiki(0.5f);


    @Override
    public void registerEventHandlers()
    {
        super.registerEventHandlers();
        FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());
        FMLCommonHandler.instance().bus().register(new HUDTickHandler());
        MinecraftForge.EVENT_BUS.register(new ItemTooltipEventHandler());
        MinecraftForge.EVENT_BUS.register(new DrawBlockHighlightEventHandler());
    }

    @Override
    public ClientProxy getClientProxy()
    {
        return this;
    }

    @Override
    public void initRenderingAndTextures()
    {
        RenderIds.isUnitStation = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.unitStand = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.alchemyArray = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.dummyArray = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.byakushikiChest = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.yukihiraNigata = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.elucidator = RenderingRegistry.getNextAvailableRenderId();

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.isStation), new ItemRendererISUnitStation());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.unitStand), new ItemRendererUnitStand());
        MinecraftForgeClient.registerItemRenderer(ModUnits.byakushikiChest, new ItemRendererByakushikiChest());
        MinecraftForgeClient.registerItemRenderer(ModItems.yukihiraNigata, new ItemRendererYukihira());
        MinecraftForgeClient.registerItemRenderer(ModItems.elucidator, new ItemRendererElucidator());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityISStation.class, new TileEntityRendererISUnitStation());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityUnitStand.class, new TileEntityRendererUnitStand());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAlchemyArray.class, new TileEntityRendererAlchemyArray());

        RenderingRegistry.registerEntityRenderingHandler(EntityISTabane.class, new EntityRendererTabane());
    }

    @Override
    public void registerKeyBindings()
    {
        ClientRegistry.registerKeyBinding(KeyBindings.standbyKey);
        ClientRegistry.registerKeyBinding(KeyBindings.fullDeployKey);
        ClientRegistry.registerKeyBinding(KeyBindings.partialDeployKey);
        ClientRegistry.registerKeyBinding(KeyBindings.equalizerAccessModifier);
        ClientRegistry.registerKeyBinding(KeyBindings.oneOffAbility);
        ClientRegistry.registerKeyBinding(KeyBindings.oneOffAbilityOff);
    }

    @Override
    public void playSound(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch)
    {
        ClientSoundHelper.playSound(soundName, xCoord, yCoord, zCoord, volume, pitch);
    }

    @Override
    public ModelBiped getArmorModel(int id)
    {
        switch ( id )
        {
            case 0:
                return byakushiki;
            case 1:
                return byakushikiLegs;
            default:
                break;
        }

        return byakushiki;
    }


}
