package com.ardacraft.ardagrass;

import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.Baker;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
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
    public void setParents(Function<Identifier, UnbakedModel> modelLoader) {
        this.baseModel.setParents(modelLoader);
    }

    @Nullable
    @Override
    public BakedModel bake(Baker loader, Function<SpriteIdentifier, Sprite> textureGetter, ModelBakeSettings rotationContainer, Identifier modelId) {
        return new ArdaGrassBakedModel(this.baseModel.bake(loader, textureGetter, rotationContainer, modelId));
    }
}
