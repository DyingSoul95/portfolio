package com.intellekta.generics.middleearth.unit.mordor_unit;

import com.intellekta.generics.middleearth.unit.abstraktClass.AbstractUnit;
import com.intellekta.generics.middleearth.unit.unit_interface.MordorUnit;
import com.intellekta.generics.middleearth.unit.unit_interface.Unit;

public class UrukHai extends AbstractUnit implements MordorUnit {

    private int power = 10 + (int) (Math.random() + 1.5);

    private String name;

    @Override
    public boolean isAlive() {
        return super.isAlive();
    }

    public UrukHai(String name) {
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
