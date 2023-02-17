package rpgheroes.items;

public class Weapon extends Item{

    public enum WeaponType{
        axe,bow,dagger,hammer,staff,sword,wand
    }
    WeaponType weaponType;
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
