package rpgheroes.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rpgheroes.items.Armor;
import rpgheroes.items.Weapon;
import rpgheroes.items.Item;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {

    private final double delta = 0.01;
    private Hero hero;
    private final Armor.ArmorType validArmorType = Armor.ArmorType.plate;
    private final Armor.ArmorType invalidArmorType = Armor.ArmorType.cloth;
    private final Weapon.WeaponType validWeaponType = Weapon.WeaponType.sword;
    private final Weapon.WeaponType invalidWeaponType = Weapon.WeaponType.staff;
    private final HeroAttribute startAttributes = new HeroAttribute(5, 2, 1);
    private final HeroAttribute itemAttributes = new HeroAttribute(2, 2, 2);
    private final HeroAttribute itemAttributes2 = new HeroAttribute(3, 3, 3);
    private final String heroName = "Gandalf";
    private final int startLevel = 1;
    private final int weaponDamage = 2;
    private final Item.Slot bodyArmorSlot = Item.Slot.body;
    private final Item.Slot headArmorSlot = Item.Slot.head;
    private final Item.Slot weaponSlot = Item.Slot.weapon;

    @BeforeEach
    void setUp() {
        this.hero = new Warrior(heroName);
    }

    @Test
    void constructor_should_setCorrectLevel() {
        assertEquals(startLevel, hero.getLevel());
    }

    @Test
    void constructor_should_setCorrectName() {
        assertEquals(heroName, hero.getName());
    }

    @Test
    void constructor_should_setCorrectAttributes() {
        assertEquals(startAttributes, hero.getLevelAttributesTotal());
    }

    @Test
    void levelUp_should_increaseLevel() {
        hero.levelUp();
        assertEquals(startLevel + 1, hero.getLevel());
    }

    @Test
    void levelUp_should_increaseAttribute() {
        HeroAttribute levelAttr = hero.getLevelAttributesTotal();
        levelAttr = new HeroAttribute(levelAttr.getStrength(), levelAttr.getDexterity(), levelAttr.getIntelligence());
        HeroAttribute attr = hero.getLEVEL_UP_ATTRIBUTES();

        hero.levelUp();

        int expectedStr = levelAttr.getStrength() + attr.getStrength();
        int expectedDex = levelAttr.getDexterity() + attr.getDexterity();
        int expectedInt = levelAttr.getIntelligence() + attr.getIntelligence();
        HeroAttribute expectedAttr = new HeroAttribute(expectedStr, expectedDex, expectedInt);
        HeroAttribute levelAttrAfterLvl = hero.getLevelAttributesTotal();

        assertEquals(expectedAttr, levelAttrAfterLvl);
    }

    @Test
    void equip_changeArmorWhenValid() {
        int heroLevel = hero.getLevel();
        Armor newArmor = new Armor("Item", bodyArmorSlot, validArmorType, itemAttributes, heroLevel);

        Map<Item.Slot, Item> equipment = hero.getEquipment();
        Item itemBeforeEquip = equipment.get(bodyArmorSlot);

        try {
            hero.equip(newArmor);
        } catch (Exception ignored) {
        }
        Item armorAfterEquip = equipment.get(bodyArmorSlot);

        assertNotEquals(itemBeforeEquip, armorAfterEquip);
        assertEquals(newArmor, armorAfterEquip);
    }

    @Test
    void equip_armor_throws_InvalidArmorExceptionWhenInvalid() {
        int heroLevel = hero.getLevel();
        Armor newArmor = new Armor("Item", bodyArmorSlot, invalidArmorType, itemAttributes, heroLevel);

        assertThrows(
                InvalidArmorException.class,
                () -> hero.equip(newArmor),
                "Expected equip(armor) to throw, but it didn't"
        );
    }

    @Test
    void equip_armor_preventEquipWhenInvalidType() {
        int heroLevel = hero.getLevel();
        Armor newArmor = new Armor("Item", bodyArmorSlot, invalidArmorType, itemAttributes, heroLevel);
        Map<Item.Slot, Item> equipment = hero.getEquipment();
        Item itemBeforeEquip = equipment.get(bodyArmorSlot);

        try {
            hero.equip(newArmor);
        } catch (InvalidArmorException ignored) {
        }
        Item armorAfterEquip = equipment.get(bodyArmorSlot);

        assertEquals(itemBeforeEquip, armorAfterEquip);
    }

    @Test
    void equip_armor_throws_InvalidArmorExceptionWhenInvalidLevel() {
        int heroLevel = hero.getLevel();
        Armor newArmor = new Armor("Item", bodyArmorSlot, validArmorType, itemAttributes, heroLevel + 1);

        assertThrows(
                InvalidArmorException.class,
                () -> hero.equip(newArmor),
                "Expected equip(armor) to throw, but it didn't"
        );
    }

    @Test
    void equip_armor_preventEquipWhenInvalidLevel() {
        int heroLevel = hero.getLevel();
        Armor newArmor = new Armor("Item", bodyArmorSlot, validArmorType, itemAttributes, heroLevel + 1);
        Map<Item.Slot, Item> equipment = hero.getEquipment();
        Item itemBeforeEquip = equipment.get(bodyArmorSlot);

        try {
            hero.equip(newArmor);
        } catch (InvalidArmorException ignored) {
        }
        Item armorAfterEquip = equipment.get(bodyArmorSlot);

        assertEquals(itemBeforeEquip, armorAfterEquip);
    }

    @Test
    void equip_weapon_equipWeaponWhenValid() {
        int heroLevel = hero.getLevel();
        Weapon newWeapon = new Weapon("Weapon", validWeaponType, weaponDamage, heroLevel);
        Map<Item.Slot, Item> equipment = hero.getEquipment();

        try {
            hero.equip(newWeapon);
        } catch (Exception ignored) {
        }
        Item weaponAfterEquip = equipment.get(weaponSlot);

        assertEquals(newWeapon, weaponAfterEquip);
    }

    @Test
    void equip_weapon_throws_InvalidWeaponExceptionWhenInvalidType() {
        int heroLevel = hero.getLevel();
        Weapon newWeapon = new Weapon("Weapon", invalidWeaponType, weaponDamage, heroLevel);

        assertThrows(
                InvalidWeaponException.class,
                () -> hero.equip(newWeapon),
                "Expected equip(weapon) to throw, but it didn't"
        );
    }

    @Test
    void equip_weapon_preventsEquipWhenInvalidType() {
        int heroLevel = hero.getLevel();
        Weapon newWeapon = new Weapon("Weapon", invalidWeaponType, weaponDamage, heroLevel);
        Map<Item.Slot, Item> equipment = hero.getEquipment();
        Item itemBeforeEquip = equipment.get(weaponSlot);

        try {
            hero.equip(newWeapon);
        } catch (InvalidWeaponException ignored) {
        }
        Item weaponAfterEquip = equipment.get(weaponSlot);

        assertEquals(itemBeforeEquip, weaponAfterEquip);
    }

    @Test
    void equip_weapon_throws_InvalidWeaponExceptionWhenInvalidLevel() {
        int heroLevel = hero.getLevel();
        Weapon newWeapon = new Weapon("Weapon", validWeaponType, weaponDamage, heroLevel + 1);

        assertThrows(
                InvalidWeaponException.class,
                () -> hero.equip(newWeapon),
                "Expected equip(weapon) to throw, but it didn't"
        );
    }

    @Test
    void equip_weapon_preventEquipWhenInvalidLevel() {
        int heroLevel = hero.getLevel();
        Weapon newWeapon = new Weapon("Weapon", validWeaponType, weaponDamage, heroLevel + 1);
        Map<Item.Slot, Item> equipment = hero.getEquipment();
        Item itemBeforeEquip = equipment.get(weaponSlot);

        try {
            hero.equip(newWeapon);
        } catch (InvalidWeaponException ignored) {
        }
        Item weaponAfterEquip = equipment.get(weaponSlot);

        assertEquals(itemBeforeEquip, weaponAfterEquip);
    }

    @Test
    void totalAttributes_noArmor() {
        HeroAttribute levelAttr = hero.getLevelAttributesTotal();
        HeroAttribute totalAttr = hero.totalAttributes();

        assertEquals(levelAttr, totalAttr);
    }

    @Test
    void totalAttributes_equippedOneArmor() {
        int heroLevel = hero.getLevel();
        Armor newArmor = new Armor("Item", bodyArmorSlot, validArmorType, itemAttributes, heroLevel);
        try {
            hero.equip(newArmor);
        } catch (Exception ignored) {
        }
        HeroAttribute levelAttr = hero.getLevelAttributesTotal();
        HeroAttribute totalAttr = hero.totalAttributes();

        assertNotEquals(levelAttr, totalAttr);
        levelAttr.addAttributes(itemAttributes);
        assertEquals(levelAttr, totalAttr);
    }

    @Test
    void totalAttributes_equippedTwoArmor() {
        int heroLevel = hero.getLevel();
        Armor newArmor = new Armor("Item", bodyArmorSlot, validArmorType, itemAttributes, heroLevel);

        Armor newArmor2 = new Armor("Item", headArmorSlot, validArmorType, itemAttributes2, heroLevel);
        try {
            hero.equip(newArmor);
            hero.equip(newArmor2);
        } catch (Exception ignored) {
        }
        HeroAttribute levelAttr = hero.getLevelAttributesTotal();
        HeroAttribute totalAttr = hero.totalAttributes();

        levelAttr.addAttributes(itemAttributes);
        levelAttr.addAttributes(itemAttributes2);
        assertEquals(levelAttr, totalAttr);
    }

    @Test
    void totalAttributes_equippedReplaceArmor() {
        int heroLevel = hero.getLevel();
        Armor newArmor = new Armor("Item", bodyArmorSlot, validArmorType, itemAttributes, heroLevel);

        Armor newArmor2 = new Armor("Item", bodyArmorSlot, validArmorType, itemAttributes2, heroLevel);
        try {
            hero.equip(newArmor);
            hero.equip(newArmor2);
        } catch (Exception ignored) {
        }
        HeroAttribute levelAttr = hero.getLevelAttributesTotal();
        HeroAttribute totalAttr = hero.totalAttributes();

        levelAttr.addAttributes(itemAttributes2);
        assertEquals(levelAttr, totalAttr);
    }

    @Test
    void damage_weaponless() {
        int damagingAttribute = hero.getLevelAttributesTotal().getStrength();
        int weaponlessDamage = 1;

        double expectedDmg = weaponlessDamage * (1 + damagingAttribute / 100.0);
        double actualDmg = hero.damage();

        assertEquals(expectedDmg, actualDmg, delta);
    }

    @Test
    void damage_withWeapon() {
        int heroLevel = hero.getLevel();
        Weapon newWeapon = new Weapon("Weapon", validWeaponType, weaponDamage, heroLevel);

        try {
            hero.equip(newWeapon);
        } catch (Exception ignored) {
        }

        int damagingAttribute = hero.getLevelAttributesTotal().getStrength();
        double expectedDmg = weaponDamage * (1 + damagingAttribute / 100.0);
        double actualDmg = hero.damage();

        assertEquals(expectedDmg, actualDmg, delta);
    }

    @Test
    void damage_withWeaponReplaced() {
        int heroLevel = hero.getLevel();
        Weapon newWeapon = new Weapon("Weapon", validWeaponType, weaponDamage, heroLevel);
        int weaponDamage2 = weaponDamage + 1;
        Weapon newWeapon2 = new Weapon("Weapon2", validWeaponType, weaponDamage2, heroLevel);

        try {
            hero.equip(newWeapon);
            hero.equip(newWeapon2);
        } catch (Exception ignored) {
        }

        int damagingAttribute = hero.getLevelAttributesTotal().getStrength();
        double expectedDmg = weaponDamage2 * (1 + damagingAttribute / 100.0);
        double actualDmg = hero.damage();
        assertEquals(expectedDmg, actualDmg, delta);
    }

    @Test
    void damage_withWeaponAndArmor() {
        int heroLevel = hero.getLevel();
        Weapon newWeapon = new Weapon("Weapon", validWeaponType, weaponDamage, heroLevel);
        Armor newArmor = new Armor("Item", bodyArmorSlot, validArmorType, itemAttributes, heroLevel);

        try {
            hero.equip(newWeapon);
            hero.equip(newArmor);
        } catch (Exception ignored) {
        }

        int damagingAttribute = hero.getLevelAttributesTotal().getStrength();
        damagingAttribute += itemAttributes.getIntelligence();
        double expectedDmg = weaponDamage * (1 + damagingAttribute / 100.0);
        double actualDmg = hero.damage();
        assertEquals(expectedDmg, actualDmg, delta);
    }

    @Test
    void display() {
        HeroAttribute total = hero.totalAttributes();

        String expectedStr = "Name: " + heroName + "\r\n" +
                "Class: " + hero.getHeroClass() + "\r\n" +
                "Level: " + startLevel + "\r\n" +
                "Total strength: " + total.getStrength() + "\r\n" +
                "Total dexterity: " + total.getDexterity() + "\r\n" +
                "Total intelligence: " + total.getIntelligence() + "\r\n" +
                "Damage: " + hero.damage() + "\r\n";
        String actualStr = hero.display();
        assertEquals(expectedStr, actualStr);
    }
}