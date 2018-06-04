package com.sparta.is.armor.models;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.sparta.is.core.armor.IISUnit;
import com.sparta.is.core.client.model.format.Armament;
import com.sparta.is.init.ModItems;
import gnu.trove.map.hash.THashMap;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ModelUnit implements IModel
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

    private Map<String, Armament[]> models = new THashMap<>();

    public ModelUnit()
    {

    }

    public void addModelForVariant(String variant, Armament[] model)
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
        for(Armament[] model : models.values())
        {
            for(Armament a : model)
            {
                for(ResourceLocation texture : a.textures.values())
                {
                    if(!cache.contains(texture))
                    {
                        cache.add(texture);
                        builder.add(texture);
                    }
                }
            }
        }

        return builder.build();
    }

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter)
    {
        throw new UnsupportedOperationException("The Unit model is not built to be used as an item model.");
    }

    public Map<String, IBakedModel> bakeModels(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter)
    {
        Map<String, IBakedModel> bakedModels = new THashMap<>();

        // Scale unit
        //float scale = 0.025f;
        //ITransformation transformation = new TRSRTransformation(new Vector3f(0, 0, 0.0001f - scale / 2f), null, new Vector3f(1, 1, 1f + scale), null);

        for(Map.Entry<String, Armament[]> entry : models.entrySet())
        {
            IISUnit unit = ModItems.getUnit(entry.getKey());
            if(unit != null && unit.hasTexturePerMaterial())
            {

            }
            else
            {
//                IModel model = ItemLayerModel.INSTANCE.retexture(ImmutableMap.of("layer0", entry.getValue()));
//                IBakedModel bakedModel = model.bake(state, format, bakedTextureGetter);
//                bakedModels.put(entry.getKey(), bakedModel);
            }
        }

        return bakedModels;
    }

    @Override
    public IModelState getDefaultState()
    {
        return TRSRTransformation.identity();
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
