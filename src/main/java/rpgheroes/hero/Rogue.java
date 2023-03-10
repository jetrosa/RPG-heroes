package rpgheroes.hero;

import rpgheroes.items.Armor.ArmorType;
import rpgheroes.items.Weapon.WeaponType;

import java.util.Set;

public class Rogue extends Hero {
    /**
     * Subclass of Hero. Includes class name, valid armor and weapon types, start and level attributes.
     *
     * @param name Name of the hero
     */
    public Rogue(String name) {
        super(
                name,
                "rogue",
                Set.of(ArmorType.leather, ArmorType.mail),
                Set.of(WeaponType.dagger, WeaponType.sword),
                new HeroAttribute(2, 6, 1),
                new HeroAttribute(1, 4, 1)
        );
    }
}
