package com.phasetranscrystal.deliciousonterra.dnd;

public class Dice {

    private final int SIDES;

    public Dice(int sides) {
        if (sides < 4 || sides > 20) throw new RuntimeException("unable create : sides unavailable!");
        this.SIDES = sides;
    }

    public int roll() {
        return Core.RANDOM.nextInt(1, SIDES + 1);
    }

    public int getSides() {
        return SIDES;
    }
}
