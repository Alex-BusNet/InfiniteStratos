package com.sparta.is.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ItemModelYukihira extends ModelBase
{
    //fields
    ModelRenderer hilt;
    ModelRenderer PommelPiece1;
    ModelRenderer HandGuard;
    ModelRenderer HandGuardLeft;
    ModelRenderer BladePiece1;
    ModelRenderer BladePiece2;
    ModelRenderer BladePiece3;
    ModelRenderer BladePiece4;
    ModelRenderer BladePiece5;
    ModelRenderer BladePiece6;
    ModelRenderer BladePiece7;
    ModelRenderer BladePiece8;
    ModelRenderer BladePiece9;
    ModelRenderer BladePiece10;
    ModelRenderer BladePiece11;
    ModelRenderer BladePiece12;
    ModelRenderer BladePiece13;
    ModelRenderer BladePiece14;
    ModelRenderer BladePiece15;
    ModelRenderer BladePiece16;
    ModelRenderer BladePiece17;
    ModelRenderer BladePiece1Left;
    ModelRenderer BladePiece2Left;
    ModelRenderer BladePiece3Left;
    ModelRenderer BladePiece4Left;
    ModelRenderer BladePiece5Left;
    ModelRenderer BladePiece6Left;
    ModelRenderer BladePiece7Left;
    ModelRenderer BladePiece8Left;
    ModelRenderer BladePiece9Left;
    ModelRenderer BladePiece10Left;
    ModelRenderer BladePiece11Left;
    ModelRenderer BladePiece12Left;
    ModelRenderer BladePiece13Left;
    ModelRenderer RedPiece1;
    ModelRenderer RedPiece2;

    public ItemModelYukihira()
    {
        textureWidth = 128;
        textureHeight = 64;

        hilt = new ModelRenderer(this, 0, 0);
        hilt.addBox(-1.5F, 14F, 0F, 1, 1, 7);
        hilt.setRotationPoint(0F, 0F, 0F);
        hilt.setTextureSize(128, 64);
        hilt.mirror = true;
        setRotation(hilt, 0F, 0F, 0F);

        PommelPiece1 = new ModelRenderer(this, 0, 9);
        PommelPiece1.addBox(- 1.5F, 13.5F, -1.5F, 1, 2, 2);
        PommelPiece1.setRotationPoint(0F, 0F, 0F);
        PommelPiece1.setTextureSize(128, 64);
        PommelPiece1.mirror = true;
        setRotation(PommelPiece1, 0F, 0F, 0F);

        HandGuard = new ModelRenderer(this, 0, 26);
        HandGuard.addBox(-1.4F, 12.7F, 3.5F, 1, 3, 3);
        HandGuard.setRotationPoint(0F, 0F, 0F);
        HandGuard.setTextureSize(128, 64);
        HandGuard.mirror = true;
        setRotation(HandGuard, 0F, 0F, 0F);

        HandGuardLeft = new ModelRenderer(this, 0, 26);
        HandGuardLeft.addBox(-1.6F, 12.7F, 3.5F, 1, 3, 3);
        HandGuardLeft.setRotationPoint(0F, 0F, 0F);
        HandGuardLeft.setTextureSize(128, 64);
        HandGuardLeft.mirror = true;
        setRotation(HandGuardLeft, 0F, 0F, 0F);

        BladePiece1 = new ModelRenderer(this, 0, 20);
        BladePiece1.addBox(-1.4F, 11.5F, 6.5F, 1, 3, 1);
        BladePiece1.setRotationPoint(0F, 0F, 0F);
        BladePiece1.setTextureSize(128, 64);
        BladePiece1.mirror = true;
        setRotation(BladePiece1, 0F, 0F, 0F);

        BladePiece2 = new ModelRenderer(this, 0, 14);
        BladePiece2.addBox(-1.4F, 13.1F, 8.5F, 1, 1, 4);
        BladePiece2.setRotationPoint(0F, 0F, 0F);
        BladePiece2.setTextureSize(128, 64);
        BladePiece2.mirror = true;
        setRotation(BladePiece2, 0F, 0F, 0F);

        BladePiece3 = new ModelRenderer(this, 17, 0);
        BladePiece3.addBox(-1.4F, 14.8F, 6.5F, 1, 1, 6);
        BladePiece3.setRotationPoint(0F, 0F, 0F);
        BladePiece3.setTextureSize(128, 64);
        BladePiece3.mirror = true;
        setRotation(BladePiece3, 0F, 0F, 0F);

        BladePiece4 = new ModelRenderer(this, 5, 20);
        BladePiece4.addBox(-1.4F, 12.5F, 7.5F, 1, 2, 1);
        BladePiece4.setRotationPoint(0F, 0F, 0F);
        BladePiece4.setTextureSize(128, 64);
        BladePiece4.mirror = true;
        setRotation(BladePiece4, 0F, 0F, 0F);

        BladePiece5 = new ModelRenderer(this, 17, 8);
        BladePiece5.addBox(-1.4F, 14.5F, 8.8F, 1, 1, 4);
        BladePiece5.setRotationPoint(0F, 0F, 0F);
        BladePiece5.setTextureSize(128, 64);
        BladePiece5.mirror = true;
        setRotation(BladePiece5, 0F, 0F, 0F);

        BladePiece6 = new ModelRenderer(this, 24, 17);
        BladePiece6.addBox(-1.4F, 14.3F, 12.8F, 1, 1, 4);
        BladePiece6.setRotationPoint(0F, 0F, 0F);
        BladePiece6.setTextureSize(128, 64);
        BladePiece6.mirror = true;
        setRotation(BladePiece6, -0.0174533F, 0F, 0F);

        BladePiece7 = new ModelRenderer(this, 0, 14);
        BladePiece7.addBox(-1.4F, 13.3F, 12.5F, 1, 1, 4);
        BladePiece7.setRotationPoint(0F, 0F, 0F);
        BladePiece7.setTextureSize(128, 64);
        BladePiece7.mirror = true;
        setRotation(BladePiece7, 0F, 0F, 0F);

        BladePiece8 = new ModelRenderer(this, 18, 23);
        BladePiece8.addBox(-1.4F, 14.6F, 18F, 1, 1, 5);
        BladePiece8.setRotationPoint(0F, 0F, 0F);
        BladePiece8.setTextureSize(128, 64);
        BladePiece8.mirror = true;
        setRotation(BladePiece8, 0F, 0F, 0F);

        BladePiece9 = new ModelRenderer(this, 11, 23);
        BladePiece9.addBox(-1.4F, 14.6F, 16.5F, 1, 1, 2);
        BladePiece9.setRotationPoint(0F, 0F, 0F);
        BladePiece9.setTextureSize(128, 64);
        BladePiece9.mirror = true;
        setRotation(BladePiece9, 0F, 0F, 0F);

        BladePiece10 = new ModelRenderer(this, 11, 16);
        BladePiece10.addBox(-1.4F, 15.3F, 11.3F, 1, 1, 5);
        BladePiece10.setRotationPoint(0F, 0F, 0F);
        BladePiece10.setTextureSize(128, 64);
        BladePiece10.mirror = true;
        setRotation(BladePiece10, 0.0174533F, 0F, 0F);

        BladePiece11 = new ModelRenderer(this, 9, 30);
        BladePiece11.addBox(-1.4F, 13.4F, 16.5F, 1, 1, 8);
        BladePiece11.setRotationPoint(0F, 0F, 0F);
        BladePiece11.setTextureSize(128, 64);
        BladePiece11.mirror = true;
        setRotation(BladePiece11, 0F, 0F, 0F);

        BladePiece12 = new ModelRenderer(this, 5, 20);
        BladePiece12.addBox(-1.4F, 13.4F, 23.5F, 1, 2, 1);
        BladePiece12.setRotationPoint(0F, 0F, 0F);
        BladePiece12.setTextureSize(128, 64);
        BladePiece12.mirror = true;
        setRotation(BladePiece12, 0F, 0F, 0F);

        BladePiece13 = new ModelRenderer(this, 5, 20);
        BladePiece13.addBox(-1.4F, 13.3F, 24.5F, 1, 2, 1);
        BladePiece13.setRotationPoint(0F, 0F, 0F);
        BladePiece13.setTextureSize(128, 64);
        BladePiece13.mirror = true;
        setRotation(BladePiece13, 0F, 0F, 0F);

        BladePiece14 = new ModelRenderer(this, 38, 23);
        BladePiece14.addBox(-1.4F, -14.5F, 28.3F, 1, 3, 1);
        BladePiece14.setRotationPoint(0F, 0F, 0F);
        BladePiece14.setTextureSize(128, 64);
        BladePiece14.mirror = true;
        setRotation(BladePiece14, -0.8241259F, 0F, 0.0069813F);

        BladePiece15 = new ModelRenderer(this, 31, 23);
        BladePiece15.addBox(-1.5F, -16.8F, 23.8F, 1, 2, 2);
        BladePiece15.setRotationPoint(0F, 0F, 0F);
        BladePiece15.setTextureSize(128, 64);
        BladePiece15.mirror = true;
        setRotation(BladePiece15, -1.064651F, 0F, 0F);

        BladePiece16 = new ModelRenderer(this, 31, 23);
        BladePiece16.addBox(-1.5F, -18.4F, 24.0F, 1, 2, 2);
        BladePiece16.setRotationPoint(0F, 0F, 0F);
        BladePiece16.setTextureSize(128, 64);
        BladePiece16.mirror = true;
        setRotation(BladePiece16, -1.047198F, 0F, 0F);

        BladePiece17 = new ModelRenderer(this, 38, 23);
        BladePiece17.addBox(-1.4F, -20.4F, 24.1F, 1, 3, 1);
        BladePiece17.setRotationPoint(0F, 0F, 0F);
        BladePiece17.setTextureSize(128, 64);
        BladePiece17.mirror = true;
        setRotation(BladePiece17, -1.047198F, 0F, 0.0069813F);

        BladePiece1Left = new ModelRenderer(this, 0, 20);
        BladePiece1Left.addBox(-1.6F, 11.5F, 6.5F, 1, 3, 1);
        BladePiece1Left.setRotationPoint(0F, 0F, 0F);
        BladePiece1Left.setTextureSize(128, 64);
        BladePiece1Left.mirror = true;
        setRotation(BladePiece1Left, 0F, 0F, 0F);

        BladePiece2Left = new ModelRenderer(this, 0, 14);
        BladePiece2Left.addBox(-1.6F, 13.1F, 8.5F, 1, 1, 4);
        BladePiece2Left.setRotationPoint(0F, 0F, 0F);
        BladePiece2Left.setTextureSize(128, 64);
        BladePiece2Left.mirror = true;
        setRotation(BladePiece2Left, 0F, 0F, 0F);

        BladePiece3Left = new ModelRenderer(this, 17, 0);
        BladePiece3Left.addBox(-1.6F, 14.8F, 6.5F, 1, 1, 6);
        BladePiece3Left.setRotationPoint(0F, 0F, 0F);
        BladePiece3Left.setTextureSize(128, 64);
        BladePiece3Left.mirror = true;
        setRotation(BladePiece3Left, 0F, 0F, 0F);

        BladePiece4Left = new ModelRenderer(this, 5, 20);
        BladePiece4Left.addBox(-1.6F, 12.5F, 7.5F, 1, 2, 1);
        BladePiece4Left.setRotationPoint(0F, 0F, 0F);
        BladePiece4Left.setTextureSize(128, 64);
        BladePiece4Left.mirror = true;
        setRotation(BladePiece4Left, 0F, 0F, 0F);

        BladePiece5Left = new ModelRenderer(this, 17, 8);
        BladePiece5Left.addBox(-1.6F, 14.5F, 8.8F, 1, 1, 4);
        BladePiece5Left.setRotationPoint(0F, 0F, 0F);
        BladePiece5Left.setTextureSize(128, 64);
        BladePiece5Left.mirror = true;
        setRotation(BladePiece5Left, 0F, 0F, 0F);

        BladePiece6Left = new ModelRenderer(this, 24, 17);
        BladePiece6Left.addBox(-1.6F, 14.3F, 12.8F, 1, 1, 4);
        BladePiece6Left.setRotationPoint(0F, 0F, 0F);
        BladePiece6Left.setTextureSize(128, 64);
        BladePiece6Left.mirror = true;
        setRotation(BladePiece6Left, -0.0174533F, 0F, 0F);

        BladePiece7Left = new ModelRenderer(this, 0, 14);
        BladePiece7Left.addBox(-1.6F, 13.3F, 12.5F, 1, 1, 4);
        BladePiece7Left.setRotationPoint(0F, 0F, 0F);
        BladePiece7Left.setTextureSize(128, 64);
        BladePiece7Left.mirror = true;
        setRotation(BladePiece7Left, 0F, 0F, 0F);

        BladePiece8Left = new ModelRenderer(this, 18, 23);
        BladePiece8Left.addBox(-1.6F, 14.6F, 18F, 1, 1, 5);
        BladePiece8Left.setRotationPoint(0F, 0F, 0F);
        BladePiece8Left.setTextureSize(128, 64);
        BladePiece8Left.mirror = true;
        setRotation(BladePiece8Left, 0F, 0F, 0F);

        BladePiece9Left = new ModelRenderer(this, 11, 23);
        BladePiece9Left.addBox(-1.6F, 14.6F, 16.5F, 1, 1, 2);
        BladePiece9Left.setRotationPoint(0F, 0F, 0F);
        BladePiece9Left.setTextureSize(128, 64);
        BladePiece9Left.mirror = true;
        setRotation(BladePiece9Left, 0F, 0F, 0F);

        BladePiece10Left = new ModelRenderer(this, 11, 16);
        BladePiece10Left.addBox(-1.6F, 15.3F, 11.3F, 1, 1, 5);
        BladePiece10Left.setRotationPoint(0F, 0F, 0F);
        BladePiece10Left.setTextureSize(128, 64);
        BladePiece10Left.mirror = true;
        setRotation(BladePiece10Left, 0.0174533F, 0F, 0F);

        BladePiece11Left = new ModelRenderer(this, 9, 30);
        BladePiece11Left.addBox(-1.6F, 13.4F, 16.5F, 1, 1, 8);
        BladePiece11Left.setRotationPoint(0F, 0F, 0F);
        BladePiece11Left.setTextureSize(128, 64);
        BladePiece11Left.mirror = true;
        setRotation(BladePiece11Left, 0F, 0F, 0F);

        BladePiece12Left = new ModelRenderer(this, 5, 20);
        BladePiece12Left.addBox(-1.6F, 13.4F, 23.5F, 1, 2, 1);
        BladePiece12Left.setRotationPoint(0F, 0F, 0F);
        BladePiece12Left.setTextureSize(128, 64);
        BladePiece12Left.mirror = true;
        setRotation(BladePiece12Left, 0F, 0F, 0F);

        BladePiece13Left = new ModelRenderer(this, 5, 20);
        BladePiece13Left.addBox(-1.6F, 13.3F, 24.5F, 1, 2, 1);
        BladePiece13Left.setRotationPoint(0F, 0F, 0F);
        BladePiece13Left.setTextureSize(128, 64);
        BladePiece13Left.mirror = true;
        setRotation(BladePiece13Left, 0F, 0F, 0F);

        RedPiece1 = new ModelRenderer(this, 35, 0);
        RedPiece1.addBox(-1.5F, 14F, 6.5F, 1, 1, 17);
        RedPiece1.setRotationPoint(0F, 0F, 0F);
        RedPiece1.setTextureSize(128, 64);
        RedPiece1.mirror = true;
        setRotation(RedPiece1, 0F, 0F, 0F);

        RedPiece2 = new ModelRenderer(this, 35, 19);
        RedPiece2.addBox(-1.5F, 18.6F, 19.5F, 1, 1, 1);
        RedPiece2.setRotationPoint(0F, 0F, 0F);
        RedPiece2.setTextureSize(128, 64);
        RedPiece2.mirror = true;
        setRotation(RedPiece2, 0.1919862F, 0F, 0F);

        this.hilt.addChild(this.PommelPiece1);
        this.hilt.addChild(this.HandGuard);
        this.hilt.addChild(this.HandGuardLeft);
        this.hilt.addChild(this.BladePiece1);
        this.hilt.addChild(this.BladePiece1Left);
        this.hilt.addChild(this.BladePiece2);
        this.hilt.addChild(this.BladePiece2Left);
        this.hilt.addChild(this.BladePiece3);
        this.hilt.addChild(this.BladePiece3Left);
        this.hilt.addChild(this.BladePiece4);
        this.hilt.addChild(this.BladePiece4Left);
        this.hilt.addChild(this.BladePiece5);
        this.hilt.addChild(this.BladePiece5Left);
        this.hilt.addChild(this.BladePiece6);
        this.hilt.addChild(this.BladePiece6Left);
        this.hilt.addChild(this.BladePiece7);
        this.hilt.addChild(this.BladePiece7Left);
        this.hilt.addChild(this.BladePiece8);
        this.hilt.addChild(this.BladePiece8Left);
        this.hilt.addChild(this.BladePiece9);
        this.hilt.addChild(this.BladePiece10);
        this.hilt.addChild(this.BladePiece10Left);
        this.hilt.addChild(this.BladePiece11);
        this.hilt.addChild(this.BladePiece11Left);
        this.hilt.addChild(this.BladePiece12);
        this.hilt.addChild(this.BladePiece12Left);
        this.hilt.addChild(this.BladePiece13);
        this.hilt.addChild(this.BladePiece13Left);
        this.hilt.addChild(this.BladePiece14);
        this.hilt.addChild(this.BladePiece15);
        this.hilt.addChild(this.BladePiece16);
        this.hilt.addChild(this.BladePiece17);
        this.hilt.addChild(this.RedPiece1);
        this.hilt.addChild(this.RedPiece2);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        hilt.render(f5);
        PommelPiece1.render(f5);
        HandGuard.render(f5);
        HandGuardLeft.render(f5);
        BladePiece1.render(f5);
        BladePiece2.render(f5);
        BladePiece3.render(f5);
        BladePiece4.render(f5);
        BladePiece5.render(f5);
        BladePiece6.render(f5);
        BladePiece7.render(f5);
        BladePiece8.render(f5);
        BladePiece9.render(f5);
        BladePiece10.render(f5);
        BladePiece11.render(f5);
        BladePiece12.render(f5);
        BladePiece13.render(f5);
        BladePiece14.render(f5);
        BladePiece15.render(f5);
        BladePiece16.render(f5);
        BladePiece17.render(f5);
        BladePiece1Left.render(f5);
        BladePiece2Left.render(f5);
        BladePiece3Left.render(f5);
        BladePiece4Left.render(f5);
        BladePiece5Left.render(f5);
        BladePiece6Left.render(f5);
        BladePiece7Left.render(f5);
        BladePiece8Left.render(f5);
        BladePiece9Left.render(f5);
        BladePiece10Left.render(f5);
        BladePiece11Left.render(f5);
        BladePiece12Left.render(f5);
        BladePiece13Left.render(f5);
        RedPiece1.render(f5);
        RedPiece2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
