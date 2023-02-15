package items;

public class Weapon extends Item{

    public enum WeaponType{
        axe,bow,dagger,hammer,staff,sword,wand
    }
    WeaponType weaponType;
    private int weaponDamage;

    public Weapon(WeaponType weaponType, int weaponDamage) {
        this.weaponType = weaponType;
        this.weaponDamage = weaponDamage;
    }
}
