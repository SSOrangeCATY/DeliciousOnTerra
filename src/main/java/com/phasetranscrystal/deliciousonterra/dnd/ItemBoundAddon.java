package com.phasetranscrystal.deliciousonterra.dnd;

import net.minecraft.world.item.ItemStack;

public class ItemBoundAddon extends AddonValue {

    public final ItemStack itemStack;

    public ItemBoundAddon(ItemStack item, int addon) {
        super.setAddonValue(item.getDisplayName().toString(), addon);
        this.itemStack = item;
    }

    public boolean testWithoutNBT(ItemStack itemStack) {
        return this.itemStack.getItem() == itemStack.getItem();
    }
}
