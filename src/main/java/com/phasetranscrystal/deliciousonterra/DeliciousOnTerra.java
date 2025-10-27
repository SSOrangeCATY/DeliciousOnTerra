package com.phasetranscrystal.deliciousonterra;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import com.mojang.logging.LogUtils;
import com.phasetranscrystal.deliciousonterra.entity.EntityRegister;
import com.phasetranscrystal.deliciousonterra.item.DOTItemTabs;
import com.phasetranscrystal.deliciousonterra.item.ItemRegister;
import com.phasetranscrystal.deliciousonterra.render.entity.OriginiumSlugRenderer;
import org.slf4j.Logger;

@Mod(DeliciousOnTerra.MODID)
public class DeliciousOnTerra {

    public static final String MODID = "deliciousonterra";
    private static final Logger LOGGER = LogUtils.getLogger();

    public DeliciousOnTerra(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        EntityRegister.ENTITY_TYPE.register(modEventBus);
        ItemRegister.ITEMS.register(modEventBus);
        DOTItemTabs.CREATIVE_MODE_TABS.register(modEventBus);
        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {}

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {}

        @SubscribeEvent
        public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(EntityRegister.ORIGINIUM_SLUG.get(), OriginiumSlugRenderer::new);
        }
    }

    public static ResourceLocation byPath(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
