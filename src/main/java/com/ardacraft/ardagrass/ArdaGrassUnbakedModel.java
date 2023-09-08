package com.ardacraft.ardagrass;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

public class ArdaGrassUnbakedModel implements UnbakedModel {
    private final UnbakedModel baseModel;

    public ArdaGrassUnbakedModel(UnbakedModel unbakedModel) {
        this.baseModel = unbakedModel;
    }

    @Override
    public Collection<Identifier> getModelDependencies() {
        return this.baseModel.getModelDependencies();
    }

    @Override
    public Collection<SpriteIdentifier> getTextureDependencies(Function<Identifier, UnbakedModel> unbakedModelGetter, Set<Pair<String, String>> unresolvedTextureReferences) {
        var baseIds = this.baseModel.getTextureDependencies(unbakedModelGetter, unresolvedTextureReferences);
        return new ArrayList<>(baseIds);
    }

    @Nullable
    @Override
    public BakedModel bake(ModelLoader loader, Function<SpriteIdentifier, Sprite> textureGetter, ModelBakeSettings rotationContainer, Identifier modelId) {
        return new ArdaGrassBakedModel(this.baseModel.bake(loader, textureGetter, rotationContainer, modelId));
    }
}
