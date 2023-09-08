package com.ardacraft.ardagrass.mixin;

import com.ardacraft.ardagrass.ArdaGrassBakedModel;
import com.ardacraft.ardagrass.ArdaGrassUnbakedModel;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.block.GrassBlock;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Mixin(ModelLoader.class)
public class ArdaGrassModelLoaderMixin {

    private List<String> betterGrassNames =
            List.of("minecraft:grass_block",
                    "minecraft:podzol",
                    "minecraft:mycelium",
                    "minecraft:crimson_nylium",
                    "minecraft:warped_nylium",
                    "conquest:clover_covered_grass",
                    "conquest:taiga_grass");

    @Shadow
    @Final
    private Map<Identifier, UnbakedModel> unbakedModels;

    @Shadow
    @Final
    private Set<Identifier> modelsToLoad;

    @Inject(method = "putModel", at = @At("HEAD"), cancellable = true)
    private void onPutModel(Identifier id, UnbakedModel unbakedModel, CallbackInfo ci) {
        if (id instanceof ModelIdentifier modelId) {
            if (!modelId.getVariant().equals("inventory")) {
                betterGrassNames.forEach(s -> {
                    if (modelId.toString().startsWith(s)) {
                        var newModel = new ArdaGrassUnbakedModel(unbakedModel);
                        this.unbakedModels.put(id, newModel);
                        this.modelsToLoad.addAll(newModel.getModelDependencies());
                        ci.cancel();
                    }
                });

            }
        }
    }
}
