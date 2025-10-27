package com.phasetranscrystal.deliciousonterra.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import com.phasetranscrystal.deliciousonterra.DeliciousOnTerra;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class DOTItemTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DeliciousOnTerra.MODID);
    public static final List<DeferredItem<? extends Item>> ITEMS = new ArrayList<>();
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DELICIOUSONTERRA_TAB = CREATIVE_MODE_TABS.register("deliciousonterra_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.deliciousonterra")) // The language key for the title of your
                                                                         // CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(ItemRegister.TEST_ITEM.asItem()::getDefaultInstance)
            .displayItems((parameters, output) -> ITEMS.stream().map(Supplier::get).map(Item::getDefaultInstance).forEach(output::accept))
            .build());
}
