package com.phasetranscrystal.deliciousonterra.item;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import com.phasetranscrystal.deliciousonterra.dnd.AddonDiceSystem;
import com.phasetranscrystal.deliciousonterra.screen.RollDiceScreen;

public class TestItem extends Item {

    public TestItem(Properties p_41383_) {
        super(p_41383_);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (level.isClientSide) {
            AddonDiceSystem sys = new AddonDiceSystem("dice_test", 15);
            sys.addRollTime(99);
            Minecraft.getInstance().setScreen(new RollDiceScreen(sys));
        }
        return super.use(level, player, interactionHand);
    }
}
