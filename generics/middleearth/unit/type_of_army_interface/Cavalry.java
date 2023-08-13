package com.intellekta.generics.middleearth.unit.type_of_army_interface;

import com.intellekta.generics.middleearth.animal.Animal;
import com.intellekta.generics.middleearth.animal.Horse;
import com.intellekta.generics.middleearth.unit.unit_interface.Unit;

public interface Cavalry extends Unit {
    Animal getAnimal();
}
