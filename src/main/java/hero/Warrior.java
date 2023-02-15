package hero;

import attributes.HeroAttribute;
import items.Armor.ArmorType;
import items.Weapon.WeaponType;
import java.util.Set;

public class Warrior extends Hero{
    public Warrior() {
        super(
                Set.of(ArmorType.mail,ArmorType.plate),
                Set.of(WeaponType.axe, WeaponType.hammer,WeaponType.sword),
                new HeroAttribute(5, 2, 1),
                new HeroAttribute(3, 2, 1)
        );
    }
}
