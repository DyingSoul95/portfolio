package com.intellekta.generics.middleearth.animal;

public class Warg implements Animal {
    private int power = 4 + (int) (Math.random() + 2.5);

    public Warg() {
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if (power < this.power)
            this.power = power;
        if (this.power < 0)
            this.power = 0;
    }

    public boolean isAlive() {
        return getPower() > 0;
    }
}
