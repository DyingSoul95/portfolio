package com.intellekta.generics.middleearth.unit.abstraktClass;

import com.intellekta.generics.middleearth.animal.Animal;
import com.intellekta.generics.middleearth.unit.type_of_army_interface.Cavalry;
import com.intellekta.generics.middleearth.unit.unit_interface.Unit;

public abstract class AbstractCavalryUnit extends AbstractUnit implements Cavalry {


    public AbstractCavalryUnit(String name) {
        super(name);
    }

    @Override
    public abstract int getPower();

    @Override
    public abstract void setPower(int power);

    @Override
    public boolean isAlive() {
        return super.isAlive();
    }


    public abstract <T extends Unit> void strike(T unit);

    public abstract <T extends Unit> void damageUnit(int damage);

    public abstract <T extends Animal> void damageAnimal(int damage);

}
