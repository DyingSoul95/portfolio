package com.intellekta.generics.middleearth;

import com.intellekta.generics.middleearth.unit.middle_Earth_unit.*;
import com.intellekta.generics.middleearth.unit.mordor_unit.CavalryOrc;
import com.intellekta.generics.middleearth.unit.mordor_unit.Goblin;
import com.intellekta.generics.middleearth.unit.mordor_unit.InfantryOrc;
import com.intellekta.generics.middleearth.unit.mordor_unit.UrukHai;
import com.intellekta.generics.middleearth.unit.unit_interface.MiddleEarthUnit;
import com.intellekta.generics.middleearth.unit.unit_interface.MordorUnit;
import com.intellekta.generics.middleearth.unit.unit_interface.Unit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Battle {


    public void fight() {
        //Определяем численность армий
        int countOfMiddleEarthUnit = 10 + (int) (Math.random() * 50);
        int countOfMordorUnit = (int) (countOfMiddleEarthUnit * 0.8) + (int) (countOfMiddleEarthUnit * 0.4 * Math.random());

        //Создаем списки армий
        List<MiddleEarthUnit> earthUnitList = new ArrayList<>();
        List<MordorUnit> mordorUnitList = new ArrayList<>();

        //Создаем армию
        Army<MiddleEarthUnit> middleEarthUnitArmy = new Army<>(earthUnitList);
        Army<MordorUnit> mordorUnitArmy = new Army<>(mordorUnitList);


        //Определяем будет ли волшебник
        if ((int) (Math.random() + 0.5) == 1) {
            middleEarthUnitArmy.recruit(new Wizard("Gandalf"));
            countOfMiddleEarthUnit--;
        }

        //Заполняем армию добра
        for (int i = 0; i < countOfMiddleEarthUnit; i++)
            middleEarthUnitArmy.recruit(getMiddleEarthUnit(getMiddleEarthNameUnit()));
        System.out.println("Army of MiddleEarthUnit consists of: ");
        for (Unit u : middleEarthUnitArmy.getArmy()) {
            System.out.println(u.getClass().getSimpleName() + " " + u.getName() + " has power " + u.getPower());
        }

        //ну и конечно же, куда без зла)
        for (int i = 0; i < countOfMordorUnit; i++) {
            mordorUnitArmy.recruit(getMordorUnit(getMordorNameUnit()));
        }
        System.out.println("Army of MordorUnit consists of: ");
        for (Unit u : mordorUnitArmy.getArmy()) {
            System.out.println(u.getClass().getSimpleName() + " " + u.getName() + " has power " + u.getPower());
        }

        //Фаза 1
        System.out.println("Phase 1: ");
        while (middleEarthUnitArmy.getCavalry().size() != 0 && mordorUnitArmy.getCavalry().size() != 0) {
            if ((int) (Math.random() + 0.5) == 0) {
                MordorUnit mordorUnit = mordorUnitArmy.getCavalry().get((int) (Math.random() * mordorUnitArmy.getCavalry().size() - 0.5));
                MiddleEarthUnit middleUnit = middleEarthUnitArmy.getCavalry().get((int) (Math.random() * middleEarthUnitArmy.getCavalry().size() - 0.5));
                mordorUnit.strike(middleUnit);
                System.out.println(middleUnit.getClass().getSimpleName() + " " + middleUnit.getName() + " has power " + middleUnit.getPower() +
                        " strikes " + mordorUnit.getClass().getSimpleName() + " " + mordorUnit.getName() + " has power " + mordorUnit.getPower());
                if (!mordorUnit.isAlive()) {
                    mordorUnitArmy.release(mordorUnit);
                } else {
                    middleUnit.strike(mordorUnit);
                    System.out.println(mordorUnit.getClass().getSimpleName() + " " + mordorUnit.getName() + " has power " + mordorUnit.getPower() +
                            " strikes " + middleUnit.getClass().getSimpleName() + " " + middleUnit.getName() + " has power " + middleUnit.getPower());
                    if (!middleUnit.isAlive())
                        middleEarthUnitArmy.release(middleUnit);
                }
            } else {
                MordorUnit mordorUnit = mordorUnitArmy.getCavalry().get((int) (Math.random() * mordorUnitArmy.getCavalry().size() - 0.5));
                MiddleEarthUnit middleUnit = middleEarthUnitArmy.getCavalry().get((int) (Math.random() * middleEarthUnitArmy.getCavalry().size() - 0.5));
                middleUnit.strike(mordorUnit);
                System.out.println(mordorUnit.getClass().getSimpleName() + " " + mordorUnit.getName() + " has power " + mordorUnit.getPower() +
                        " strikes " + middleUnit.getClass().getSimpleName() + " " + middleUnit.getName() + " has power " + middleUnit.getPower());
                if (!middleUnit.isAlive()) middleEarthUnitArmy.release(middleUnit);
                else {
                    mordorUnit.strike(middleUnit);
                    System.out.println(middleUnit.getClass().getSimpleName() + " " + middleUnit.getName() + " has power " + middleUnit.getPower() +
                            " strikes " + mordorUnit.getClass().getSimpleName() + " " + mordorUnit.getName() + " has power " + mordorUnit.getPower());
                    if (!mordorUnit.isAlive()) {
                        mordorUnitArmy.release(mordorUnit);
                    }
                }
            }
        }
        if (middleEarthUnitArmy.getCavalry().size() != 0) {
            System.out.println("Army of MiddleEarthUnit has won the 1 phase. The winners list: ");
            for (Unit u : middleEarthUnitArmy.getCavalry()) {
                System.out.println(u.getClass().getSimpleName() + " " + u.getName() + " has power " + u.getPower());
            }
            System.out.println("Survivors " + middleEarthUnitArmy.getCavalry().size() + " units");
        } else {
            System.out.println("Army of MordorUnit has won the 1 phase. The winners list: ");
            for (Unit u : mordorUnitArmy.getCavalry()) {
                System.out.println(u.getClass().getSimpleName() + " " + u.getName() + " has power " + u.getPower());
            }
            System.out.println("Survivors " + mordorUnitArmy.getCavalry().size() + " units");
        }

        //Фаза 2
        System.out.println("Phase 2: ");
        while (middleEarthUnitArmy.getInfantry().size() != 0 && mordorUnitArmy.getInfantry().size() != 0) {
            if ((int) (Math.random() + 0.5) == 0) {
                MordorUnit mordorUnit = mordorUnitArmy.getInfantry().get((int) (Math.random() * mordorUnitArmy.getInfantry().size() - 0.5));
                MiddleEarthUnit middleUnit = middleEarthUnitArmy.getInfantry().get((int) (Math.random() * middleEarthUnitArmy.getInfantry().size() - 0.5));
                mordorUnit.strike(middleUnit);
                System.out.println(middleUnit.getClass().getSimpleName() + " " + middleUnit.getName() + " has power " + middleUnit.getPower() +
                        " strikes " + mordorUnit.getClass().getSimpleName() + " " + mordorUnit.getName() + " has power " + mordorUnit.getPower());
                if (!mordorUnit.isAlive()) {
                    mordorUnitArmy.release(mordorUnit);
                } else {
                    middleUnit.strike(mordorUnit);
                    System.out.println(mordorUnit.getClass().getSimpleName() + " " + mordorUnit.getName() + " has power " + mordorUnit.getPower() +
                            " strikes " + middleUnit.getClass().getSimpleName() + " " + middleUnit.getName() + " has power " + middleUnit.getPower());
                    if (!middleUnit.isAlive())
                        middleEarthUnitArmy.release(middleUnit);
                }
            } else {
                MordorUnit mordorUnit = mordorUnitArmy.getInfantry().get((int) (Math.random() * mordorUnitArmy.getInfantry().size() - 0.5));
                MiddleEarthUnit middleUnit = middleEarthUnitArmy.getInfantry().get((int) (Math.random() * middleEarthUnitArmy.getInfantry().size() - 0.5));
                middleUnit.strike(mordorUnit);
                System.out.println(mordorUnit.getClass().getSimpleName() + " " + mordorUnit.getName() + " has power " + mordorUnit.getPower() +
                        " strikes " + middleUnit.getClass().getSimpleName() + " " + middleUnit.getName() + " has power " + middleUnit.getPower());
                if (!middleUnit.isAlive()) middleEarthUnitArmy.release(middleUnit);
                else {
                    mordorUnit.strike(middleUnit);
                    System.out.println(middleUnit.getClass().getSimpleName() + " " + middleUnit.getName() + " has power " + middleUnit.getPower() +
                            " strikes " + mordorUnit.getClass().getSimpleName() + " " + mordorUnit.getName() + " has power " + mordorUnit.getPower());
                    if (!mordorUnit.isAlive()) {
                        mordorUnitArmy.release(mordorUnit);
                    }
                }
            }
        }
        if (middleEarthUnitArmy.getInfantry().size() != 0) {
            System.out.println("Army of MiddleEarthUnit has won the 2 phase. The winners list: ");
            for (Unit u : middleEarthUnitArmy.getInfantry()) {
                System.out.println(u.getClass().getSimpleName() + " " + u.getName() + " has power " + u.getPower());
            }
            System.out.println("Survivors " + middleEarthUnitArmy.getInfantry().size() + " units");
        } else {
            System.out.println("Army of MordorUnit has won the 2 phase. The winners list: ");
            for (Unit u : mordorUnitArmy.getInfantry()) {
                System.out.println(u.getClass().getSimpleName() + " " + u.getName() + " has power " + u.getPower());
            }
            System.out.println("Survivors " + mordorUnitArmy.getInfantry().size() + " units");
        }

        //Фаза 3
        System.out.println((middleEarthUnitArmy.getArmy().size() != 0 && mordorUnitArmy.getArmy().size() != 0) ? "Phase 3: " : "");
        while (middleEarthUnitArmy.getArmy().size() != 0 && mordorUnitArmy.getArmy().size() != 0) {
            if ((int) (Math.random() + 0.5) == 0) {
                MordorUnit mordorUnit = mordorUnitArmy.getArmy().get((int) (Math.random() * mordorUnitArmy.getInfantry().size() - 0.5));
                MiddleEarthUnit middleUnit = middleEarthUnitArmy.getArmy().get((int) (Math.random() * middleEarthUnitArmy.getInfantry().size() - 0.5));
                mordorUnit.strike(middleUnit);
                System.out.println(middleUnit.getClass().getSimpleName() + " " + middleUnit.getName() + " has power " + middleUnit.getPower() +
                        " strikes " + mordorUnit.getClass().getSimpleName() + " " + mordorUnit.getName() + " has power " + mordorUnit.getPower());
                if (!mordorUnit.isAlive()) {
                    mordorUnitArmy.release(mordorUnit);
                } else {
                    middleUnit.strike(mordorUnit);
                    System.out.println(mordorUnit.getClass().getSimpleName() + " " + mordorUnit.getName() + " has power " + mordorUnit.getPower() +
                            " strikes " + middleUnit.getClass().getSimpleName() + " " + middleUnit.getName() + " has power " + middleUnit.getPower());
                    if (!middleUnit.isAlive())
                        middleEarthUnitArmy.release(middleUnit);
                }
            } else {
                MordorUnit mordorUnit = mordorUnitArmy.getArmy().get((int) (Math.random() * mordorUnitArmy.getInfantry().size() - 0.5));
                MiddleEarthUnit middleUnit = middleEarthUnitArmy.getArmy().get((int) (Math.random() * middleEarthUnitArmy.getInfantry().size() - 0.5));
                middleUnit.strike(mordorUnit);
                System.out.println(mordorUnit.getClass().getSimpleName() + " " + mordorUnit.getName() + " has power " + mordorUnit.getPower() +
                        " strikes " + middleUnit.getClass().getSimpleName() + " " + middleUnit.getName() + " has power " + middleUnit.getPower());
                if (!middleUnit.isAlive()) middleEarthUnitArmy.release(middleUnit);
                else {
                    mordorUnit.strike(middleUnit);
                    System.out.println(middleUnit.getClass().getSimpleName() + " " + middleUnit.getName() + " has power " + middleUnit.getPower() +
                            " strikes " + mordorUnit.getClass().getSimpleName() + " " + mordorUnit.getName() + " has power " + mordorUnit.getPower());
                    if (!mordorUnit.isAlive()) {
                        mordorUnitArmy.release(mordorUnit);
                    }
                }
            }
        }
        if (middleEarthUnitArmy.getArmy().size() != 0) {
            System.out.println("Army of MiddleEarthUnit has won the battle. The winners list: ");
            for (Unit u : middleEarthUnitArmy.getArmy()) {
                System.out.println(u.getClass().getSimpleName() + " " + u.getName() + " has power " + u.getPower());
            }
            System.out.println("Survivors " + middleEarthUnitArmy.getArmy().size() + " units");
        } else {
            System.out.println("Army of MordorUnit has won the battle. The winners list: ");
            for (Unit u : mordorUnitArmy.getArmy()) {
                System.out.println(u.getClass().getSimpleName() + " " + u.getName() + " has power " + u.getPower());
            }
            System.out.println("Survivors " + mordorUnitArmy.getArmy().size() + " units");
        }
    }


    //Методы создания юнитов
    private MiddleEarthUnit getMiddleEarthUnit(String name) {
        ///Пусть класс юнита будет определяться случайным числом от 0 до 4, так же и в следующем методе
        int randomNumberClass = (int) (Math.random() * 4 + 0.5);
        return switch (randomNumberClass) {
            case (0) -> new CavalryHuman(name);
            case (1) -> new Elf(name);
            case (2) -> new InfantryHuman(name);
            case (3) -> new Rohhirim(name);
            case (4) -> new WoodenElf(name);
            default -> new InfantryHuman("ERROR");
        };
    }

    private MordorUnit getMordorUnit(String name) {
        int randomNumberClass = (int) (Math.random() * 3 + 0.5);
        return switch (randomNumberClass) {
            case (0) -> new CavalryOrc(name);
            case (1) -> new Goblin(name);
            case (2) -> new InfantryOrc(name);
            case (3) -> new UrukHai(name);
            default -> new InfantryOrc("ERROR");
        };
    }


    //Случайные имена для юнита


    private static String getMiddleEarthNameUnit() {
        List<String> namesList = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader("src/com/intellekta/generics/middleearth/unit/unitNames/MiddleEarthNames.txt"))) {
            String line;
            while ((line = bf.readLine()) != null) {
                line = line.replaceAll("\\W", " ");
                String[] names = line.split(" ");
                for (String str : names) {
                    if (!(str.length() == 0)) namesList.add(str);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return namesList.get((int) (Math.random() * namesList.size()));
    }

    private static String getMordorNameUnit() {
        List<String> namesListMordor = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader("src/com/intellekta/generics/middleearth/unit/unitNames/MordorUnitsNames.txt"))) {
            String line;
            while ((line = bf.readLine()) != null) {
                line = line.replaceAll("\\W", " ");
                String[] names = line.split(" ");
                for (String str : names) {
                    if (!(str.length() == 0)) namesListMordor.add(str);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return namesListMordor.get((int) (Math.random() * namesListMordor.size()));
    }
}
