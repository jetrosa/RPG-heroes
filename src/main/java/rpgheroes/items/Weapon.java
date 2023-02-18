package rpgheroes.items;

public class Weapon extends Item {

    /**
     * All existing weapon types.
     */
    public enum WeaponType {
        axe, bow, dagger, hammer, staff, sword, wand
    }

    //Weapon type of the item (specified in the constructor)
    private final WeaponType weaponType;
    private int weaponDamage;

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public Weapon(String name, WeaponType weaponType, int weaponDamage, int requiredLevel) {
        super(name, Slot.weapon, requiredLevel);
        this.weaponType = weaponType;
        this.weaponDamage = weaponDamage;
    }
}
