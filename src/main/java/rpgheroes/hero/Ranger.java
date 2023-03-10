package rpgheroes.hero;

import rpgheroes.items.Armor.ArmorType;
import rpgheroes.items.Weapon.WeaponType;

import java.util.Set;

public class Ranger extends Hero {
    /**
     * Subclass of Hero. Includes class name, valid armor and weapon types, start and level attributes.
     *
     * @param name Name of the hero
     */
    public Ranger(String name) {
        super(
                name,
                "ranger",
                Set.of(ArmorType.leather, ArmorType.mail),
                Set.of(WeaponType.bow),
                new HeroAttribute(1, 7, 1),
                new HeroAttribute(1, 5, 1)
        );
    }
}
