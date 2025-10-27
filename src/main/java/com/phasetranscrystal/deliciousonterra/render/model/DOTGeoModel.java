package com.phasetranscrystal.deliciousonterra.render.model;

import net.minecraft.resources.ResourceLocation;

import com.phasetranscrystal.deliciousonterra.DeliciousOnTerra;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class DOTGeoModel<T extends GeoAnimatable> extends GeoModel<T> {

    public ResourceLocation modelLocation;
    public ResourceLocation textLoc;
    public ResourceLocation animationLoc;

    public DOTGeoModel(ResourceLocation modelLocation, ResourceLocation textLoc, ResourceLocation animationLoc) {
        this.modelLocation = modelLocation;
        this.textLoc = textLoc;
        this.animationLoc = animationLoc;
    }

    public DOTGeoModel(String modelLoc, String textLoc, String animationLoc) {
        this(DeliciousOnTerra.byPath(modelLoc), DeliciousOnTerra.byPath(textLoc), DeliciousOnTerra.byPath(animationLoc));
    }

    public DOTGeoModel(String name, String type) {
        this(
                DeliciousOnTerra.byPath("geo/" + type + "/" + name + ".geo.json"),
                DeliciousOnTerra.byPath("textures/" + type + "/" + name + ".png"),
                DeliciousOnTerra.byPath("animations/" + name + "_animation.json"));
    }

    @Override
    public ResourceLocation getModelResource(T t) {
        return modelLocation;
    }

    @Override
    public ResourceLocation getTextureResource(T t) {
        return textLoc;
    }

    @Override
    public ResourceLocation getAnimationResource(T t) {
        return animationLoc;
    }
}
