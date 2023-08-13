package com.intellekta.generics.middleearth.unit.mordor_unit;

import com.intellekta.generics.middleearth.animal.Animal;
import com.intellekta.generics.middleearth.animal.Warg;
import com.intellekta.generics.middleearth.unit.abstraktClass.AbstractCavalryUnit;
import com.intellekta.generics.middleearth.unit.unit_interface.MordorUnit;
import com.intellekta.generics.middleearth.unit.unit_interface.Unit;

public class CavalryOrc extends AbstractCavalryUnit implements MordorUnit {

    Animal warg = new Warg();
    private int power = 8 + (int) (Math.random() + 1.5);
    private String name;

    @Override
    public String getName() {
        return super.getName();
    }

    public CavalryOrc(String name) {
        super(name);
    }

    public Animal getAnimal() {
        return this.warg;
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
    public boolean isAlive() {
        return super.isAlive();
    }

    @Override
    public <T extends Unit> void strike(T unit) {
        if (warg.isAlive())
            damageAnimal(unit.getPower());
        else
            damageUnit(unit.getPower());
    }

    @Override
    public <T extends Unit> void damageUnit(int damage) {
        setPower(getPower() - damage);
    }

    @Override
    public <T extends Animal> void damageAnimal(int damage) {
        warg.setPower(warg.getPower() - damage);
    }
}
