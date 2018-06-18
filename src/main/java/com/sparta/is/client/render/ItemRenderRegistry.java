package com.sparta.is.client.render;

import com.google.common.collect.Maps;
import com.sparta.is.client.render.Items.PerspectiveModel;
import com.sparta.is.core.client.model.BakedISUnitModel;
import com.sparta.is.core.reference.Names;
import com.sparta.is.core.reference.Reference;
import com.sparta.is.core.utils.helpers.LogHelper;
import com.sparta.is.init.ModItems;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Map;

@Mod.EventBusSubscriber
public class ItemRenderRegistry
{
    public static ItemRenderRegistry INSTANCE = new ItemRenderRegistry();

    private static ModelResourceLocation yukihiraNormalMrl, yukihiraInvMrl;
    private static ModelResourceLocation reirakuNormalMrl, reirakuInvMrl;
    private static ModelResourceLocation elucidatorNormalMrl, elucidatorInvMrl;
    private static ModelResourceLocation itemStandNormalMrl, itemStandInvMrl;
    private static ModelResourceLocation unitStationNormalMrl, unitStationInvMrl;

    private static ModelResourceLocation byakushikiNormalMrl, byakushikiInvMrl;
    private static ModelResourceLocation kuroakikoNormalMrl, kuroakikoInvMrl;

    public static void register()
    {
        LogHelper.info("Registering Custom models...");
        MinecraftForge.EVENT_BUS.register(INSTANCE);

        yukihiraNormalMrl = new ModelResourceLocation(Reference.MOD_ID + ":yukihiranigata");
        yukihiraInvMrl = new ModelResourceLocation(Reference.MOD_ID + ":yukihiranigata_inv");
        reirakuNormalMrl = new ModelResourceLocation(Reference.MOD_ID + ":reiraku");
        reirakuInvMrl = new ModelResourceLocation(Reference.MOD_ID + ":reiraku_inv");
        elucidatorNormalMrl = new ModelResourceLocation(Reference.MOD_ID + ":elucidator");
        elucidatorInvMrl = new ModelResourceLocation(Reference.MOD_ID + ":elucidator_inv");

        byakushikiInvMrl = new ModelResourceLocation(Reference.MOD_ID  + ":byakushiki_inv");
        byakushikiNormalMrl = new ModelResourceLocation(Reference.MOD_ID + ":byakushiki.unit");
        kuroakikoInvMrl = new ModelResourceLocation(Reference.MOD_ID + ":kuroakiko_inv");
        kuroakikoNormalMrl = new ModelResourceLocation(Reference.MOD_ID + ":kuroakiko.unit");

//        itemStandNormalMrl = new ModelResourceLocation(Reference.MOD_ID + ":itemdisplaystand");
//        itemStandInvMrl = new ModelResourceLocation(Reference.MOD_ID + ":itemdisplaystand_inv");
//        unitStationNormalMrl = new ModelResourceLocation(Reference.MOD_ID + ":isunitstation");
//        unitStationInvMrl = new ModelResourceLocation(Reference.MOD_ID + ":isunitstation_inv");

        modelList.add(Pair.of(yukihiraNormalMrl, yukihiraInvMrl));
        modelList.add(Pair.of(reirakuNormalMrl, reirakuInvMrl));
        modelList.add(Pair.of(elucidatorNormalMrl, elucidatorInvMrl));

        unitMrlRefMap.put(Names.Units.BYAKUSHIKI, byakushikiInvMrl);
        unitMrlRefMap.put(Names.Units.KURO_AKIKO, kuroakikoInvMrl);
//        modelList.add(Pair.of(itemStandNormalMrl, itemStandInvMrl));
//        modelList.add(Pair.of(unitStationNormalMrl, unitStationInvMrl));

        // Set the custom models to the Inventory variants
        ModelLoader.setCustomModelResourceLocation(ModItems.yukihira, 0, yukihiraInvMrl);
        ModelLoader.setCustomModelResourceLocation(ModItems.yukihira, 1, reirakuInvMrl);
        ModelLoader.setCustomModelResourceLocation(ModItems.elucidator, 0, elucidatorInvMrl);

        ModelLoader.setCustomModelResourceLocation(ModItems.byakushiki, 0, byakushikiInvMrl);
        ModelLoader.setCustomModelResourceLocation(ModItems.kuroakiko, 0, kuroakikoInvMrl);
//        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.itemDisplay), 0, itemStandInvMrl);
//        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.unitStation), 0, unitStationInvMrl);

        // Register the 3D versions of each model
        ModelBakery.registerItemVariants(ModItems.yukihira, yukihiraNormalMrl);
        ModelBakery.registerItemVariants(ModItems.yukihira, reirakuNormalMrl);
        ModelBakery.registerItemVariants(ModItems.elucidator, elucidatorNormalMrl);
        ModelBakery.registerItemVariants(ModItems.byakushiki, byakushikiNormalMrl);
        ModelBakery.registerItemVariants(ModItems.kuroakiko, kuroakikoNormalMrl);
//        ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.itemDisplay), itemStandNormalMrl);
//        ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.unitStation), unitStationNormalMrl);
    }

    public static void registerUnitModel(BakedISUnitModel unitModel, ModelResourceLocation inventoryModel)
    {
        unitModelList.add(Pair.of(unitModel, inventoryModel));
    }

    public static ModelResourceLocation getUnitInvMrl(String name)
    {
        return unitMrlRefMap.getOrDefault(name, null);
    }

    @SubscribeEvent
    public static void modelBake(ModelBakeEvent e)
    {
        for(Pair<ModelResourceLocation, ModelResourceLocation> mrl : modelList)
        {
            IBakedModel obj2D = e.getModelRegistry().getObject(mrl.getValue());
            IBakedModel obj3D = e.getModelRegistry().getObject(mrl.getKey());

            if(obj2D != null)
            {
                LogHelper.info("Creating new Perspective model for " + mrl.getKey() + " / " + mrl.getValue() + "...");
                PerspectiveModel perspectiveModel = new PerspectiveModel(obj3D, obj2D, obj2D);
                e.getModelRegistry().putObject(mrl.getValue(), perspectiveModel);
            }
        }

        for(Pair<BakedISUnitModel, ModelResourceLocation> bmrl : unitModelList)
        {
            IBakedModel obj3d = bmrl.getLeft();
            IBakedModel obj2d = e.getModelRegistry().getObject(bmrl.getValue());

            if(obj2d != null)
            {
                LogHelper.info("Creating new Perspective model for " + bmrl.getValue() + "...");
                PerspectiveModel perspectiveModel = new PerspectiveModel(obj3d, obj2d, obj2d);
                e.getModelRegistry().putObject(bmrl.getValue(), perspectiveModel);
            }
        }
    }

    /*
     * Models are pairs as Pair<Normal variant, Inventory variant>
     *     Pass the same model to both sides of the Pair<> when there is not
     *     a special variant to be used.
     */
    private static ArrayList<Pair<ModelResourceLocation, ModelResourceLocation>> modelList = new ArrayList<>();

    private static ArrayList<Pair<BakedISUnitModel, ModelResourceLocation>> unitModelList = new ArrayList<>();

    private static Map<String, ModelResourceLocation> unitMrlRefMap = Maps.newHashMap();
}
