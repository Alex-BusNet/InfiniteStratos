package com.sparta.is.armor.models;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.sparta.is.client.render.ItemRenderRegistry;
import com.sparta.is.core.client.model.BakedISUnitModel;
import com.sparta.is.core.client.model.format.Armament;
import com.sparta.is.core.utils.helpers.LogHelper;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ModelUnit extends ModelBiped implements IModel
{
//    public ModelRenderer unitHead;
//    public ModelRenderer unitBody;
//    public ModelRenderer[] leftUnitArm;
//    public ModelRenderer[] rightUnitArm;
//    public ModelRenderer leftUnitLeg;
//    public ModelRenderer rightUnitLeg;
//    public ModelRenderer[] rightUnitWing;
//    public ModelRenderer[] leftUnitWing;
//
//    public ModelBiped.ArmPose leftArmPose;
//    public ModelBiped.ArmPose rightArmPose;
//    public boolean isSneak;

    private Map<String, Map<String, Armament>> models = Maps.newHashMap();
    private final String unitName;

    public ModelUnit(String name)
    {
        unitName = name;
    }

    public void addModelForVariant(String variant, Map<String, Armament> model)
    {
        models.put(variant, model);
    }

    @Override
    public Collection<ResourceLocation> getDependencies()
    {
        return ImmutableList.of();
    }

    @Override
    public Collection<ResourceLocation> getTextures()
    {
        ImmutableSet.Builder<ResourceLocation> builder = ImmutableSet.builder();

        List<ResourceLocation> cache = new ArrayList<>();
        // Retrieve all textures for each model.
        for(Map<String, Armament> model : models.values())
        {
            for(Armament a : model.values())
            {
                for(Map.Entry<String, Map<String, ResourceLocation>> textures : a.textures.entrySet())
                {
                    for(Map.Entry<String, ResourceLocation> texture : textures.getValue().entrySet())
                    {
                        if ( ! cache.contains(texture.getValue()) )
                        {
                            cache.add(texture.getValue());
                            builder.add(texture.getValue());
                        }
                    }
                }
            }
        }

        return builder.build();
    }

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter)
    {
        List<BakedQuad> quads = new ArrayList<>();
        FaceBakery faceBakery = new FaceBakery();
        BakedISUnitModel bakedISUnitModel = new BakedISUnitModel();
        for(Map.Entry<String, Map<String, Armament>> entry : models.entrySet())
        {
            LogHelper.debug("Baking " + entry.getKey() + " state...");

            for(Armament a : entry.getValue().values())
            {
                LogHelper.debug("\tBaking " + a.armamentName + "...");

                for( Map.Entry<String, Armament.ArmamentBlock> aEntry : a.subblocks.entrySet())
                {
                    LogHelper.debug("\t\tBaking subblock " + aEntry.getKey() + "...");

                    Armament.ArmamentBlock ab = aEntry.getValue();
                    for(Map.Entry<EnumFacing, Pair<String, BlockFaceUV>> abEntry : ab.getFaces().entrySet())
                    {
                        String abTexture = a.textures.get(entry.getKey()).get(abEntry.getValue().getLeft()).toString();
                        BlockPartFace bpf = new BlockPartFace(abEntry.getKey(), -1, abTexture, abEntry.getValue().getRight());

                        BakedQuad quad = faceBakery.makeBakedQuad(ab.getFrom(), ab.getTo(), bpf,
                                bakedTextureGetter.apply(a.textures.get(entry.getKey()).get(abEntry.getValue().getLeft())),
                                abEntry.getKey(), ModelRotation.X0_Y0, ab.getRotation(), false, false);

                        quads.add(quad);
                    }


                    LogHelper.debug("\t\tFinished baking subblock " + aEntry.getKey());
                }

                bakedISUnitModel.addQuads(entry.getKey(), a.armamentName, quads);
                quads.clear();

                LogHelper.debug("\tFinished baking " + a.armamentName);
            }

            LogHelper.debug("Finished baking " + entry.getKey() + " state");
        }

        // The ModelResourceLocation generated here MUST match the pre-defined one in ItemRenderRegistry
        ItemRenderRegistry.registerUnitModel(bakedISUnitModel, ItemRenderRegistry.getUnitInvMrl(unitName));

        return bakedISUnitModel;
//        throw new UnsupportedOperationException("The Unit model is not built to be used as an item model.");
    }

    @Override
    public IModelState getDefaultState()
    {
        return TRSRTransformation.identity();
    }

    public ModelBiped getArmorForState(String unitState, ModelBiped mb)
    {
        Map<String, Armament> armamentMap = models.get(unitState);

        for(Map.Entry<String, Armament> entry : armamentMap.entrySet())
        {
            ModelRenderer armamentRenderer = new ModelRenderer(this, entry.getValue().armamentName);
            for( Map.Entry<String, Armament.ArmamentBlock> abe : entry.getValue().subblocks.entrySet())
            {
                Armament.ArmamentBlock ab = abe.getValue();
                float w = ab.getTo().x - ab.getFrom().x;
                float h = ab.getTo().y - ab.getFrom().y;
                float d = ab.getTo().z - ab.getFrom().z;
                armamentRenderer.addBox(abe.getKey(), ab.getFrom().x, ab.getFrom().y, ab.getFrom().z, (int)w, (int)h, (int)d);
                armamentRenderer.setRotationPoint(0F, 0F, 0F);
            }

            addToModel(mb, armamentRenderer, entry.getValue().armamentName);
        }

        return this;
    }

    private void addToModel(ModelBiped mb, ModelRenderer child, String sectionName)
    {
        if(sectionName.equals("headpiece"))
        {
            mb.bipedHead.addChild(child);
        }
        else if(sectionName.equals("chest") || sectionName.equals("leftwing") || sectionName.equals("rightwing"))
        {
            mb.bipedBody.addChild(child);
        }
        else if(sectionName.equals("leftleg"))
        {
            mb.bipedLeftLeg.addChild(child);
        }
        else if(sectionName.equals("rightleg"))
        {
            mb.bipedRightLeg.addChild(child);
        }
        else if(sectionName.equals("rightarm"))
        {
            mb.bipedRightArm.addChild(child);
        }
        else if(sectionName.equals("leftarm"))
        {
            mb.bipedLeftArm.addChild(child);
        }
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    /*
     *
     * Old code used for rendering. May be moved to new location
     *
     */
//    @Override
//    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
//    {
//        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
//        GlStateManager.pushMatrix();
//
//        if (this.isChild)
//        {
//            float f = 2.0F;
//            GlStateManager.scale(0.75F, 0.75F, 0.75F);
//            GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
//            this.unitHead.render(scale);
//            GlStateManager.popMatrix();
//            GlStateManager.pushMatrix();
//            GlStateManager.scale(0.5F, 0.5F, 0.5F);
//            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
//            this.unitBody.render(scale);
//            for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].render(scale); }
//            for(int i = 0; i < this.leftUnitArm.length; i++) { this.leftUnitArm[i].render(scale); }
//            this.rightUnitLeg.render(scale);
//            this.leftUnitLeg.render(scale);
//        }
//        else
//        {
//            if (entityIn.isSneaking())
//            {
//                GlStateManager.translate(0.0F, 0.2F, 0.0F);
//            }
//
//            this.unitHead.render(scale);
//            this.unitBody.render(scale);
//            for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].render(scale); }
//            for(int i = 0; i < this.leftUnitArm.length; i++) { this.leftUnitArm[i].render(scale); }
//            this.rightUnitLeg.render(scale);
//            this.leftUnitLeg.render(scale);
//        }
//
//        GlStateManager.popMatrix();
//    }
//
//    @Override
//    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
//    {
//        boolean flag = entityIn instanceof EntityLivingBase && ((EntityLivingBase)entityIn).getTicksElytraFlying() > 4;
//        this.unitHead.rotateAngleY = netHeadYaw * 0.017453292F;
//
//        if (flag)
//        {
//            this.unitHead.rotateAngleX = -((float)Math.PI / 4F);
//        }
//        else
//        {
//            this.unitHead.rotateAngleX = headPitch * 0.017453292F;
//        }
//
//        this.unitBody.rotateAngleY = 0.0F;
//        for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].rotationPointZ = 0.0F; }
//        for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].rotationPointX = -5.0F; }
//        for(int i = 0; i < this.leftUnitArm.length; i++) { this.leftUnitArm[i].rotationPointZ = 0.0F; }
//        for(int i = 0; i < this.leftUnitArm.length; i++) { this.leftUnitArm[i].rotationPointX = 5.0F; }
//        float f = 1.0F;
//
//        if (flag)
//        {
//            f = (float)(entityIn.motionX * entityIn.motionX + entityIn.motionY * entityIn.motionY + entityIn.motionZ * entityIn.motionZ);
//            f = f / 0.2F;
//            f = f * f * f;
//        }
//
//        if (f < 1.0F)
//        {
//            f = 1.0F;
//        }
//
//        for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;}
//        for(int i = 0; i < this.leftUnitArm.length; i++) {this.leftUnitArm[i].rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f; }
//        for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].rotateAngleZ = 0.0F;}
//        for(int i = 0; i < this.leftUnitArm.length; i++) {this.leftUnitArm[i].rotateAngleZ = 0.0F; }
//
//        this.rightUnitLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
//        this.leftUnitLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
//        this.rightUnitLeg.rotateAngleY = 0.0F;
//        this.leftUnitLeg.rotateAngleY = 0.0F;
//        this.rightUnitLeg.rotateAngleZ = 0.0F;
//        this.leftUnitLeg.rotateAngleZ = 0.0F;
//
//        if (this.isRiding)
//        {
//            for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].rotateAngleX += -((float)Math.PI / 5F);}
//            for(int i = 0; i < this.leftUnitArm.length; i++) { this.leftUnitArm[i].rotateAngleX += -((float)Math.PI / 5F); }
//            this.rightUnitLeg.rotateAngleX = -1.4137167F;
//            this.rightUnitLeg.rotateAngleY = ((float)Math.PI / 10F);
//            this.rightUnitLeg.rotateAngleZ = 0.07853982F;
//            this.leftUnitLeg.rotateAngleX = -1.4137167F;
//            this.leftUnitLeg.rotateAngleY = -((float)Math.PI / 10F);
//            this.leftUnitLeg.rotateAngleZ = -0.07853982F;
//        }
//
//            for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].rotateAngleY = 0.0F;}
//            for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].rotateAngleZ = 0.0F;}
//
//        switch (this.leftArmPose)
//        {
//            case EMPTY:
//                for(int i = 0; i < this.leftUnitArm.length; i++) { this.leftUnitArm[i].rotateAngleY = 0.0F; }
//                break;
//            case BLOCK:
//                for(int i = 0; i < this.leftUnitArm.length; i++) { this.leftUnitArm[i].rotateAngleX = this.leftUnitArm[i].rotateAngleX * 0.5F - 0.9424779F; }
//                for(int i = 0; i < this.leftUnitArm.length; i++) { this.leftUnitArm[i].rotateAngleY = 0.5235988F; }
//                break;
//            case ITEM:
//                for(int i = 0; i < this.leftUnitArm.length; i++) { this.leftUnitArm[i].rotateAngleX = this.leftUnitArm[i].rotateAngleX * 0.5F - ((float)Math.PI / 10F); }
//                for(int i = 0; i < this.leftUnitArm.length; i++) { this.leftUnitArm[i].rotateAngleY = 0.0F; }
//        }
//
//        switch (this.rightArmPose)
//        {
//            case EMPTY:
//                for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].rotateAngleY = 0.0F; }
//                break;
//            case BLOCK:
//                for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].rotateAngleX = this.rightUnitArm[i].rotateAngleX * 0.5F - 0.9424779F;}
//                for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].rotateAngleY = -0.5235988F;}
//                break;
//            case ITEM:
//                for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].rotateAngleX = this.rightUnitArm[i].rotateAngleX * 0.5F - ((float)Math.PI / 10F); }
//                for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].rotateAngleY = 0.0F;}
//        }
//
//        if (this.swingProgress > 0.0F)
//        {
//            EnumHandSide enumhandside = this.getMainHand(entityIn);
//            ModelRenderer[] modelrenderer = this.getArmForSide(enumhandside);
//            float f1 = this.swingProgress;
//            this.unitBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float)Math.PI * 2F)) * 0.2F;
//
//            if (enumhandside == EnumHandSide.LEFT)
//            {
//                this.unitBody.rotateAngleY *= -1.0F;
//            }
//
//            for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].rotationPointZ = MathHelper.sin(this.unitBody.rotateAngleY) * 5.0F; }
//            for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].rotationPointX = -MathHelper.cos(this.unitBody.rotateAngleY) * 5.0F; }
//            for(int i = 0; i < this.leftUnitArm.length; i++) { this.leftUnitArm[i].rotationPointZ = -MathHelper.sin(this.unitBody.rotateAngleY) * 5.0F; }
//            for(int i = 0; i < this.leftUnitArm.length; i++) { this.leftUnitArm[i].rotationPointX = MathHelper.cos(this.unitBody.rotateAngleY) * 5.0F; }
//            for(int i = 0; i < this.rightUnitArm.length; i++) {  this.rightUnitArm[i].rotateAngleY += this.unitBody.rotateAngleY; }
//            for(int i = 0; i < this.leftUnitArm.length; i++) { this.leftUnitArm[i].rotateAngleY += this.unitBody.rotateAngleY; }
//            for(int i = 0; i < this.leftUnitArm.length; i++) { this.leftUnitArm[i].rotateAngleX += this.unitBody.rotateAngleY; }
//
//            f1 = 1.0F - this.swingProgress;
//            f1 = f1 * f1;
//            f1 = f1 * f1;
//            f1 = 1.0F - f1;
//            float f2 = MathHelper.sin(f1 * (float)Math.PI);
//            float f3 = MathHelper.sin(this.swingProgress * (float)Math.PI) * -(this.unitHead.rotateAngleX - 0.7F) * 0.75F;
//
//            for(int i = 0; i < modelrenderer.length; i++)
//            {
//                modelrenderer[i].rotateAngleX = (float) ((double) modelrenderer[i].rotateAngleX - ((double) f2 * 1.2D + (double) f3));
//                modelrenderer[i].rotateAngleY += this.unitBody.rotateAngleY * 2.0F;
//                modelrenderer[i].rotateAngleZ += MathHelper.sin(this.swingProgress * (float) Math.PI) * - 0.4F;
//            }
//        }
//
//        if (this.isSneak)
//        {
//            this.unitBody.rotateAngleX = 0.5F;
//            for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].rotateAngleX += 0.4F; }
//            for(int i = 0; i < this.leftUnitArm.length; i++) { this.leftUnitArm[i].rotateAngleX += 0.4F; }
//            this.rightUnitLeg.rotationPointZ = 4.0F;
//            this.leftUnitLeg.rotationPointZ = 4.0F;
//            this.rightUnitLeg.rotationPointY = 9.0F;
//            this.leftUnitLeg.rotationPointY = 9.0F;
//            this.unitHead.rotationPointY = 1.0F;
//        }
//        else
//        {
//            this.unitBody.rotateAngleX = 0.0F;
//            this.rightUnitLeg.rotationPointZ = 0.1F;
//            this.leftUnitLeg.rotationPointZ = 0.1F;
//            this.rightUnitLeg.rotationPointY = 12.0F;
//            this.leftUnitLeg.rotationPointY = 12.0F;
//            this.unitHead.rotationPointY = 0.0F;
//        }
//
//        for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F; }
//        for(int i = 0; i < this.leftUnitArm.length; i++) { this.leftUnitArm[i].rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F; }
//        for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F; }
//        for(int i = 0; i < this.leftUnitArm.length; i++) { this.leftUnitArm[i].rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F; }
//
//        if (this.rightArmPose == ModelBiped.ArmPose.BOW_AND_ARROW)
//        {
//            for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].rotateAngleY = -0.1F + this.unitHead.rotateAngleY; }
//            for(int i = 0; i < this.leftUnitArm.length; i++) { this.leftUnitArm[i].rotateAngleY = 0.1F + this.unitHead.rotateAngleY + 0.4F; }
//            for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].rotateAngleX = -((float)Math.PI / 2F) + this.unitHead.rotateAngleX; }
//            for(int i = 0; i < this.leftUnitArm.length; i++) { this.leftUnitArm[i].rotateAngleX = -((float)Math.PI / 2F) + this.unitHead.rotateAngleX; }
//        }
//        else if (this.leftArmPose == ModelBiped.ArmPose.BOW_AND_ARROW)
//        {
//            for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].rotateAngleY = -0.1F + this.unitHead.rotateAngleY - 0.4F; }
//            for(int i = 0; i < this.leftUnitArm.length; i++) { this.leftUnitArm[i].rotateAngleY = 0.1F + this.unitHead.rotateAngleY; }
//            for(int i = 0; i < this.rightUnitArm.length; i++) { this.rightUnitArm[i].rotateAngleX = -((float)Math.PI / 2F) + this.unitHead.rotateAngleX; }
//            for(int i = 0; i < this.leftUnitArm.length; i++) { this.leftUnitArm[i].rotateAngleX = -((float)Math.PI / 2F) + this.unitHead.rotateAngleX; }
//        }
//
////        copyModelAngles(this.unitHead, this.bipedHeadwear);
//    }

//    protected ModelRenderer[] getArmForSide(EnumHandSide side)
//    {
//        return side == EnumHandSide.LEFT ? this.leftUnitArm : this.rightUnitArm;
//    }
//
//    protected EnumHandSide getMainHand(Entity entityIn)
//    {
//        if (entityIn instanceof EntityLivingBase)
//        {
//            EntityLivingBase entitylivingbase = (EntityLivingBase)entityIn;
//            EnumHandSide enumhandside = entitylivingbase.getPrimaryHand();
//            return entitylivingbase.swingingHand == EnumHand.MAIN_HAND ? enumhandside : enumhandside.opposite();
//        }
//        else
//        {
//            return EnumHandSide.RIGHT;
//        }
//    }

}
