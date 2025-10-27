package com.phasetranscrystal.deliciousonterra.dnd;

import java.util.concurrent.atomic.AtomicInteger;

public class AddonDiceSystem {

    public final String title;
    private final Dice dice;
    private final AddonValue addonValue;
    private final int TARGET_VALUE;
    private int rolled = 0;
    private int rollTime = 1;
    public boolean isStart = false;

    public AddonDiceSystem(String title, AddonValue addonValue, int targetValue) {
        this.title = title;
        this.dice = new Dice(20);
        this.addonValue = addonValue;
        this.TARGET_VALUE = targetValue;
    }

    public AddonDiceSystem(String title, int targetValue) {
        this.title = title;
        this.dice = new Dice(20);
        this.addonValue = new AddonValue();
        this.TARGET_VALUE = targetValue;
    }

    private void roll() {
        AtomicInteger rolled = new AtomicInteger(dice.roll());
        if (rolled.get() > 3 || rolled.get() < 20) {
            this.addonValue.getAddonValue().forEach((s, check) -> {
                if (DNDConfig.debug) {
                    int roll = rolled.get();
                    rolled.addAndGet(check);
                    System.out.println("name: " + s + " addon " + check);
                    System.out.println(roll + " -> " + rolled.get());
                } else {
                    rolled.addAndGet(check);
                }
            });
            this.rolled = rolled.get();
        }
        this.rollTime -= 1;
    }

    public void addRollTime(int time) {
        this.rollTime += time;
    }

    public boolean startRoll() {
        if (rollTime > 0) {
            isStart = true;
            this.roll();
            return this.testResult();
        }
        System.out.println("error : rollTime = 0");
        return false;
    }

    public int getRolled() {
        return rolled;
    }

    public int getRollTime() {
        return rollTime;
    }

    public int getTARGET_VALUE() {
        return TARGET_VALUE;
    }

    public AddonValue getAddonValue() {
        return addonValue;
    }

    public boolean testResult() {
        if (rolled >= TARGET_VALUE) {
            System.out.println("success: " + rolled + " >= " + this.TARGET_VALUE);
            return true;
        } else if (rolled == 1) {
            System.out.println("big fail: " + rolled + " < " + this.TARGET_VALUE);
            return false;
        } else if (rolled == 20) {
            System.out.println("big success: MAX" + " >= " + this.TARGET_VALUE);
            return true;
        } else {
            System.out.println("fail: " + rolled + " < " + this.TARGET_VALUE);
            return false;
        }
    }
}
