package com.sparta.is.armor.units;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Byakushiki extends ModelBiped
{
    private ModelRenderer RightShoulderGuardTop;
    private ModelRenderer RightShoulderGuardFrontBack;
    private ModelRenderer RightFrontIncline1;
    private ModelRenderer RightFrontIncline2;
    private ModelRenderer RightFrontIncline3;
    private ModelRenderer RightFrontIncline4;
    private ModelRenderer RightFrontIncline5;
    private ModelRenderer RightBackIncline1;
    private ModelRenderer RightBackIncline2;
    private ModelRenderer RightBackIncline3;
    private ModelRenderer RightBackIncline4;
    private ModelRenderer RightBackIncline5;
    private ModelRenderer RightJointGuard1;
    private ModelRenderer RightJointGuard2;
    private ModelRenderer RightJointGuard3;
    private ModelRenderer BackTop;
    private ModelRenderer BackMiddle;
    private ModelRenderer Abdomen;
    private ModelRenderer FrontMiddleRight;
    private ModelRenderer FrontMiddleLeft;
    private ModelRenderer Waist;
    private ModelRenderer LeftArmBase;
    private ModelRenderer LeftArmVent;
    private ModelRenderer LeftArmGuardTop;
    private ModelRenderer LeftArmGuardFront;
    private ModelRenderer LeftArmGuardBack;
    private ModelRenderer RightArmBase;
    private ModelRenderer RightArmVent;
    private ModelRenderer RightArmGuardTop;
    private ModelRenderer RightArmGuardFront;
    private ModelRenderer RightArmGuardBack;
    private ModelRenderer LeftLegBase;
    private ModelRenderer LeftLegBaseSide;
    private ModelRenderer LeftLegBaseFrontTop;
    private ModelRenderer LeftLegBaseInside;
    private ModelRenderer LeftLegBaseOutside;
    private ModelRenderer LeftFootJoint1;
    private ModelRenderer LeftFootJoint2;
    private ModelRenderer LeftFootJoint3;
    private ModelRenderer LeftFootJoint4;
    private ModelRenderer RightLegBase;
    private ModelRenderer RightLegBaseSide;
    private ModelRenderer RightLegBaseFrontTop;
    private ModelRenderer RightLegBaseInside;
    private ModelRenderer RightLegBaseOutside;
    private ModelRenderer RightFootJoint1;
    private ModelRenderer RightFootJoint2;
    private ModelRenderer RightFootJoint3;
    private ModelRenderer RightFootJoint4;
    private ModelRenderer HelmetBack;
    private ModelRenderer HelmetLeft;
    private ModelRenderer HelmetRight;
    private ModelRenderer LeftShoulderGuardTop;
    private ModelRenderer LeftShoulderGuardFrontBack;
    private ModelRenderer LeftFrontIncline1;
    private ModelRenderer LeftFrontIncline2;
    private ModelRenderer LeftFrontIncline3;
    private ModelRenderer LeftFrontIncline4;
    private ModelRenderer LeftFrontIncline5;
    private ModelRenderer LeftBackIncline1;
    private ModelRenderer LeftBackIncline2;
    private ModelRenderer LeftBackIncline3;
    private ModelRenderer LeftBackIncline4;
    private ModelRenderer LeftBackIncline5;
    private ModelRenderer LeftJointGuard1;
    private ModelRenderer LeftJointGuard2;
    private ModelRenderer LeftJointGuard3;
    private ModelRenderer LeftWingPiece1;
    private ModelRenderer LeftWingPiece2;
    private ModelRenderer LeftWingPiece3;
    private ModelRenderer LeftWingPiece4;
    private ModelRenderer LeftWingPiece5;
    private ModelRenderer LeftWingPiece6;
    private ModelRenderer LeftWingPiece7;
    private ModelRenderer LeftWingPiece8;
    private ModelRenderer LeftWingPiece9;
    private ModelRenderer LeftWingPiece10;
    private ModelRenderer LeftWingPiece11;
    private ModelRenderer LeftWingPiece12;
    private ModelRenderer LeftWingPiece13;
    private ModelRenderer LeftWingPiece14;
    private ModelRenderer LeftWingPiece15;
    private ModelRenderer LeftWingPiece16;
    private ModelRenderer LeftWingPiece17;
    private ModelRenderer LeftWingPiece18;
    private ModelRenderer LeftWingPiece19;
    private ModelRenderer RightWingPiece1;
    private ModelRenderer RightWingPiece2;
    private ModelRenderer RightWingPiece3;
    private ModelRenderer RightWingPiece4;
    private ModelRenderer RightWingPiece5;
    private ModelRenderer RightWingPiece6;
    private ModelRenderer RightWingPiece7;
    private ModelRenderer RightWingPiece8;
    private ModelRenderer RightWingPiece9;
    private ModelRenderer RightWingPiece10;
    private ModelRenderer RightWingPiece11;
    private ModelRenderer RightWingPiece12;
    private ModelRenderer RightWingPiece13;
    private ModelRenderer RightWingPiece14;
    private ModelRenderer RightWingPiece15;
    private ModelRenderer RightWingPiece16;
    private ModelRenderer RightWingPiece17;
    private ModelRenderer RightWingPiece18;
    private ModelRenderer RightWingPiece19;

    public Byakushiki(float f, boolean partial)
    {
        super(f, 0, 256, 256);

        textureWidth = 256;
        textureHeight = 256;

        /*
         *  If armorState is set to Full deploy, then render everything
         */
        if(!partial)
        {
            RightShoulderGuardTop = new ModelRenderer(this, 0, 34);
            RightShoulderGuardTop.mirror = true;
            RightShoulderGuardTop.addBox(- 8.5F, - 3F, - 3F, 8, 1, 6);
            RightShoulderGuardTop.setRotationPoint(0F, 0F, 0F);
            RightShoulderGuardTop.setTextureSize(256, 256);
            RightShoulderGuardTop.setTextureOffset(0, 34);
            RightShoulderGuardTop.mirror = true;
            setRotation(RightShoulderGuardTop, 0F, 0F, 0.1745329F);
            RightShoulderGuardTop.mirror = false;

            RightShoulderGuardFrontBack = new ModelRenderer(this, 0, 43);
            RightShoulderGuardFrontBack.addBox(- 2.5F, - 2.5F, - 3.5F, 3, 3, 7);
            RightShoulderGuardFrontBack.setRotationPoint(0F, 0F, 0F);
            RightShoulderGuardFrontBack.setTextureSize(256, 256);
            RightShoulderGuardFrontBack.setTextureOffset(0, 43);
            RightShoulderGuardFrontBack.mirror = true;
            setRotation(RightShoulderGuardFrontBack, 0F, 0F, 0F);

            RightFrontIncline1 = new ModelRenderer(this, 32, 34);
            RightFrontIncline1.addBox(- 5F, - 1.5F, - 3F, 5, 1, 1);
            RightFrontIncline1.setRotationPoint(0F, 0F, 0F);
            RightFrontIncline1.setTextureSize(256, 256);
            RightFrontIncline1.setTextureOffset(32, 34);
            RightFrontIncline1.mirror = true;
            setRotation(RightFrontIncline1, 0F, 0F, 0F);

            RightFrontIncline2 = new ModelRenderer(this, 32, 37);
            RightFrontIncline2.addBox(- 5.5F, - 2F, - 3F, 5, 1, 1);
            RightFrontIncline2.setRotationPoint(0F, 0F, 0F);
            RightFrontIncline2.setTextureSize(256, 256);
            RightFrontIncline2.setTextureOffset(32, 37);
            RightFrontIncline2.mirror = true;
            setRotation(RightFrontIncline2, 0F, 0F, 0F);

            RightFrontIncline3 = new ModelRenderer(this, 32, 40);
            RightFrontIncline3.addBox(- 6F, - 2.5F, - 3F, 5, 1, 1);
            RightFrontIncline3.setRotationPoint(0F, 0F, 0F);
            RightFrontIncline3.setTextureSize(256, 256);
            RightFrontIncline3.setTextureOffset(32, 40);
            RightFrontIncline3.mirror = true;
            setRotation(RightFrontIncline3, 0F, 0F, 0F);

            RightFrontIncline4 = new ModelRenderer(this, 32, 43);
            RightFrontIncline4.addBox(- 6.5F, - 3F, - 3F, 5, 1, 1);
            RightFrontIncline4.setRotationPoint(0F, 0F, 0F);
            RightFrontIncline4.setTextureSize(256, 256);
            RightFrontIncline4.setTextureOffset(32, 43);
            RightFrontIncline4.mirror = true;
            setRotation(RightFrontIncline4, 0F, 0F, 0F);

            RightFrontIncline5 = new ModelRenderer(this, 32, 46);
            RightFrontIncline5.addBox(- 7F, - 3.5F, - 3F, 5, 1, 1);
            RightFrontIncline5.setRotationPoint(0F, 0F, 0F);
            RightFrontIncline5.setTextureSize(256, 256);
            RightFrontIncline5.setTextureOffset(32, 46);
            RightFrontIncline5.mirror = true;
            setRotation(RightFrontIncline5, 0F, 0F, 0F);

            RightBackIncline1 = new ModelRenderer(this, 32, 34);
            RightBackIncline1.addBox(- 5F, - 1.5F, 2F, 5, 1, 1);
            RightBackIncline1.setRotationPoint(0F, 0F, 0F);
            RightBackIncline1.setTextureSize(256, 256);
            RightBackIncline1.setTextureOffset(32, 34);
            RightBackIncline1.mirror = true;
            setRotation(RightBackIncline1, 0F, 0F, 0F);

            RightBackIncline2 = new ModelRenderer(this, 32, 37);
            RightBackIncline2.addBox(- 5.5F, - 2F, 2F, 5, 1, 1);
            RightBackIncline2.setRotationPoint(0F, 0F, 0F);
            RightBackIncline2.setTextureSize(256, 256);
            RightBackIncline2.setTextureOffset(32, 37);
            RightBackIncline2.mirror = true;
            setRotation(RightBackIncline2, 0F, 0F, 0F);

            RightBackIncline3 = new ModelRenderer(this, 32, 40);
            RightBackIncline3.addBox(- 6F, - 2.5F, 2F, 5, 1, 1);
            RightBackIncline3.setRotationPoint(0F, 0F, 0F);
            RightBackIncline3.setTextureSize(256, 256);
            RightBackIncline3.setTextureOffset(32, 40);
            RightBackIncline3.mirror = true;
            setRotation(RightBackIncline3, 0F, 0F, 0F);

            RightBackIncline4 = new ModelRenderer(this, 32, 43);
            RightBackIncline4.addBox(- 6.5F, - 3F, 2F, 5, 1, 1);
            RightBackIncline4.setRotationPoint(0F, 0F, 0F);
            RightBackIncline4.setTextureSize(256, 256);
            RightBackIncline4.setTextureOffset(32, 43);
            RightBackIncline4.mirror = true;
            setRotation(RightBackIncline4, 0F, 0F, 0F);

            RightBackIncline5 = new ModelRenderer(this, 32, 46);
            RightBackIncline5.addBox(- 7F, - 3.5F, 2F, 5, 1, 1);
            RightBackIncline5.setRotationPoint(0F, 0F, 0F);
            RightBackIncline5.setTextureSize(256, 256);
            RightBackIncline5.setTextureOffset(32, 46);
            RightBackIncline5.mirror = true;
            setRotation(RightBackIncline5, 0F, 0F, 0F);

            RightJointGuard1 = new ModelRenderer(this, 0, 54);
            RightJointGuard1.addBox(- 4.9F, - 0.5F, - 3F, 2, 5, 6);
            RightJointGuard1.setRotationPoint(0F, 0F, 0F);
            RightJointGuard1.setTextureSize(256, 256);
            RightJointGuard1.setTextureOffset(0, 54);
            RightJointGuard1.mirror = true;
            setRotation(RightJointGuard1, 0F, 0F, 0F);

            RightJointGuard2 = new ModelRenderer(this, 0, 66);
            RightJointGuard2.addBox(- 3.1F, 0F, - 3F, 1, 3, 6);
            RightJointGuard2.setRotationPoint(0F, 0F, 0F);
            RightJointGuard2.setTextureSize(256, 256);
            RightJointGuard2.setTextureOffset(0, 66);
            RightJointGuard2.mirror = true;
            setRotation(RightJointGuard2, 0F, 0F, 0F);

            RightJointGuard3 = new ModelRenderer(this, 0, 76);
            RightJointGuard3.addBox(- 2.1F, 0F, - 3F, 1, 2, 6);
            RightJointGuard3.setRotationPoint(0F, 0F, 0F);
            RightJointGuard3.setTextureSize(256, 256);
            RightJointGuard3.setTextureOffset(0, 76);
            RightJointGuard3.mirror = true;
            setRotation(RightJointGuard3, 0F, 0F, 0F);

            BackTop = new ModelRenderer(this, 0, 95);
            BackTop.addBox(- 4F, 0F, 2.1F, 8, 6, 1);
            BackTop.setRotationPoint(0F, 0F, 0F);
            BackTop.setTextureSize(256, 256);
            BackTop.setTextureOffset(0, 95);
            BackTop.mirror = true;
            setRotation(BackTop, 0F, 0F, 0F);

            BackMiddle = new ModelRenderer(this, 0, 103);
            BackMiddle.addBox(- 2F, 5F, 1.5F, 4, 3, 1);
            BackMiddle.setRotationPoint(0F, 0F, 0F);
            BackMiddle.setTextureSize(256, 256);
            BackMiddle.setTextureOffset(0, 103);
            BackMiddle.mirror = true;
            setRotation(BackMiddle, 0F, 0F, 0F);

            Abdomen = new ModelRenderer(this, 0, 108);
            Abdomen.addBox(- 4.4F, 7F, - 2.5F, 9, 5, 5);
            Abdomen.setRotationPoint(0F, 0F, 0F);
            Abdomen.setTextureSize(256, 256);
            Abdomen.setTextureOffset(0, 108);
            Abdomen.mirror = true;
            setRotation(Abdomen, 0F, 0F, 0F);

            FrontMiddleRight = new ModelRenderer(this, 32, 54);
            FrontMiddleRight.addBox(- 5F, 6.5F, - 3F, 4, 1, 6);
            FrontMiddleRight.setRotationPoint(0F, 0F, 0F);
            FrontMiddleRight.setTextureSize(256, 256);
            FrontMiddleRight.setTextureOffset(32, 54);
            FrontMiddleRight.mirror = true;
            setRotation(FrontMiddleRight, 0F, 0F, 0F);

            FrontMiddleLeft = new ModelRenderer(this, 32, 54);
            FrontMiddleLeft.addBox(1F, 6.5F, - 3F, 4, 1, 6);
            FrontMiddleLeft.setRotationPoint(0F, 0F, 0F);
            FrontMiddleLeft.setTextureSize(256, 256);
            FrontMiddleLeft.setTextureOffset(32, 54);
            FrontMiddleLeft.mirror = true;
            setRotation(FrontMiddleLeft, 0F, 0F, 0F);

            Waist = new ModelRenderer(this, 0, 138);
            Waist.addBox(- 5F, 11F, - 3F, 10, 1, 5);
            Waist.setRotationPoint(0F, 0F, 0F);
            Waist.setTextureSize(256, 256);
            Waist.setTextureOffset(0, 138);
            Waist.mirror = true;
            setRotation(Waist, 0F, 0F, 0F);

            LeftArmBase = new ModelRenderer(this, 0, 122);
            LeftArmBase.addBox(- 1.5F, 5.5F, - 2.5F, 5, 9, 5);
            LeftArmBase.setRotationPoint(0F, 0F, 0F);
            LeftArmBase.setTextureSize(256, 256);
            LeftArmBase.setTextureOffset(0, 122);
            LeftArmBase.mirror = true;
            setRotation(LeftArmBase, 0F, 0F, 0F);

            LeftArmVent = new ModelRenderer(this, 32, 98);
            LeftArmVent.addBox(3.5F, 3.5F, - 3F, 2, 9, 6);
            LeftArmVent.setRotationPoint(0F, 0F, 0F);
            LeftArmVent.setTextureSize(256, 256);
            LeftArmVent.setTextureOffset(32, 98);
            LeftArmVent.mirror = true;
            setRotation(LeftArmVent, 0F, 0F, 0F);

            LeftArmGuardTop = new ModelRenderer(this, 32, 80);
            LeftArmGuardTop.addBox(3F, 7F, - 3.5F, 3, 9, 7);
            LeftArmGuardTop.setRotationPoint(0F, 0F, 0F);
            LeftArmGuardTop.setTextureSize(256, 256);
            LeftArmGuardTop.setTextureOffset(32, 80);
            LeftArmGuardTop.mirror = true;
            setRotation(LeftArmGuardTop, 0F, 0F, 0F);

            LeftArmGuardFront = new ModelRenderer(this, 32, 115);
            LeftArmGuardFront.addBox(2F, 12F, - 3F, 1, 3, 1);
            LeftArmGuardFront.setRotationPoint(0F, 0F, 0F);
            LeftArmGuardFront.setTextureSize(256, 256);
            LeftArmGuardFront.setTextureOffset(32, 115);
            LeftArmGuardFront.mirror = true;
            setRotation(LeftArmGuardFront, 0F, 0F, 0F);

            LeftArmGuardBack = new ModelRenderer(this, 37, 115);
            LeftArmGuardBack.addBox(2F, 12F, 2F, 1, 3, 1);
            LeftArmGuardBack.setRotationPoint(0F, 0F, 0F);
            LeftArmGuardBack.setTextureSize(256, 256);
            LeftArmGuardBack.setTextureOffset(37, 115);
            LeftArmGuardBack.mirror = true;
            setRotation(LeftArmGuardBack, 0F, 0F, 0F);

            LeftLegBase = new ModelRenderer(this, 32, 122);
            LeftLegBase.addBox(- 2.5F, 6F, - 2.5F, 5, 11, 5);
            LeftLegBase.setRotationPoint(0F, 0F, 0F);
            LeftLegBase.setTextureSize(256, 256);
            LeftLegBase.setTextureOffset(32, 122);
            LeftLegBase.mirror = true;
            setRotation(LeftLegBase, 0F, 0F, 0F);

            LeftLegBaseSide = new ModelRenderer(this, 32, 140);
            LeftLegBaseSide.addBox(1.5F, 4F, - 2.5F, 1, 3, 5);
            LeftLegBaseSide.setRotationPoint(0F, 0F, 0F);
            LeftLegBaseSide.setTextureSize(256, 256);
            LeftLegBaseSide.setTextureOffset(32, 140);
            LeftLegBaseSide.mirror = true;
            setRotation(LeftLegBaseSide, 0F, 0F, 0F);

            LeftLegBaseFrontTop = new ModelRenderer(this, 32, 149);
            LeftLegBaseFrontTop.addBox(- 1F, 4F, - 4.7F, 2, 5, 1);
            LeftLegBaseFrontTop.setRotationPoint(0F, 0F, 0F);
            LeftLegBaseFrontTop.setTextureSize(256, 256);
            LeftLegBaseFrontTop.setTextureOffset(32, 149);
            LeftLegBaseFrontTop.mirror = true;
            setRotation(LeftLegBaseFrontTop, 0.1570796F, 0F, 0F);

            LeftLegBaseInside = new ModelRenderer(this, 0, 195);
            LeftLegBaseInside.addBox(- 3F, 5.5F, - 2.5F, 1, 12, 5);
            LeftLegBaseInside.setRotationPoint(0F, 0F, 0F);
            LeftLegBaseInside.setTextureSize(256, 256);
            LeftLegBaseInside.setTextureOffset(0, 195);
            LeftLegBaseInside.mirror = true;
            setRotation(LeftLegBaseInside, 0F, 0F, 0F);

            LeftLegBaseOutside = new ModelRenderer(this, 0, 175);
            LeftLegBaseOutside.addBox(2F, 4.5F, - 2.5F, 1, 13, 5);
            LeftLegBaseOutside.setRotationPoint(0F, 0F, 0F);
            LeftLegBaseOutside.setTextureSize(256, 256);
            LeftLegBaseOutside.setTextureOffset(0, 175);
            LeftLegBaseOutside.mirror = true;
            setRotation(LeftLegBaseOutside, 0F, 0F, 0F);

            LeftFootJoint1 = new ModelRenderer(this, 0, 214);
            LeftFootJoint1.addBox(- 1.5F, 17F, - 1.5F, 3, 1, 3);
            LeftFootJoint1.setRotationPoint(0F, 0F, 0F);
            LeftFootJoint1.setTextureSize(256, 256);
            LeftFootJoint1.setTextureOffset(0, 214);
            LeftFootJoint1.mirror = true;
            setRotation(LeftFootJoint1, 0F, 0F, 0F);

            LeftFootJoint2 = new ModelRenderer(this, 0, 167);
            LeftFootJoint2.addBox(- 2F, 17.5F, - 3.5F, 4, 1, 6);
            LeftFootJoint2.setRotationPoint(0F, 0F, 0F);
            LeftFootJoint2.setTextureSize(256, 256);
            LeftFootJoint2.setTextureOffset(0, 167);
            LeftFootJoint2.mirror = true;
            setRotation(LeftFootJoint2, 0F, 0F, 0F);

            LeftFootJoint3 = new ModelRenderer(this, 0, 157);
            LeftFootJoint3.addBox(- 3F, 18.3F, - 4.5F, 6, 1, 7);
            LeftFootJoint3.setRotationPoint(0F, 0F, 0F);
            LeftFootJoint3.setTextureSize(256, 256);
            LeftFootJoint3.setTextureOffset(0, 157);
            LeftFootJoint3.mirror = true;
            setRotation(LeftFootJoint3, 0F, 0F, 0F);

            LeftFootJoint4 = new ModelRenderer(this, 0, 146);
            LeftFootJoint4.addBox(- 3F, 18.7F, - 5.5F, 6, 1, 8);
            LeftFootJoint4.setRotationPoint(0F, 0F, 0F);
            LeftFootJoint4.setTextureSize(256, 256);
            LeftFootJoint4.setTextureOffset(0, 146);
            LeftFootJoint4.mirror = true;
            setRotation(LeftFootJoint4, 0F, 0F, 0F);

            RightLegBase = new ModelRenderer(this, 32, 122);
            RightLegBase.addBox(- 2.5F, 6F, - 2.5F, 5, 11, 5);
            RightLegBase.setRotationPoint(0F, 0F, 0F);
            RightLegBase.setTextureSize(256, 256);
            RightLegBase.setTextureOffset(32, 122);
            RightLegBase.mirror = true;
            setRotation(RightLegBase, 0F, 0F, 0F);

            RightLegBaseSide = new ModelRenderer(this, 32, 140);
            RightLegBaseSide.addBox(- 2.5F, 4F, - 2.5F, 1, 3, 5);
            RightLegBaseSide.setRotationPoint(0F, 0F, 0F);
            RightLegBaseSide.setTextureSize(256, 256);
            RightLegBaseSide.setTextureOffset(32, 140);
            RightLegBaseSide.mirror = true;
            setRotation(RightLegBaseSide, 0F, 0F, 0F);

            RightLegBaseFrontTop = new ModelRenderer(this, 32, 149);
            RightLegBaseFrontTop.addBox(- 1F, 4F, - 4.7F, 2, 5, 1);
            RightLegBaseFrontTop.setRotationPoint(0F, 0F, 0F);
            RightLegBaseFrontTop.setTextureSize(256, 256);
            RightLegBaseFrontTop.setTextureOffset(32, 149);
            RightLegBaseFrontTop.mirror = true;
            setRotation(RightLegBaseFrontTop, 0.1570796F, 0F, 0F);

            RightLegBaseInside = new ModelRenderer(this, 0, 195);
            RightLegBaseInside.addBox(2F, 5.5F, - 2.5F, 1, 12, 5);
            RightLegBaseInside.setRotationPoint(0F, 0F, 0F);
            RightLegBaseInside.setTextureSize(256, 256);
            RightLegBaseInside.setTextureOffset(0, 195);
            RightLegBaseInside.mirror = true;
            setRotation(RightLegBaseInside, 0F, 0F, 0F);

            RightLegBaseOutside = new ModelRenderer(this, 0, 175);
            RightLegBaseOutside.addBox(- 3F, 4.5F, - 2.5F, 1, 13, 5);
            RightLegBaseOutside.setRotationPoint(0F, 0F, 0F);
            RightLegBaseOutside.setTextureSize(256, 256);
            RightLegBaseOutside.setTextureOffset(0, 175);
            RightLegBaseOutside.mirror = true;
            setRotation(RightLegBaseOutside, 0F, 0F, 0F);

            RightFootJoint1 = new ModelRenderer(this, 0, 214);
            RightFootJoint1.addBox(- 1.5F, 17F, - 1.5F, 3, 1, 3);
            RightFootJoint1.setRotationPoint(0F, 0F, 0F);
            RightFootJoint1.setTextureSize(256, 256);
            RightFootJoint1.setTextureOffset(0, 214);
            RightFootJoint1.mirror = true;
            setRotation(RightFootJoint1, 0F, 0F, 0F);

            RightFootJoint2 = new ModelRenderer(this, 0, 167);
            RightFootJoint2.addBox(- 2F, 17.5F, - 3.5F, 4, 1, 6);
            RightFootJoint2.setRotationPoint(0F, 0F, 0F);
            RightFootJoint2.setTextureSize(256, 256);
            RightFootJoint2.setTextureOffset(0, 167);
            RightFootJoint2.mirror = true;
            setRotation(RightFootJoint2, 0F, 0F, 0F);

            RightFootJoint3 = new ModelRenderer(this, 0, 157);
            RightFootJoint3.addBox(- 3F, 18.3F, - 4.5F, 6, 1, 7);
            RightFootJoint3.setRotationPoint(0F, 0F, 0F);
            RightFootJoint3.setTextureSize(256, 256);
            RightFootJoint3.setTextureOffset(0, 157);
            RightFootJoint3.mirror = true;
            setRotation(RightFootJoint3, 0F, 0F, 0F);

            RightFootJoint4 = new ModelRenderer(this, 0, 146);
            RightFootJoint4.addBox(- 3F, 18.7F, - 5.5F, 6, 1, 8);
            RightFootJoint4.setRotationPoint(0F, 0F, 0F);
            RightFootJoint4.setTextureSize(256, 256);
            RightFootJoint4.setTextureOffset(0, 146);
            RightFootJoint4.mirror = true;
            setRotation(RightFootJoint4, 0F, 0F, 0F);

            HelmetBack = new ModelRenderer(this, 32, 76);
            HelmetBack.addBox(- 2F, - 6.85F, 3.5F, 4, 1, 1);
            HelmetBack.setRotationPoint(0F, 0F, 0F);
            HelmetBack.setTextureSize(256, 256);
            HelmetBack.setTextureOffset(32, 76);
            HelmetBack.mirror = true;
            setRotation(HelmetBack, 0F, 0F, 0F);

            HelmetLeft = new ModelRenderer(this, 32, 64);
            HelmetLeft.addBox(2.5F, - 6.5F, - 4.5F, 3, 1, 9);
            HelmetLeft.setRotationPoint(0F, 0F, 0F);
            HelmetLeft.setTextureSize(256, 256);
            HelmetLeft.setTextureOffset(32, 64);
            HelmetLeft.mirror = true;
            setRotation(HelmetLeft, 0F, 0F, - 0.1570796F);

            HelmetRight = new ModelRenderer(this, 32, 64);
            HelmetRight.addBox(- 5.5F, - 6.5F, - 4.5F, 3, 1, 9);
            HelmetRight.setRotationPoint(0F, 0F, 0F);
            HelmetRight.setTextureSize(256, 256);
            HelmetRight.setTextureOffset(32, 64);
            HelmetRight.mirror = true;
            setRotation(HelmetRight, 0F, 0F, 0.1570796F);

            LeftShoulderGuardTop = new ModelRenderer(this, 0, 34);
            LeftShoulderGuardTop.addBox(0.5F, - 3F, - 3F, 8, 1, 6);
            LeftShoulderGuardTop.setRotationPoint(0F, 0F, 0F);
            LeftShoulderGuardTop.setTextureSize(256, 256);
            LeftShoulderGuardTop.setTextureOffset(0, 34);
            LeftShoulderGuardTop.mirror = true;
            setRotation(LeftShoulderGuardTop, 0F, 0F, - 0.1745329F);

            LeftShoulderGuardFrontBack = new ModelRenderer(this, 0, 43);
            LeftShoulderGuardFrontBack.addBox(- 0.5F, - 2.5F, - 3.5F, 3, 3, 7);
            LeftShoulderGuardFrontBack.setRotationPoint(0F, 0F, 0F);
            LeftShoulderGuardFrontBack.setTextureSize(256, 256);
            LeftShoulderGuardFrontBack.setTextureOffset(0, 43);
            LeftShoulderGuardFrontBack.mirror = true;
            setRotation(LeftShoulderGuardFrontBack, 0F, 0F, 0F);

            LeftFrontIncline1 = new ModelRenderer(this, 32, 34);
            LeftFrontIncline1.addBox(0F, - 1.5F, - 3F, 5, 1, 1);
            LeftFrontIncline1.setRotationPoint(0F, 0F, 0F);
            LeftFrontIncline1.setTextureSize(256, 256);
            LeftFrontIncline1.setTextureOffset(32, 34);
            LeftFrontIncline1.mirror = true;
            setRotation(LeftFrontIncline1, 0F, 0F, 0F);

            LeftFrontIncline2 = new ModelRenderer(this, 32, 37);
            LeftFrontIncline2.addBox(0.5F, - 2F, - 3F, 5, 1, 1);
            LeftFrontIncline2.setRotationPoint(0F, 0F, 0F);
            LeftFrontIncline2.setTextureSize(256, 256);
            LeftFrontIncline2.setTextureOffset(32, 37);
            LeftFrontIncline2.mirror = true;
            setRotation(LeftFrontIncline2, 0F, 0F, 0F);

            LeftFrontIncline3 = new ModelRenderer(this, 32, 40);
            LeftFrontIncline3.addBox(1F, - 2.5F, - 3F, 5, 1, 1);
            LeftFrontIncline3.setRotationPoint(0F, 0F, 0F);
            LeftFrontIncline3.setTextureSize(256, 256);
            LeftFrontIncline3.setTextureOffset(32, 40);
            LeftFrontIncline3.mirror = true;
            setRotation(LeftFrontIncline3, 0F, 0F, 0F);

            LeftFrontIncline4 = new ModelRenderer(this, 32, 43);
            LeftFrontIncline4.addBox(1.5F, - 3F, - 3F, 5, 1, 1);
            LeftFrontIncline4.setRotationPoint(0F, 0F, 0F);
            LeftFrontIncline4.setTextureSize(256, 256);
            LeftFrontIncline4.setTextureOffset(32, 43);
            LeftFrontIncline4.mirror = true;
            setRotation(LeftFrontIncline4, 0F, 0F, 0F);

            LeftFrontIncline5 = new ModelRenderer(this, 32, 46);
            LeftFrontIncline5.addBox(2F, - 3.5F, - 3F, 5, 1, 1);
            LeftFrontIncline5.setRotationPoint(0F, 0F, 0F);
            LeftFrontIncline5.setTextureSize(256, 256);
            LeftFrontIncline5.setTextureOffset(32, 46);
            LeftFrontIncline5.mirror = true;
            setRotation(LeftFrontIncline5, 0F, 0F, 0F);

            LeftBackIncline1 = new ModelRenderer(this, 32, 34);
            LeftBackIncline1.addBox(0F, - 1.5F, 2F, 5, 1, 1);
            LeftBackIncline1.setRotationPoint(0F, 0F, 0F);
            LeftBackIncline1.setTextureSize(256, 256);
            LeftBackIncline1.setTextureOffset(32, 34);
            LeftBackIncline1.mirror = true;
            setRotation(LeftBackIncline1, 0F, 0F, 0F);

            LeftBackIncline2 = new ModelRenderer(this, 32, 37);
            LeftBackIncline2.addBox(0.5F, - 2F, 2F, 5, 1, 1);
            LeftBackIncline2.setRotationPoint(0F, 0F, 0F);
            LeftBackIncline2.setTextureSize(256, 256);
            LeftBackIncline2.setTextureOffset(32, 37);
            LeftBackIncline2.mirror = true;
            setRotation(LeftBackIncline2, 0F, 0F, 0F);

            LeftBackIncline3 = new ModelRenderer(this, 32, 40);
            LeftBackIncline3.addBox(1F, - 2.5F, 2F, 5, 1, 1);
            LeftBackIncline3.setRotationPoint(0F, 0F, 0F);
            LeftBackIncline3.setTextureSize(256, 256);
            LeftBackIncline3.setTextureOffset(32, 40);
            LeftBackIncline3.mirror = true;
            setRotation(LeftBackIncline3, 0F, 0F, 0F);

            LeftBackIncline4 = new ModelRenderer(this, 32, 43);
            LeftBackIncline4.addBox(1.5F, - 3F, 2F, 5, 1, 1);
            LeftBackIncline4.setRotationPoint(0F, 0F, 0F);
            LeftBackIncline4.setTextureSize(256, 256);
            LeftBackIncline4.setTextureOffset(32, 43);
            LeftBackIncline4.mirror = true;
            setRotation(LeftBackIncline4, 0F, 0F, 0F);

            LeftBackIncline5 = new ModelRenderer(this, 32, 46);
            LeftBackIncline5.addBox(2F, - 3.5F, 2F, 5, 1, 1);
            LeftBackIncline5.setRotationPoint(0F, 0F, 0F);
            LeftBackIncline5.setTextureSize(256, 256);
            LeftBackIncline5.setTextureOffset(32, 46);
            LeftBackIncline5.mirror = true;
            setRotation(LeftBackIncline5, 0F, 0F, 0F);

            LeftJointGuard1 = new ModelRenderer(this, 0, 54);
            LeftJointGuard1.addBox(2.9F, - 0.5F, - 3F, 2, 5, 6);
            LeftJointGuard1.setRotationPoint(0F, 0F, 0F);
            LeftJointGuard1.setTextureSize(256, 256);
            LeftJointGuard1.setTextureOffset(0, 54);
            LeftJointGuard1.mirror = true;
            setRotation(LeftJointGuard1, 0F, 0F, 0F);

            LeftJointGuard2 = new ModelRenderer(this, 0, 66);
            LeftJointGuard2.addBox(2.1F, 0F, - 3F, 1, 3, 6);
            LeftJointGuard2.setRotationPoint(0F, 0F, 0F);
            LeftJointGuard2.setTextureSize(256, 256);
            LeftJointGuard2.setTextureOffset(0, 66);
            LeftJointGuard2.mirror = true;
            setRotation(LeftJointGuard2, 0F, 0F, 0F);

            LeftJointGuard3 = new ModelRenderer(this, 0, 76);
            LeftJointGuard3.addBox(1.1F, 0F, - 3F, 1, 2, 6);
            LeftJointGuard3.setRotationPoint(0F, 0F, 0F);
            LeftJointGuard3.setTextureSize(256, 256);
            LeftJointGuard3.setTextureOffset(0, 76);
            LeftJointGuard3.mirror = true;
            setRotation(LeftJointGuard3, 0F, 0F, 0F);

            LeftWingPiece1 = new ModelRenderer(this, 70, 1);
            LeftWingPiece1.addBox(9F, - 2F, 4.5F, 9, 3, 4);
            LeftWingPiece1.setRotationPoint(0F, 0F, 0F);
            LeftWingPiece1.setTextureSize(256, 256);
            LeftWingPiece1.mirror = true;
            setRotation(LeftWingPiece1, 0F, 0F, - 0.4363323F);

            LeftWingPiece2 = new ModelRenderer(this, 70, 10);
            LeftWingPiece2.addBox(9F, - 6F, 5F, 8, 3, 3);
            LeftWingPiece2.setRotationPoint(0F, 0F, 0F);
            LeftWingPiece2.setTextureSize(256, 256);
            LeftWingPiece2.mirror = true;
            setRotation(LeftWingPiece2, 0F, 0F, 0F);

            LeftWingPiece3 = new ModelRenderer(this, 70, 18);
            LeftWingPiece3.addBox(14F, - 7.5F, 4.5F, 7, 3, 1);
            LeftWingPiece3.setRotationPoint(0F, 0F, 0F);
            LeftWingPiece3.setTextureSize(256, 256);
            LeftWingPiece3.mirror = true;
            setRotation(LeftWingPiece3, 0F, 0F, - 0.0698132F);

            LeftWingPiece4 = new ModelRenderer(this, 70, 24);
            LeftWingPiece4.addBox(9.5F, - 17F, 6F, 3, 4, 1);
            LeftWingPiece4.setRotationPoint(0F, 0F, 0F);
            LeftWingPiece4.setTextureSize(256, 256);
            LeftWingPiece4.mirror = true;
            setRotation(LeftWingPiece4, - 0.1919862F, 0.2268928F, 0.296706F);

            LeftWingPiece5 = new ModelRenderer(this, 77, 31);
            LeftWingPiece5.addBox(9.3F, 0.9F, 5.5F, 6, 2, 2);
            LeftWingPiece5.setRotationPoint(0F, 0F, 0F);
            LeftWingPiece5.setTextureSize(256, 256);
            LeftWingPiece5.mirror = true;
            setRotation(LeftWingPiece5, 0F, 0F, - 0.7504916F);

            LeftWingPiece6 = new ModelRenderer(this, 77, 40);
            LeftWingPiece6.addBox(17F, 1.5F, 5.5F, 5, 2, 2);
            LeftWingPiece6.setRotationPoint(0F, 0F, 0F);
            LeftWingPiece6.setTextureSize(256, 256);
            LeftWingPiece6.mirror = true;
            setRotation(LeftWingPiece6, 0F, 0F, - 0.5759587F);

            LeftWingPiece7 = new ModelRenderer(this, 77, 36);
            LeftWingPiece7.addBox(15.5F, - 15.5F, 7F, 9, 2, 1);
            LeftWingPiece7.setRotationPoint(0F, 0F, 0F);
            LeftWingPiece7.setTextureSize(256, 256);
            LeftWingPiece7.mirror = true;
            setRotation(LeftWingPiece7, 0.0174533F, 0.0174533F, 0.2792527F);

            LeftWingPiece8 = new ModelRenderer(this, 77, 36);
            LeftWingPiece8.addBox(15.8F, - 15.8F, 5F, 9, 2, 1);
            LeftWingPiece8.setRotationPoint(0F, 0F, 0F);
            LeftWingPiece8.setTextureSize(256, 256);
            LeftWingPiece8.mirror = true;
            setRotation(LeftWingPiece8, - 0.0174533F, - 0.0174533F, 0.2792527F);

            LeftWingPiece9 = new ModelRenderer(this, 70, 31);
            LeftWingPiece9.addBox(12F, - 3F, 6F, 2, 13, 1);
            LeftWingPiece9.setRotationPoint(0F, 0F, 0F);
            LeftWingPiece9.setTextureSize(256, 256);
            LeftWingPiece9.mirror = true;
            setRotation(LeftWingPiece9, 0F, 0F, 0F);

            LeftWingPiece10 = new ModelRenderer(this, 70, 46);
            LeftWingPiece10.addBox(13F, - 6F, 5.5F, 4, 17, 2);
            LeftWingPiece10.setRotationPoint(0F, 0F, 0F);
            LeftWingPiece10.setTextureSize(256, 256);
            LeftWingPiece10.mirror = true;
            setRotation(LeftWingPiece10, 0F, 0F, 0.1047198F);

            LeftWingPiece11 = new ModelRenderer(this, 86, 46);
            LeftWingPiece11.addBox(16F, 2F, 5.5F, 2, 9, 2);
            LeftWingPiece11.setRotationPoint(0F, 0F, 0F);
            LeftWingPiece11.setTextureSize(256, 256);
            LeftWingPiece11.mirror = true;
            setRotation(LeftWingPiece11, 0F, 0F, 0.1047198F);

            LeftWingPiece12 = new ModelRenderer(this, 70, 18);
            LeftWingPiece12.addBox(14F, - 7.5F, 7.5F, 7, 3, 1);
            LeftWingPiece12.setRotationPoint(0F, 0F, 0F);
            LeftWingPiece12.setTextureSize(256, 256);
            LeftWingPiece12.mirror = true;
            setRotation(LeftWingPiece12, 0F, 0F, - 0.0698132F);

            LeftWingPiece13 = new ModelRenderer(this, 70, 24);
            LeftWingPiece13.addBox(12.3F, - 15.5F, 5.7F, 3, 4, 1);
            LeftWingPiece13.setRotationPoint(0F, 0F, 0F);
            LeftWingPiece13.setTextureSize(256, 256);
            LeftWingPiece13.mirror = true;
            setRotation(LeftWingPiece13, 0.1570796F, - 0.1919862F, 0.296706F);

            LeftWingPiece14 = new ModelRenderer(this, 86, 59);
            LeftWingPiece14.addBox(14F, - 0.2F, 6.2F, 4, 3, 1);
            LeftWingPiece14.setRotationPoint(0F, 0F, 0F);
            LeftWingPiece14.setTextureSize(256, 256);
            LeftWingPiece14.mirror = true;
            setRotation(LeftWingPiece14, - 0.1396263F, 0.0523599F, - 0.7504916F);

            LeftWingPiece15 = new ModelRenderer(this, 86, 59);
            LeftWingPiece15.addBox(14.5F, 2.05F, 5.6F, 4, 3, 1);
            LeftWingPiece15.setRotationPoint(0F, 0F, 0F);
            LeftWingPiece15.setTextureSize(256, 256);
            LeftWingPiece15.mirror = true;
            setRotation(LeftWingPiece15, 0.1396263F, - 0.0523599F, - 0.7504916F);

            LeftWingPiece16 = new ModelRenderer(this, 70, 66);
            LeftWingPiece16.addBox(5F, - 18F, 5.4F, 16, 2, 1);
            LeftWingPiece16.setRotationPoint(0F, 0F, 0F);
            LeftWingPiece16.setTextureSize(256, 256);
            LeftWingPiece16.mirror = true;
            setRotation(LeftWingPiece16, - 0.122173F, 0.1570796F, 0.8726646F);

            LeftWingPiece17 = new ModelRenderer(this, 70, 66);
            LeftWingPiece17.addBox(6F, - 18F, 6.6F, 16, 2, 1);
            LeftWingPiece17.setRotationPoint(0F, 0F, 0F);
            LeftWingPiece17.setTextureSize(256, 256);
            LeftWingPiece17.mirror = true;
            setRotation(LeftWingPiece17, 0.122173F, - 0.1570796F, 0.8726646F);

            LeftWingPiece18 = new ModelRenderer(this, 70, 70);
            LeftWingPiece18.addBox(5F, - 19F, 5.4F, 10, 4, 1);
            LeftWingPiece18.setRotationPoint(0F, 0F, 0F);
            LeftWingPiece18.setTextureSize(256, 256);
            LeftWingPiece18.mirror = true;
            setRotation(LeftWingPiece18, - 0.122173F, 0.1570796F, 0.8726646F);

            LeftWingPiece19 = new ModelRenderer(this, 70, 70);
            LeftWingPiece19.addBox(6F, - 19F, 6.6F, 10, 4, 1);
            LeftWingPiece19.setRotationPoint(0F, 0F, 0F);
            LeftWingPiece19.setTextureSize(256, 256);
            LeftWingPiece19.mirror = true;
            setRotation(LeftWingPiece19, 0.122173F, - 0.1570796F, 0.8726646F);

            RightWingPiece1 = new ModelRenderer(this, 70, 1);
            RightWingPiece1.addBox(- 18F, - 2F, 4.5F, 9, 3, 4);
            RightWingPiece1.setRotationPoint(0F, 0F, 0F);
            RightWingPiece1.setTextureSize(256, 256);
            RightWingPiece1.mirror = true;
            setRotation(RightWingPiece1, 0F, 0F, 0.4363323F);

            RightWingPiece2 = new ModelRenderer(this, 70, 10);
            RightWingPiece2.addBox(- 17F, - 6F, 5F, 8, 3, 3);
            RightWingPiece2.setRotationPoint(0F, 0F, 0F);
            RightWingPiece2.setTextureSize(256, 256);
            RightWingPiece2.mirror = true;
            setRotation(RightWingPiece2, 0F, 0F, 0F);

            RightWingPiece3 = new ModelRenderer(this, 70, 18);
            RightWingPiece3.addBox(- 21F, - 7.5F, 4.5F, 7, 3, 1);
            RightWingPiece3.setRotationPoint(0F, 0F, 0F);
            RightWingPiece3.setTextureSize(256, 256);
            RightWingPiece3.mirror = true;
            setRotation(RightWingPiece3, 0F, 0F, 0.0698132F);

            RightWingPiece4 = new ModelRenderer(this, 70, 24);
            RightWingPiece4.addBox(- 15.8F, - 16.2F, 5.6F, 3, 4, 1);
            RightWingPiece4.setRotationPoint(0F, 0F, 0F);
            RightWingPiece4.setTextureSize(256, 256);
            RightWingPiece4.mirror = true;
            setRotation(RightWingPiece4, 0.1570796F, 0.1919862F, - 0.296706F);

            RightWingPiece5 = new ModelRenderer(this, 77, 31);
            RightWingPiece5.addBox(- 15.3F, 0.9F, 5.5F, 6, 2, 2);
            RightWingPiece5.setRotationPoint(0F, 0F, 0F);
            RightWingPiece5.setTextureSize(256, 256);
            RightWingPiece5.mirror = true;
            setRotation(RightWingPiece5, 0F, 0F, 0.7504916F);

            RightWingPiece6 = new ModelRenderer(this, 77, 40);
            RightWingPiece6.addBox(- 22F, 1.5F, 5.5F, 5, 2, 2);
            RightWingPiece6.setRotationPoint(0F, 0F, 0F);
            RightWingPiece6.setTextureSize(256, 256);
            RightWingPiece6.mirror = true;
            setRotation(RightWingPiece6, 0F, 0F, 0.5759587F);

            RightWingPiece7 = new ModelRenderer(this, 77, 36);
            RightWingPiece7.addBox(- 24.5F, - 15.5F, 6.6F, 9, 2, 1);
            RightWingPiece7.setRotationPoint(0F, 0F, 0F);
            RightWingPiece7.setTextureSize(256, 256);
            RightWingPiece7.mirror = true;
            setRotation(RightWingPiece7, - 0.0174533F, - 0.0174533F, - 0.2792527F);

            RightWingPiece8 = new ModelRenderer(this, 77, 36);
            RightWingPiece8.addBox(- 24.8F, - 15.3F, 5.5F, 9, 2, 1);
            RightWingPiece8.setRotationPoint(0F, 0F, 0F);
            RightWingPiece8.setTextureSize(256, 256);
            RightWingPiece8.mirror = true;
            setRotation(RightWingPiece8, 0.0174533F, 0.0174533F, - 0.2792527F);

            RightWingPiece9 = new ModelRenderer(this, 70, 31);
            RightWingPiece9.addBox(- 14F, - 3F, 6F, 2, 13, 1);
            RightWingPiece9.setRotationPoint(0F, 0F, 0F);
            RightWingPiece9.setTextureSize(256, 256);
            RightWingPiece9.mirror = true;
            setRotation(RightWingPiece9, 0F, 0F, 0F);

            RightWingPiece10 = new ModelRenderer(this, 70, 46);
            RightWingPiece10.addBox(- 17F, - 6F, 5.5F, 4, 17, 2);
            RightWingPiece10.setRotationPoint(0F, 0F, 0F);
            RightWingPiece10.setTextureSize(256, 256);
            RightWingPiece10.mirror = true;
            setRotation(RightWingPiece10, 0F, 0F, - 0.1047198F);

            RightWingPiece11 = new ModelRenderer(this, 86, 46);
            RightWingPiece11.addBox(- 18F, 1F, 5.5F, 2, 9, 2);
            RightWingPiece11.setRotationPoint(0F, 0F, 0F);
            RightWingPiece11.setTextureSize(256, 256);
            RightWingPiece11.mirror = true;
            setRotation(RightWingPiece11, 0F, 0F, - 0.1047198F);

            RightWingPiece12 = new ModelRenderer(this, 70, 18);
            RightWingPiece12.addBox(- 21F, - 7.5F, 7.5F, 7, 3, 1);
            RightWingPiece12.setRotationPoint(0F, 0F, 0F);
            RightWingPiece12.setTextureSize(256, 256);
            RightWingPiece12.mirror = true;
            setRotation(RightWingPiece12, 0F, 0F, 0.0698132F);

            RightWingPiece13 = new ModelRenderer(this, 70, 24);
            RightWingPiece13.addBox(- 13.3F, - 17.5F, 6F, 3, 4, 1);
            RightWingPiece13.setRotationPoint(0F, 0F, 0F);
            RightWingPiece13.setTextureSize(256, 256);
            RightWingPiece13.mirror = true;
            setRotation(RightWingPiece13, - 0.1570796F, - 0.1919862F, - 0.296706F);

            RightWingPiece14 = new ModelRenderer(this, 86, 59);
            RightWingPiece14.addBox(- 18F, - 0.2F, 6.2F, 4, 3, 1);
            RightWingPiece14.setRotationPoint(0F, 0F, 0F);
            RightWingPiece14.setTextureSize(256, 256);
            RightWingPiece14.mirror = true;
            setRotation(RightWingPiece14, - 0.1396263F, - 0.0523599F, 0.7504916F);

            RightWingPiece15 = new ModelRenderer(this, 86, 59);
            RightWingPiece15.addBox(- 18.5F, 2.1F, 5.6F, 4, 3, 1);
            RightWingPiece15.setRotationPoint(0F, 0F, 0F);
            RightWingPiece15.setTextureSize(256, 256);
            RightWingPiece15.mirror = true;
            setRotation(RightWingPiece15, 0.1396263F, 0.0523599F, 0.7504916F);

            RightWingPiece16 = new ModelRenderer(this, 70, 66);
            RightWingPiece16.addBox(- 22F, - 18F, 6.6F, 16, 2, 1);
            RightWingPiece16.setRotationPoint(0F, 0F, 0F);
            RightWingPiece16.setTextureSize(256, 256);
            RightWingPiece16.mirror = true;
            setRotation(RightWingPiece16, 0.122173F, 0.1570796F, - 0.8726646F);

            RightWingPiece17 = new ModelRenderer(this, 70, 66);
            RightWingPiece17.addBox(- 21F, - 18F, 5.4F, 16, 2, 1);
            RightWingPiece17.setRotationPoint(0F, 0F, 0F);
            RightWingPiece17.setTextureSize(256, 256);
            RightWingPiece17.mirror = true;
            setRotation(RightWingPiece17, - 0.122173F, - 0.1570796F, - 0.8726646F);

            RightWingPiece18 = new ModelRenderer(this, 70, 70);
            RightWingPiece18.addBox(- 15F, - 19F, 5.4F, 10, 4, 1);
            RightWingPiece18.setRotationPoint(0F, 0F, 0F);
            RightWingPiece18.setTextureSize(256, 256);
            RightWingPiece18.mirror = true;
            setRotation(RightWingPiece18, - 0.122173F, - 0.1570796F, - 0.8726646F);

            RightWingPiece19 = new ModelRenderer(this, 70, 70);
            RightWingPiece19.addBox(- 16F, - 19F, 6.6F, 10, 4, 1);
            RightWingPiece19.setRotationPoint(0F, 0F, 0F);
            RightWingPiece19.setTextureSize(256, 256);
            RightWingPiece19.mirror = true;
            setRotation(RightWingPiece19, 0.122173F, 0.1570796F, - 0.8726646F);

            //
            // Head
            //
            this.bipedHead.addChild(HelmetLeft);
            this.bipedHead.addChild(HelmetRight);
            this.bipedHead.addChild(HelmetBack);

            //
            // Right Arm
            //
            this.bipedRightArm.addChild(RightShoulderGuardFrontBack);
            this.bipedRightArm.addChild(RightShoulderGuardTop);

            this.bipedRightArm.addChild(RightFrontIncline1);
            this.bipedRightArm.addChild(RightFrontIncline2);
            this.bipedRightArm.addChild(RightFrontIncline3);
            this.bipedRightArm.addChild(RightFrontIncline4);
            this.bipedRightArm.addChild(RightFrontIncline5);

            this.bipedRightArm.addChild(RightBackIncline1);
            this.bipedRightArm.addChild(RightBackIncline2);
            this.bipedRightArm.addChild(RightBackIncline3);
            this.bipedRightArm.addChild(RightBackIncline4);
            this.bipedRightArm.addChild(RightBackIncline5);

            //
            // Main Body
            //
            this.bipedBody.addChild(RightJointGuard1);
            this.bipedBody.addChild(RightJointGuard2);
            this.bipedBody.addChild(RightJointGuard3);

            this.bipedBody.addChild(LeftJointGuard1);
            this.bipedBody.addChild(LeftJointGuard2);
            this.bipedBody.addChild(LeftJointGuard3);

            this.bipedBody.addChild(Abdomen);
            this.bipedBody.addChild(BackMiddle);
            this.bipedBody.addChild(BackTop);
            this.bipedBody.addChild(FrontMiddleLeft);
            this.bipedBody.addChild(FrontMiddleRight);
            this.bipedBody.addChild(Waist);

            //
            // Left Arm
            //
            this.bipedLeftArm.addChild(LeftShoulderGuardFrontBack);
            this.bipedLeftArm.addChild(LeftShoulderGuardTop);

            this.bipedLeftArm.addChild(LeftFrontIncline1);
            this.bipedLeftArm.addChild(LeftFrontIncline2);
            this.bipedLeftArm.addChild(LeftFrontIncline3);
            this.bipedLeftArm.addChild(LeftFrontIncline4);
            this.bipedLeftArm.addChild(LeftFrontIncline5);

            this.bipedLeftArm.addChild(LeftBackIncline1);
            this.bipedLeftArm.addChild(LeftBackIncline2);
            this.bipedLeftArm.addChild(LeftBackIncline3);
            this.bipedLeftArm.addChild(LeftBackIncline4);
            this.bipedLeftArm.addChild(LeftBackIncline5);

            this.bipedLeftArm.addChild(LeftArmBase);
            this.bipedLeftArm.addChild(LeftArmGuardBack);
            this.bipedLeftArm.addChild(LeftArmGuardFront);
            this.bipedLeftArm.addChild(LeftArmGuardTop);
            this.bipedLeftArm.addChild(LeftArmVent);

            //
            // Right Leg
            //
            this.bipedRightLeg.addChild(RightLegBase);
            this.bipedRightLeg.addChild(RightLegBaseFrontTop);
            this.bipedRightLeg.addChild(RightLegBaseInside);
            this.bipedRightLeg.addChild(RightLegBaseOutside);
            this.bipedRightLeg.addChild(RightLegBaseSide);

            this.bipedRightLeg.addChild(RightFootJoint1);
            this.bipedRightLeg.addChild(RightFootJoint2);
            this.bipedRightLeg.addChild(RightFootJoint3);
            this.bipedRightLeg.addChild(RightFootJoint4);

            //
            // Left Leg
            //
            this.bipedLeftLeg.addChild(LeftLegBase);
            this.bipedLeftLeg.addChild(LeftLegBaseFrontTop);
            this.bipedLeftLeg.addChild(LeftLegBaseInside);
            this.bipedLeftLeg.addChild(LeftLegBaseOutside);
            this.bipedLeftLeg.addChild(LeftLegBaseSide);

            this.bipedLeftLeg.addChild(LeftFootJoint1);
            this.bipedLeftLeg.addChild(LeftFootJoint2);
            this.bipedLeftLeg.addChild(LeftFootJoint3);
            this.bipedLeftLeg.addChild(LeftFootJoint4);

            //
            // Left Wing
            //
            this.bipedBody.addChild(LeftWingPiece1);
            this.bipedBody.addChild(LeftWingPiece2);
            this.bipedBody.addChild(LeftWingPiece3);
            this.bipedBody.addChild(LeftWingPiece4);
            this.bipedBody.addChild(LeftWingPiece5);
            this.bipedBody.addChild(LeftWingPiece6);
            this.bipedBody.addChild(LeftWingPiece7);
            this.bipedBody.addChild(LeftWingPiece8);
            this.bipedBody.addChild(LeftWingPiece9);
            this.bipedBody.addChild(LeftWingPiece10);
            this.bipedBody.addChild(LeftWingPiece11);
            this.bipedBody.addChild(LeftWingPiece12);
            this.bipedBody.addChild(LeftWingPiece13);
            this.bipedBody.addChild(LeftWingPiece14);
            this.bipedBody.addChild(LeftWingPiece15);
            this.bipedBody.addChild(LeftWingPiece16);
            this.bipedBody.addChild(LeftWingPiece17);
            this.bipedBody.addChild(LeftWingPiece18);
            this.bipedBody.addChild(LeftWingPiece19);

            //
            // Right Wing
            //
            this.bipedBody.addChild(RightWingPiece1);
            this.bipedBody.addChild(RightWingPiece2);
            this.bipedBody.addChild(RightWingPiece3);
            this.bipedBody.addChild(RightWingPiece4);
            this.bipedBody.addChild(RightWingPiece5);
            this.bipedBody.addChild(RightWingPiece6);
            this.bipedBody.addChild(RightWingPiece7);
            this.bipedBody.addChild(RightWingPiece8);
            this.bipedBody.addChild(RightWingPiece9);
            this.bipedBody.addChild(RightWingPiece10);
            this.bipedBody.addChild(RightWingPiece11);
            this.bipedBody.addChild(RightWingPiece12);
            this.bipedBody.addChild(RightWingPiece13);
            this.bipedBody.addChild(RightWingPiece14);
            this.bipedBody.addChild(RightWingPiece15);
            this.bipedBody.addChild(RightWingPiece16);
            this.bipedBody.addChild(RightWingPiece17);
            this.bipedBody.addChild(RightWingPiece18);
            this.bipedBody.addChild(RightWingPiece19);
        }


        RightArmBase = new ModelRenderer(this, 0, 122);
        RightArmBase.addBox(- 3.5F, 5.5F, - 2.5F, 5, 9, 5);
        RightArmBase.setRotationPoint(0F, 0F, 0F);
        RightArmBase.setTextureSize(256, 256);
        RightArmBase.setTextureOffset(0, 122);
        RightArmBase.mirror = true;
        setRotation(RightArmBase, 0F, 0F, 0F);

        RightArmVent = new ModelRenderer(this, 32, 98);
        RightArmVent.mirror = true;
        RightArmVent.addBox(- 5.5F, 3.5F, - 3F, 2, 9, 6);
        RightArmVent.setRotationPoint(0F, 0F, 0F);
        RightArmVent.setTextureSize(256, 256);
        RightArmVent.setTextureOffset(32, 98);
        RightArmVent.mirror = true;
        setRotation(RightArmVent, 0F, 0F, 0F);
        RightArmVent.mirror = false;

        RightArmGuardTop = new ModelRenderer(this, 32, 80);
        RightArmGuardTop.mirror = true;
        RightArmGuardTop.addBox(- 6F, 7F, - 3.5F, 3, 9, 7);
        RightArmGuardTop.setRotationPoint(0F, 0F, 0F);
        RightArmGuardTop.setTextureSize(256, 256);
        RightArmGuardTop.setTextureOffset(32, 80);
        RightArmGuardTop.mirror = true;
        setRotation(RightArmGuardTop, 0F, 0F, 0F);
        RightArmGuardTop.mirror = false;

        RightArmGuardFront = new ModelRenderer(this, 32, 115);
        RightArmGuardFront.addBox(- 3F, 12F, - 3F, 1, 3, 1);
        RightArmGuardFront.setRotationPoint(0F, 0F, 0F);
        RightArmGuardFront.setTextureSize(256, 256);
        RightArmGuardFront.setTextureOffset(32, 115);
        RightArmGuardFront.mirror = true;
        setRotation(RightArmGuardFront, 0F, 0F, 0F);

        RightArmGuardBack = new ModelRenderer(this, 37, 115);
        RightArmGuardBack.addBox(- 3F, 12F, 2F, 1, 3, 1);
        RightArmGuardBack.setRotationPoint(0F, 0F, 0F);
        RightArmGuardBack.setTextureSize(256, 256);
        RightArmGuardBack.setTextureOffset(37, 115);
        RightArmGuardBack.mirror = true;
        setRotation(RightArmGuardBack, 0F, 0F, 0F);

        //
        // Right Arm (Partial Deploy)
        //
        this.bipedRightArm.addChild(RightArmBase);
        this.bipedRightArm.addChild(RightArmGuardBack);
        this.bipedRightArm.addChild(RightArmGuardFront);
        this.bipedRightArm.addChild(RightArmGuardTop);
        this.bipedRightArm.addChild(RightArmVent);

    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

}
