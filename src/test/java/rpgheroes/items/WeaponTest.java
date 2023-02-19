package rpgheroes.items;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    @Test
    void constructor_afterInit_correctValuesGetters() {
        String name = "itemName";
        Item.Slot defaultSlot = Item.Slot.weapon;
        Weapon.WeaponType weaponType = Weapon.WeaponType.sword;
        int weaponDamage = 2;
        int defaultRequiredLevel = 2;
        Weapon weapon = new Weapon(name, weaponType, weaponDamage, defaultRequiredLevel);

        assertEquals(defaultRequiredLevel, weapon.getRequiredLevel());
        assertEquals(name, weapon.getName());
        assertEquals(defaultSlot, weapon.getSlot());
        assertEquals(weaponType, weapon.getWeaponType());
        assertEquals(weaponDamage, weapon.getWeaponDamage());
    }
}