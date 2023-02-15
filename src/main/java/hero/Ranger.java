package hero;

import attributes.HeroAttribute;
import items.Armor.ArmorType;
import items.Weapon.WeaponType;
import java.util.Set;

public class Ranger extends Hero{
    public Ranger() {
        super(
                Set.of(ArmorType.leather,ArmorType.mail),
                Set.of(WeaponType.bow),
                new HeroAttribute(1, 7, 1),
                new HeroAttribute(1, 5, 1)
        );
    }
}
