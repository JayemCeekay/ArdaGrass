package com.ardacraft.ardagrass;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;

public class ArdaGrass implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Log.info(LogCategory.LOG, "ArdaGrass Enabled");
    }
}
