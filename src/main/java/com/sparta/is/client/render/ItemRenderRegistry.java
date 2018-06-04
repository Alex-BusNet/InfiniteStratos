package com.sparta.is.client.render;

import com.sparta.is.client.render.Items.PerspectiveModel;
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

@Mod.EventBusSubscriber
public class ItemRenderRegistry
{
    public static ItemRenderRegistry INSTANCE = new ItemRenderRegistry();

    private static ModelResourceLocation yukihiraNormalMrl, yukihiraInvMrl;
    private static ModelResourceLocation reirakuNormalMrl, reirakuInvMrl;
    private static ModelResourceLocation elucidatorNormalMrl, elucidatorInvMrl;
    private static ModelResourceLocation itemStandNormalMrl, itemStandInvMrl;
    private static ModelResourceLocation unitStationNormalMrl, unitStationInvMrl;

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
//        itemStandNormalMrl = new ModelResourceLocation(Reference.MOD_ID + ":itemdisplaystand");
//        itemStandInvMrl = new ModelResourceLocation(Reference.MOD_ID + ":itemdisplaystand_inv");
//        unitStationNormalMrl = new ModelResourceLocation(Reference.MOD_ID + ":isunitstation");
//        unitStationInvMrl = new ModelResourceLocation(Reference.MOD_ID + ":isunitstation_inv");

        modelList.add(Pair.of(yukihiraNormalMrl, yukihiraInvMrl));
        modelList.add(Pair.of(reirakuNormalMrl, reirakuInvMrl));
        modelList.add(Pair.of(elucidatorNormalMrl, elucidatorInvMrl));
//        modelList.add(Pair.of(itemStandNormalMrl, itemStandInvMrl));
//        modelList.add(Pair.of(unitStationNormalMrl, unitStationInvMrl));

        // Set the custom models to the Inventory variants
        ModelLoader.setCustomModelResourceLocation(ModItems.yukihira, 0, yukihiraInvMrl);
        ModelLoader.setCustomModelResourceLocation(ModItems.yukihira, 1, reirakuInvMrl);
        ModelLoader.setCustomModelResourceLocation(ModItems.elucidator, 0, elucidatorInvMrl);
//        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.itemDisplay), 0, itemStandInvMrl);
//        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.unitStation), 0, unitStationInvMrl);

        // Register the 3D versions of each model
        ModelBakery.registerItemVariants(ModItems.yukihira, yukihiraNormalMrl);
        ModelBakery.registerItemVariants(ModItems.yukihira, reirakuNormalMrl);
        ModelBakery.registerItemVariants(ModItems.elucidator, elucidatorNormalMrl);
//        ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.itemDisplay), itemStandNormalMrl);
//        ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.unitStation), unitStationNormalMrl);
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
    }

    /*
     * Models are pairs as Pair<Normal variant, Inventory variant>
     *     Pass the same model to both sides of the Pair<> when there is not
     *     a special variant to be used.
     */
    private static ArrayList<Pair<ModelResourceLocation, ModelResourceLocation>> modelList = new ArrayList<>();
}
