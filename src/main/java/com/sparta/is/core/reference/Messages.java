package com.sparta.is.core.reference;

public class Messages
{

    public static final String ENERGY_VALUE = "misc.is:energy-value";

    /* Fingerprint check related constants */
    public static final String NO_FINGERPRINT_MESSAGE = "The copy of Infinite Stratos that you are running is a development version of the mod, and as such may be unstable and/or incomplete.";
    public static final String INVALID_FINGERPRINT_MESSAGE = "The copy of Infinte Stratos that you are running has been modified from the original, and unpredictable things may happen. Please consider re-downloading the original version of the mod.";

    public static final class Commands
    {
        //TODO: Update Commands List

        private static final String COMMAND_PREFIX = "commands.ee3.";

        public static final String BASE_COMMAND_USAGE = COMMAND_PREFIX + "usage";

        public static final String INVALID_NBT_TAG_ERROR = COMMAND_PREFIX + "invalid-nbt-tag.error";
        public static final String NO_ITEM = COMMAND_PREFIX + "no-item.error";

        public static final String SET_ENERGY_VALUE_USAGE = COMMAND_PREFIX + Names.Commands.SET_ENERGY_VALUE + ".usage";
        public static final String SET_ENERGY_VALUE_SUCCESS = COMMAND_PREFIX + Names.Commands.SET_ENERGY_VALUE + ".success";

        public static final String SET_ENERGY_VALUE_CURRENT_ITEM_USAGE = COMMAND_PREFIX + Names.Commands.SET_ENERGY_VALUE_CURRENT_ITEM + ".usage";
        public static final String SET_ENERGY_VALUE_CURRENT_ITEM_SUCCESS = COMMAND_PREFIX + Names.Commands.SET_ENERGY_VALUE_CURRENT_ITEM + ".success";

        public static final String REGEN_ENERGY_VALUES_USAGE = COMMAND_PREFIX + Names.Commands.REGEN_ENERGY_VALUES + ".usage";
        public static final String REGEN_ENERGY_VALUES_SUCCESS = COMMAND_PREFIX + Names.Commands.REGEN_ENERGY_VALUES + ".success";
        public static final String REGEN_ENERGY_VALUES_DENIED = COMMAND_PREFIX + Names.Commands.REGEN_ENERGY_VALUES + ".denied";

        public static final String SYNC_ENERGY_VALUES_USAGE = COMMAND_PREFIX + Names.Commands.SYNC_ENERGY_VALUES + ".usage";
        public static final String SYNC_ENERGY_VALUES_SUCCESS = COMMAND_PREFIX + Names.Commands.SYNC_ENERGY_VALUES + ".success";
        public static final String SYNC_ENERGY_VALUES_DENIED = COMMAND_PREFIX + Names.Commands.SYNC_ENERGY_VALUES + ".denied";

        public static final String PLAYER_LEARN_ITEM_USAGE = COMMAND_PREFIX + Names.Commands.PLAYER_LEARN_ITEM + ".usage";
        public static final String PLAYER_LEARN_ITEM_SUCCESS = COMMAND_PREFIX + Names.Commands.PLAYER_LEARN_ITEM + ".success";

        public static final String PLAYER_LEARN_CURRENT_ITEM_USAGE = COMMAND_PREFIX + Names.Commands.PLAYER_LEARN_CURRENT_ITEM + ".usage";
        public static final String PLAYER_LEARN_CURRENT_ITEM_SUCCESS = COMMAND_PREFIX + Names.Commands.PLAYER_LEARN_CURRENT_ITEM + ".success";

        public static final String PLAYER_FORGET_EVERYTHING_USAGE = COMMAND_PREFIX + Names.Commands.PLAYER_FORGET_EVERYTHING + ".usage";
        public static final String PLAYER_FORGET_EVERYTHING_SUCCESS = COMMAND_PREFIX + Names.Commands.PLAYER_FORGET_EVERYTHING + ".success";

        public static final String PLAYER_FORGET_ITEM_USAGE = COMMAND_PREFIX + Names.Commands.PLAYER_FORGET_ITEM + ".usage";
        public static final String PLAYER_FORGET_ITEM_SUCCESS = COMMAND_PREFIX + Names.Commands.PLAYER_FORGET_ITEM + ".success";

        public static final String PLAYER_FORGET_CURRENT_ITEM_USAGE = COMMAND_PREFIX + Names.Commands.PLAYER_FORGET_CURRENT_ITEM + ".usage";
        public static final String PLAYER_FORGET_CURRENT_ITEM_SUCCESS = COMMAND_PREFIX + Names.Commands.PLAYER_FORGET_CURRENT_ITEM + ".success";

        public static final String SET_ITEM_LEARNABLE_USAGE = COMMAND_PREFIX + Names.Commands.SET_ITEM_LEARNABLE + ".usage";
        public static final String SET_ITEM_LEARNABLE_SUCCESS = COMMAND_PREFIX + Names.Commands.SET_ITEM_LEARNABLE + ".success";

        public static final String SET_CURRENT_ITEM_LEARNABLE_USAGE = COMMAND_PREFIX + Names.Commands.SET_ITEM_LEARNABLE + ".usage";
        public static final String SET_CURRENT_ITEM_LEARNABLE_SUCCESS = COMMAND_PREFIX + Names.Commands.SET_ITEM_LEARNABLE + ".success";
        public static final String SET_CURRENT_ITEM_LEARNABLE_NO_EFFECT = COMMAND_PREFIX + Names.Commands.SET_ITEM_LEARNABLE + ".no-effect";

        public static final String SET_ITEM_NOT_LEARNABLE_USAGE = COMMAND_PREFIX + Names.Commands.SET_ITEM_NOT_LEARNABLE + ".usage";
        public static final String SET_ITEM_NOT_LEARNABLE_SUCCESS = COMMAND_PREFIX + Names.Commands.SET_ITEM_NOT_LEARNABLE + ".success";

        public static final String SET_CURRENT_ITEM_NOT_LEARNABLE_USAGE = COMMAND_PREFIX + Names.Commands.SET_ITEM_NOT_LEARNABLE + ".usage";
        public static final String SET_CURRENT_ITEM_NOT_LEARNABLE_SUCCESS = COMMAND_PREFIX + Names.Commands.SET_ITEM_NOT_LEARNABLE + ".success";
        public static final String SET_CURRENT_ITEM_NOT_LEARNABLE_NO_EFFECT = COMMAND_PREFIX + Names.Commands.SET_ITEM_NOT_LEARNABLE + ".no-effect";

        public static final String SET_ITEM_RECOVERABLE_USAGE = COMMAND_PREFIX + Names.Commands.SET_ITEM_RECOVERABLE + ".usage";
        public static final String SET_ITEM_RECOVERABLE_SUCCESS = COMMAND_PREFIX + Names.Commands.SET_ITEM_RECOVERABLE + ".success";

        public static final String SET_CURRENT_ITEM_RECOVERABLE_USAGE = COMMAND_PREFIX + Names.Commands.SET_ITEM_RECOVERABLE + ".usage";
        public static final String SET_CURRENT_ITEM_RECOVERABLE_SUCCESS = COMMAND_PREFIX + Names.Commands.SET_ITEM_RECOVERABLE + ".success";
        public static final String SET_CURRENT_ITEM_RECOVERABLE_NO_EFFECT = COMMAND_PREFIX + Names.Commands.SET_ITEM_RECOVERABLE + ".no-effect";

        public static final String SET_ITEM_NOT_RECOVERABLE_USAGE = COMMAND_PREFIX + Names.Commands.SET_ITEM_NOT_RECOVERABLE + ".usage";
        public static final String SET_ITEM_NOT_RECOVERABLE_SUCCESS = COMMAND_PREFIX + Names.Commands.SET_ITEM_NOT_RECOVERABLE + ".success";

        public static final String SET_CURRENT_ITEM_NOT_RECOVERABLE_USAGE = COMMAND_PREFIX + Names.Commands.SET_ITEM_NOT_RECOVERABLE + ".usage";
        public static final String SET_CURRENT_ITEM_NOT_RECOVERABLE_SUCCESS = COMMAND_PREFIX + Names.Commands.SET_ITEM_NOT_RECOVERABLE + ".success";
        public static final String SET_CURRENT_ITEM_NOT_RECOVERABLE_NO_EFFECT = COMMAND_PREFIX + Names.Commands.SET_ITEM_NOT_RECOVERABLE + ".no-effect";

        public static final String RUN_TEST_USAGE = COMMAND_PREFIX + Names.Commands.RUN_TEST + ".usage";
        public static final String RUN_TESTS_SUCCESS = COMMAND_PREFIX + Names.Commands.RUN_TEST + ".success";
        public static final String RUN_TESTS_NOT_FOUND = COMMAND_PREFIX + Names.Commands.RUN_TEST + ".notfound";
        public static final String RUN_TESTS_DIR_NOT_FOUND = COMMAND_PREFIX + Names.Commands.RUN_TEST + ".dir-notfound";

        public static final String ADMIN_USAGE = COMMAND_PREFIX + Names.Commands.ADMIN_PANEL + ".usage";
    }

    public static final class ItemUse
    {
        public static final String SWORD_SWING_FAILED = "misc.is:swingSwordFailed";
        public static final String INVALID_UNIT = "misc.is:invalidUnit";
        public static final String ONE_OFF_ACTIVE = "misc.is:oneOffActive";
        public static final String ONE_OFF_DEACTIVE = "misc.is:oneOffDeactive";
        public static final String INVALID_ACTION = "misc.is:invalidAction";

    }

    public static final class ToolTips
    {
        public static final String ITEM_BELONGS_TO_NO_ONE = "tooltip.is:belongsToNoOne";
        public static final String ITEM_BELONGS_TO = "tooltip.is:belongsTo";
        public static final String NO_ENERGY_VALUE = "tooltip.is:noEnergyValue";
    }

    public static final class UnitControl
    {
        public static final String OWNER_SET_TO_SELF = "misc.is:owner-set-to-self";
        public static final String ALREADY_OWNER = "misc.is:alreadyOwner";
        public static final String INVALID_OWNER = "misc.is:invalidOwner";
    }

}