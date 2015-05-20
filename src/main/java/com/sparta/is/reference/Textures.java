package com.sparta.is.reference;

import com.sparta.is.utility.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

public class Textures
{
    public static final String RESOURCE_PREFIX = Reference.LOWERCASE_MOD_ID + ":";

    public static final class Armor
    {
        private static final String ARMOR_SHEET_LOCATION = "textures/armor/";

        public static ResourceLocation BYAKUSHIKI = ResourceLocationHelper.getResourceLocation(ARMOR_SHEET_LOCATION + "byakushiki.png");
    }

    public static final class Model
    {
        private static final String MODEL_TEXTURE_LOCATION = "textures/items/";

        public static final ResourceLocation YUKIHIRA_HILT = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "yukihirahilt.png");
        public static final ResourceLocation YUKIHIRA_BLADE = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "yukihiraablade.png");
        public static final ResourceLocation YUKIHIRA_NIGATA = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "yukihiranigata.png");
        public static final ResourceLocation ABSOLUTE_DEFENSE = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "absolutedefense.png");
        public static final ResourceLocation IS_CORE = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "iscore.png");
        public static final ResourceLocation IS_PROCESSOR = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "isprocessor.png");
    }

    public static final class Gui
    {

    }

}

