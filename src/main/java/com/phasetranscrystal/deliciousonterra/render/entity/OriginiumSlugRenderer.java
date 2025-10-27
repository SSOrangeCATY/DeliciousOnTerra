package com.phasetranscrystal.deliciousonterra.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

import com.phasetranscrystal.deliciousonterra.entity.OriginiumSlug;
import com.phasetranscrystal.deliciousonterra.render.model.DOTGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OriginiumSlugRenderer extends GeoEntityRenderer<OriginiumSlug> {

    public static final DOTGeoModel<OriginiumSlug> MODEL = new DOTGeoModel<>("originium_slug", "entity");

    public OriginiumSlugRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, MODEL);
        super.withScale(0.5f);
    }
}
