package com.ardacraft.ardagrass;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.text.TranslatableTextContent;

public class ArdaGrass implements ClientModInitializer {


    public static ArdaGrassConfig ardaGrassConfig;

    @Override
    public void onInitializeClient() {
        Log.info(LogCategory.LOG, "ArdaGrass Enabled");
        AutoConfig.register(ArdaGrassConfig.class, GsonConfigSerializer::new);
        ardaGrassConfig = AutoConfig.getConfigHolder(ArdaGrassConfig.class).getConfig();
    }
}
