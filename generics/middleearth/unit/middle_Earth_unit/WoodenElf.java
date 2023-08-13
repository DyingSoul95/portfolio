package com.intellekta.generics.middleearth.unit.middle_Earth_unit;

import com.intellekta.generics.middleearth.unit.abstraktClass.AbstractUnit;
import com.intellekta.generics.middleearth.unit.unit_interface.MiddleEarthUnit;
import com.intellekta.generics.middleearth.unit.unit_interface.Unit;

public class WoodenElf extends AbstractUnit implements MiddleEarthUnit {

    private int power = 6;
    private String name;

    @Override
    public boolean isAlive() {
        return super.isAlive();
    }

    public WoodenElf(String name) {
        super(name);
    }

    @Override
    public <T extends Unit> void strike(T unit) {
        super.strike(unit);
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public void setPower(int power) {
        if (power < this.power)
            this.power = power;
        if (this.power < 0)
            this.power = 0;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + name + " " + power;
    }
}
