package hero;

import attributes.HeroAttribute;
import items.Armor.ArmorType;
import items.Weapon.WeaponType;
import java.util.Set;

public class Rogue extends Hero{
    public Rogue() {
        super(
                Set.of(ArmorType.leather,ArmorType.mail),
                Set.of(WeaponType.dagger, WeaponType.sword),
                new HeroAttribute(2, 6, 1),
                new HeroAttribute(1, 4, 1)
        );
    }
}
