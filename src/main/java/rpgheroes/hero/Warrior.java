package rpgheroes.hero;

import rpgheroes.items.Armor.ArmorType;
import rpgheroes.items.Weapon.WeaponType;

import java.util.Set;

public class Warrior extends Hero {
    /**
     * Subclass of Hero. Includes class name, valid armor and weapon types, start and level attributes.
     *
     * @param name Name of the hero
     */
    public Warrior(String name) {
        super(
                name,
                "warrior",
                Set.of(ArmorType.mail, ArmorType.plate),
                Set.of(WeaponType.axe, WeaponType.hammer, WeaponType.sword),
                new HeroAttribute(5, 2, 1),
                new HeroAttribute(3, 2, 1)
        );
    }
}
