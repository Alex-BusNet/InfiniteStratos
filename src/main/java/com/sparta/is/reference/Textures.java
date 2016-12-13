package com.sparta.is.reference;

import com.sparta.is.utils.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

public class Textures
{
    public static final String RESOURCE_PREFIX = Reference.MOD_ID + ":";

    public static final class Armor
    {
        private static final String ARMOR_SHEET_LOCATION = "textures/armor/";

        public static ResourceLocation BYAKUSHIKI = ResourceLocationHelper.getResourceLocation(ARMOR_SHEET_LOCATION + "Byakushiki_Armor.png");
    }

    public static final class WeaponIcons
    {
        private static String WEAPON_ICON_LOCATION = "textures/items/";

        public static ResourceLocation YUKIHIRA_NIGATA_ICON = ResourceLocationHelper.getResourceLocation(WEAPON_ICON_LOCATION + "yukihiranigata.png");
    }

    public static final class Items
    {
        private static final String ITEM_TEXTURE_LOCATION = "textures/items/";

        public static final ResourceLocation YUKIHIRA_HILT = ResourceLocationHelper.getResourceLocation(ITEM_TEXTURE_LOCATION + "yukihirahilt.png");
        public static final ResourceLocation YUKIHIRA_BLADE = ResourceLocationHelper.getResourceLocation(ITEM_TEXTURE_LOCATION + "yukihirablade.png");
        public static final ResourceLocation YUKIHIRA_NIGATA = ResourceLocationHelper.getResourceLocation(ITEM_TEXTURE_LOCATION + "yukihiranigata.png");
        public static final ResourceLocation YUKIHIRA_MODEL_TEXTURE = ResourceLocationHelper.getResourceLocation(ITEM_TEXTURE_LOCATION + "yukihiraModel_color.png");
        public static final ResourceLocation REIRAKU_BYAKUYA = ResourceLocationHelper.getResourceLocation(ITEM_TEXTURE_LOCATION + "yukihiranigata_oneoff.png");
        public static final ResourceLocation ABSOLUTE_DEFENSE = ResourceLocationHelper.getResourceLocation(ITEM_TEXTURE_LOCATION + "absolutedefense.png");
        public static final ResourceLocation IS_CORE = ResourceLocationHelper.getResourceLocation(ITEM_TEXTURE_LOCATION + "isCore.png");
        public static final ResourceLocation IS_PROCESSOR = ResourceLocationHelper.getResourceLocation(ITEM_TEXTURE_LOCATION + "isProcessor.png");
        public static final ResourceLocation TEST_UNIT = ResourceLocationHelper.getResourceLocation(ITEM_TEXTURE_LOCATION + "testUnit.png");
        public static final ResourceLocation IS_UNIT_STATION = ResourceLocationHelper.getResourceLocation(ITEM_TEXTURE_LOCATION + "isUnitStation.png");
        public static final ResourceLocation IS_UNIT_STATION_MAIN_DISPLAY = ResourceLocationHelper.getResourceLocation(ITEM_TEXTURE_LOCATION + "isUnitStation_mainDisplay.png");
        public static final ResourceLocation IS_UNIT_STATION_SMALL_DISPLAY = ResourceLocationHelper.getResourceLocation(ITEM_TEXTURE_LOCATION + "isUnitStation_smallDisplay.png");
        public static final ResourceLocation UNIT_STAND = ResourceLocationHelper.getResourceLocation(ITEM_TEXTURE_LOCATION + "unitStand.png");
        public static final ResourceLocation BYAKUSHIKI_CHEST = ResourceLocationHelper.getResourceLocation(ITEM_TEXTURE_LOCATION + "byakushikiChest.png");
        public static final ResourceLocation TABANE_SPAWN_EGG = ResourceLocationHelper.getResourceLocation(ITEM_TEXTURE_LOCATION + "tabaneSpawnEgg.png");
        public static final ResourceLocation TABANE_SPAWN_EGG_OVERLAY = ResourceLocationHelper.getResourceLocation(ITEM_TEXTURE_LOCATION + "tabaneSpawnEgg_overlay.png");
        public static final ResourceLocation KURO_AKIKO_CHEST = ResourceLocationHelper.getResourceLocation(ITEM_TEXTURE_LOCATION + "kuroAkikoChest.png");
    }

    public static final class Materials
    {
        public static final String MATERIAL_TEXTURE_LOCATION = "textures/materials/";

        public static final ResourceLocation IS_ARMOR = ResourceLocationHelper.getResourceLocation(MATERIAL_TEXTURE_LOCATION + "isarmormaterial.png");
        public static final ResourceLocation IS_SWORD = ResourceLocationHelper.getResourceLocation(MATERIAL_TEXTURE_LOCATION + "isWeaponMaterial.png");
    }

    public static final class Block
    {
        public static final String BLOCK_TEXTURE_LOCATION = "textures/blocks/";

        public static final ResourceLocation Flag = ResourceLocationHelper.getResourceLocation(BLOCK_TEXTURE_LOCATION + "flag.png");
        public static final ResourceLocation IS_UNIT_STATION = ResourceLocationHelper.getResourceLocation(BLOCK_TEXTURE_LOCATION + "isUnitStation.png");
        public static final ResourceLocation UNIT_STAND = ResourceLocationHelper.getResourceLocation(BLOCK_TEXTURE_LOCATION + "unitStand.png");

    }

    public static final class Gui
    {
        public static final String GUI_TEXTURE_LOCATION = "textures/gui/";

        public static final ResourceLocation IS_UNIT_STATION_GUI = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "unitStation.png");
        public static final ResourceLocation UNIT_STAND_GUI = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "unitStand.png");
        public static final ResourceLocation OVERLAY = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "overlay.png");
        public static final ResourceLocation WIDGETS = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "widgets.png");
        public static final ResourceLocation SHIELD_BAR = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "shieldBar.png");

        public static final class Elements
        {
            protected static final String ELEMENT_TEXTURE_LOCATION = GUI_TEXTURE_LOCATION + "elements/";
            public static final ResourceLocation ARROW_LEFT = ResourceLocationHelper.getResourceLocation(ELEMENT_TEXTURE_LOCATION + "arrowLeft.png");
            public static final ResourceLocation ARROW_RIGHT = ResourceLocationHelper.getResourceLocation(ELEMENT_TEXTURE_LOCATION + "arrowRight.png");
            public static final ResourceLocation SLIDER_VERTICAL_ENABLED = ResourceLocationHelper.getResourceLocation(ELEMENT_TEXTURE_LOCATION + "sliderVerticalEnabled.png");
            public static final ResourceLocation SLIDER_VERTICAL_DISABLED = ResourceLocationHelper.getResourceLocation(ELEMENT_TEXTURE_LOCATION + "sliderVerticalDisabled.png");
            public static final ResourceLocation BUTTON_ENABLED = ResourceLocationHelper.getResourceLocation(ELEMENT_TEXTURE_LOCATION + "buttonEnabled.png");
            public static final ResourceLocation BUTTON_DISABLED = ResourceLocationHelper.getResourceLocation(ELEMENT_TEXTURE_LOCATION + "buttonDisabled.png");
            public static final ResourceLocation BUTTON_HOVER = ResourceLocationHelper.getResourceLocation(ELEMENT_TEXTURE_LOCATION + "buttonHover.png");
            public static final ResourceLocation BUTTON_SORT_OPTION = ResourceLocationHelper.getResourceLocation(ELEMENT_TEXTURE_LOCATION + "buttonSortOption.png");
            public static final ResourceLocation BUTTON_SORT_ORDER = ResourceLocationHelper.getResourceLocation(ELEMENT_TEXTURE_LOCATION + "buttonSortOrder.png");
        }
    }

    public static final class Model
    {
        public static final String MODEL_TEXTURE_LOCATION = "textures/models/";

        public static final ResourceLocation BYAKUSHIKI_CHEST = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "byakushiki_1.png");
        public static final ResourceLocation KURO_AKIKO_TEST = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "kuroAkiko_1.png");
    }

    public static final class Villagers
    {
        public static final String VILLAGER_TEXTURE_LOCATION = "textures/entity/";

        public static final ResourceLocation TABANE = ResourceLocationHelper.getResourceLocation(VILLAGER_TEXTURE_LOCATION + "tabane_villager.png");
    }

    public static final class MTL
    {
        public static final String MTL_LOCATION = "models/";

        public static final ResourceLocation ELUCIDATOR = ResourceLocationHelper.getResourceLocation(MTL_LOCATION + "elucidator.mtl");
    }
}
