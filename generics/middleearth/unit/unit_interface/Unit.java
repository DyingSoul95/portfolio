package com.intellekta.generics.middleearth.unit.unit_interface;

public interface Unit {
    boolean isAlive();
    <T extends Unit> void strike(T unit);
    int getPower();

    String getName();
}
