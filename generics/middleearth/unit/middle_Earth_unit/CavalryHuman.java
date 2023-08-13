package com.intellekta.generics.middleearth.unit.middle_Earth_unit;

import com.intellekta.generics.middleearth.animal.Animal;
import com.intellekta.generics.middleearth.animal.Horse;
import com.intellekta.generics.middleearth.unit.abstraktClass.AbstractCavalryUnit;
import com.intellekta.generics.middleearth.unit.unit_interface.MiddleEarthUnit;
import com.intellekta.generics.middleearth.unit.unit_interface.Unit;

public class CavalryHuman extends AbstractCavalryUnit implements MiddleEarthUnit {

    Animal horse = new Horse();
    private int power = 7 + (int) (Math.random() + 0.5);
    private String name;

    @Override
    public String getName() {
        return super.getName();
    }

    public CavalryHuman(String name) {
        super(name);
    }

    public Animal getAnimal() {
        return this.horse;
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
        if (horse.isAlive())
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
        horse.setPower(horse.getPower() - damage);
    }
}
