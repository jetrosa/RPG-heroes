package hero;

import attributes.HeroAttribute;
import items.Armor;
import items.Weapon;

import java.util.Set;

public abstract class Hero {
    private String name;
    private int level;
    private Set<Armor.ArmorType> allowedArmorTypes;
    private Set<Weapon.WeaponType> allowedWeaponTypes;
    private HeroAttribute startAttribute;
    private HeroAttribute levelAttribute;

    Hero(Set<Armor.ArmorType> allowedArmorTypes, Set<Weapon.WeaponType> allowedWeaponTypes, HeroAttribute startAttribute, HeroAttribute levelAttribute){
        this.allowedArmorTypes=allowedArmorTypes;
        this.allowedWeaponTypes=allowedWeaponTypes;
        this.startAttribute = startAttribute;
        this.levelAttribute = levelAttribute;
    }
}
