package com.sparta.is.client.render;

import com.sparta.is.client.render.Items.PerspectiveModel;
import com.sparta.is.core.reference.Reference;
import com.sparta.is.core.utils.helpers.LogHelper;
import com.sparta.is.init.ModItems;
import net.minecraft.client.renderer.block.model.IBakedModel;
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

    private static ModelResourceLocation yukihiraNormalMrl;
    private static ModelResourceLocation yukihiraInvMrl;
    private static ModelResourceLocation reirakuMrl;
    private static ModelResourceLocation elucidatorNormalMrl;
    private static ModelResourceLocation elucidatorInvMrl;

    public static void register()
    {
        LogHelper.info("Registering Custom models...");
        MinecraftForge.EVENT_BUS.register(INSTANCE);

        yukihiraNormalMrl = new ModelResourceLocation(Reference.MOD_ID + ":yukihiranigata");
        yukihiraInvMrl = new ModelResourceLocation(Reference.MOD_ID + ":yukihiranigata_inv");
        reirakuMrl = new ModelResourceLocation(Reference.MOD_ID + ":reiraku");
        elucidatorNormalMrl = new ModelResourceLocation(Reference.MOD_ID + ":elucidator");
        elucidatorInvMrl = new ModelResourceLocation(Reference.MOD_ID + ":elucidator_inv");

        modelList.add(Pair.of(yukihiraNormalMrl, yukihiraInvMrl));
        modelList.add(Pair.of(reirakuMrl, reirakuMrl));
        modelList.add(Pair.of(elucidatorNormalMrl, elucidatorInvMrl));

        ModelLoader.setCustomModelResourceLocation(ModItems.yukihira, 0, yukihiraInvMrl);
        ModelLoader.setCustomModelResourceLocation(ModItems.yukihira, 1, reirakuMrl);
        ModelLoader.setCustomModelResourceLocation(ModItems.elucidator, 0, elucidatorInvMrl);
    }


    @SubscribeEvent
    public static void modelBake(ModelBakeEvent e)
    {
        LogHelper.info("\t\t\t\tBaking new models...");
        for(Pair<ModelResourceLocation, ModelResourceLocation> mrl : modelList)
        {
            Object obj = e.getModelRegistry().getObject(mrl.getLeft());

            if(obj instanceof IBakedModel)
            {
                LogHelper.info("Creating new Perspective model for " + mrl.getLeft() +"...");
                IBakedModel currentModel = (IBakedModel) obj;
                PerspectiveModel perspectiveModel = new PerspectiveModel(mrl.getLeft(), mrl.getRight(), currentModel, e.getModelRegistry().getObject(mrl.getRight()));
                e.getModelRegistry().putObject(mrl.getLeft(), perspectiveModel);
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
