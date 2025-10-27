package com.phasetranscrystal.deliciousonterra.dnd;

import net.minecraft.world.entity.EntityType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class Core {

    protected final Map<EntityType<?>, AddonValue> entityBaseAddon = new HashMap<>();
    protected final Map<UUID, AddonValue> dynamicAddon = new HashMap<>();
    public static final Random RANDOM = new Random();
}
