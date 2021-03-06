package com.sparta.is.core.utils;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;

public class LoaderUtils
{
    public static LoaderState getLoaderState() {

        if (Loader.instance().isInState(LoaderState.PREINITIALIZATION)) {
            return LoaderState.PREINITIALIZATION;
        }
        else if (Loader.instance().isInState(LoaderState.INITIALIZATION)) {
            return LoaderState.INITIALIZATION;
        }
        else if (Loader.instance().isInState(LoaderState.POSTINITIALIZATION)) {
            return LoaderState.POSTINITIALIZATION;
        }
        else if (Loader.instance().isInState(LoaderState.AVAILABLE)) {
            return LoaderState.AVAILABLE;
        }
        else if (Loader.instance().isInState(LoaderState.SERVER_ABOUT_TO_START)) {
            return LoaderState.SERVER_ABOUT_TO_START;
        }
        else if (Loader.instance().isInState(LoaderState.SERVER_STARTING)) {
            return LoaderState.SERVER_STARTING;
        }
        else if (Loader.instance().isInState(LoaderState.SERVER_STARTED)) {
            return LoaderState.SERVER_STARTED;
        }
        else if (Loader.instance().isInState(LoaderState.SERVER_STARTED)) {
            return LoaderState.SERVER_STARTED;
        }
        else if (Loader.instance().isInState(LoaderState.SERVER_STARTED)) {
            return LoaderState.SERVER_STARTED;
        }
        else if (Loader.instance().isInState(LoaderState.SERVER_STOPPING)) {
            return LoaderState.SERVER_STARTED;
        }
        else if ( Loader.instance().isInState(LoaderState.SERVER_STOPPED)) {
            return LoaderState.SERVER_STARTED;
        }
        else {
            return null;
        }
    }
}
