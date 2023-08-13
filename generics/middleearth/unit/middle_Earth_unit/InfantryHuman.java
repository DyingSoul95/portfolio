package com.intellekta.generics.middleearth.unit.middle_Earth_unit;

import com.intellekta.generics.middleearth.unit.abstraktClass.AbstractUnit;
import com.intellekta.generics.middleearth.unit.unit_interface.MiddleEarthUnit;
import com.intellekta.generics.middleearth.unit.unit_interface.Unit;

public class InfantryHuman extends AbstractUnit implements MiddleEarthUnit {
    private int power = 7 + (int) (Math.random() + 0.5);
    private String name;

    @Override
    public boolean isAlive() {
        return super.isAlive();
    }

    public InfantryHuman(String name) {
        super(name);
    }

    @Override
    public <T extends Unit> void strike(T unit) {
        super.strike(unit);
    }

    @Override
    public int getPower() {
        return this.power;
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
