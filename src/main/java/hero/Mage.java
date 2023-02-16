package hero;

import attributes.HeroAttribute;
import items.Armor.ArmorType;
import items.Weapon.WeaponType;
import java.util.Set;

public class Mage extends Hero {
    public Mage(String name) {
        super(
                name,
                Set.of(ArmorType.cloth),
                Set.of(WeaponType.staff, WeaponType.wand),
                new HeroAttribute(1, 1, 8),
                new HeroAttribute(1, 1, 5)
        );
    }
}
