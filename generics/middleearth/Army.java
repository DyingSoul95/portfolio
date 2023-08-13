package com.intellekta.generics.middleearth;

import com.intellekta.generics.middleearth.unit.type_of_army_interface.Cavalry;
import com.intellekta.generics.middleearth.unit.type_of_army_interface.Infantry;
import com.intellekta.generics.middleearth.unit.unit_interface.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Army<T extends Unit> {
    private List<T> tList;

    public Army(List<T> tList) {
        this.tList = (new ArrayList<>(tList));
    }

    private List<T> gettList() {
        return tList;
    }

    public List<T> getCavalry() {
        List<T> cavalryList = new ArrayList<>();
        for (T t : gettList()) {
            if (t instanceof Cavalry)
                cavalryList.add(t);
        }
        return cavalryList;
    }

    public List<T> getInfantry() {
        List<T> infantryList = new ArrayList<>();
        for (T t : gettList()) {
            if (t instanceof Infantry)
                infantryList.add(t);
        }
        return infantryList;
    }

    public List<T> getArmy() {
        List<T> sortList = new ArrayList<>();
        for (T t : gettList()) {
            if (t instanceof Cavalry)
                sortList.add(t);
        }
        for (T t : gettList()) {
            if (t instanceof Infantry)
                sortList.add(t);
        }
        return sortList;
    }

    public void recruit(T t) {
        if (tList.add(t))
            System.out.println("true");
        else
            System.out.println("false");
    }

    public void print() {
        List<T> sortList = new ArrayList<>();
        for (T t : gettList()) {
            if (t instanceof Cavalry)
                sortList.add(t);
        }
        for (T t : gettList()) {
            if (t instanceof Infantry)
                sortList.add(t);
        }
        for (T s : sortList) {
            System.out.println(s);
        }
    }

    public void release(T t) {
        boolean tof = false;
        for (int i = 0; i < tList.size(); i++) {
            if (tList.get(i).equals(t)) {
                tList.remove(tList.get(i));
                tof = true;
            }
        }
        System.out.println(tof);
    }

    public T getRandomUnit() {
        int randomIndex;
        randomIndex = (int) (Math.random() * (tList.size() - 0.6));
        if (tList.get(randomIndex) == null) {
            return null;
        } else {
            return tList.get(randomIndex);
        }
    }

    public T getRandomUnit(T t) {
        List<T> sortList = new ArrayList<>();
        if (t instanceof Infantry) {
            for (T t1 : gettList()) {
                if (t1 instanceof Infantry)
                    sortList.add(t1);
            }
        } else if (t instanceof Cavalry) {
            for (T t1 : gettList()) {
                if (t1 instanceof Cavalry)
                    sortList.add(t1);
            }
        } else
            return null;
        int randomIndex;
        randomIndex = (int) (Math.random() * (sortList.size() - 0.5));

        if (sortList.get(randomIndex) == null) {
            return null;
        } else {
            return sortList.get(randomIndex);
        }
    }


    @Override
    public String toString() {
        return "Army{" +
                "tList=" + tList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army<?> army = (Army<?>) o;
        return tList.equals(army.tList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tList);
    }
}

