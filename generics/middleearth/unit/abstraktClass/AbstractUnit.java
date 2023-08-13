package com.intellekta.generics.middleearth.unit.abstraktClass;

import com.intellekta.generics.middleearth.unit.middle_Earth_unit.InfantryHuman;
import com.intellekta.generics.middleearth.unit.type_of_army_interface.Infantry;
import com.intellekta.generics.middleearth.unit.unit_interface.Unit;

public abstract class AbstractUnit implements Infantry {

    String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isAlive() {
        return getPower() > 0;
    }

    public AbstractUnit(String name) {
        this.name = name;
    }

    public <T extends Unit> void strike(T unit) {
        damage(unit.getPower());
    }

    public abstract int getPower();

    private void damage(int dmg) {
        this.setPower(this.getPower() - dmg);
    }

    public abstract void setPower(int power);


}
