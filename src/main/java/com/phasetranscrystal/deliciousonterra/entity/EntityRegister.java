package com.phasetranscrystal.deliciousonterra.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import com.phasetranscrystal.deliciousonterra.DeliciousOnTerra;

@EventBusSubscriber(modid = DeliciousOnTerra.MODID, bus = EventBusSubscriber.Bus.MOD)
public class EntityRegister {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(Registries.ENTITY_TYPE, DeliciousOnTerra.MODID);
    public static final DeferredHolder<EntityType<?>, EntityType<OriginiumSlug>> ORIGINIUM_SLUG = ENTITY_TYPE.register("originium_slug", () -> EntityType.Builder.of(OriginiumSlug::new, MobCategory.MONSTER).sized(0.5F, 0.5F).eyeHeight(0.25F).passengerAttachments(0.2375F).clientTrackingRange(8).build("originium_slug"));

    @SubscribeEvent
    public static void createEntityAttribute(EntityAttributeCreationEvent event) {
        event.put(ORIGINIUM_SLUG.get(), OriginiumSlug.createAttributes().build());
    }
}
