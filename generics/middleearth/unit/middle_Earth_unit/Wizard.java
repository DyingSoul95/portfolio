package com.intellekta.generics.middleearth.unit.middle_Earth_unit;

import com.intellekta.generics.middleearth.animal.Animal;
import com.intellekta.generics.middleearth.animal.Horse;
import com.intellekta.generics.middleearth.unit.unit_interface.MiddleEarthUnit;
import com.intellekta.generics.middleearth.unit.unit_interface.Unit;

public class Wizard extends CavalryHuman implements MiddleEarthUnit {

    Animal horse = new Horse();
    private int power = 20;
    private String name;

    public Wizard(String name) {
        super(name);
    }

    public Animal getHorse() {
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
