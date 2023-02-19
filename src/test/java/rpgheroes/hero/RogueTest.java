package rpgheroes.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rpgheroes.items.Armor;
import rpgheroes.items.Weapon;
import rpgheroes.items.Item;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RogueTest {

    //Hero-based variables (different for each Hero subclass)
    private final Armor.ArmorType validArmorType = Armor.ArmorType.leather;
    private final Armor.ArmorType invalidArmorType = Armor.ArmorType.plate;
    private final Weapon.WeaponType validWeaponType = Weapon.WeaponType.sword;
    private final Weapon.WeaponType invalidWeaponType = Weapon.WeaponType.staff;
    private final HeroAttribute startAttributes = new HeroAttribute(2, 6, 1);

    //Common test variables
    private final double delta = 0.001;
    private Hero hero;
    private final HeroAttribute itemAttributes = new HeroAttribute(2, 2, 2);
    private final HeroAttribute itemAttributes2 = new HeroAttribute(3, 3, 3);
    private final String heroName = "TestName";
    private final int startLevel = 1;
    private final int weaponlessDamage = 1;
    private final int weaponDamage = 2;
    private final Item.Slot bodyArmorSlot = Item.Slot.body;
    private final Item.Slot headArmorSlot = Item.Slot.head;
    private final Item.Slot weaponSlot = Item.Slot.weapon;

    @BeforeEach
    void setUp() {
        this.hero = new Rogue(heroName);
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
        Armor newArmor = new Armor("Item", bodyArmorSlot, invalidArmorType, itemAttributes, startLevel);

        assertThrows(
                InvalidArmorException.class,
                () -> hero.equip(newArmor),
                "Expected equip(armor) to throw, but it didn't"
        );
    }

    @Test
    void equip_armor_preventEquipWhenInvalidType() {
        Armor newArmor = new Armor("Item", bodyArmorSlot, invalidArmorType, itemAttributes, startLevel);
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
        Armor newArmor = new Armor("Item", bodyArmorSlot, validArmorType, itemAttributes, startLevel + 1);

        assertThrows(
                InvalidArmorException.class,
                () -> hero.equip(newArmor),
                "Expected equip(armor) to throw, but it didn't"
        );
    }

    @Test
    void equip_armor_preventEquipWhenInvalidLevel() {
        Armor newArmor = new Armor("Item", bodyArmorSlot, validArmorType, itemAttributes, startLevel + 1);
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
        Weapon newWeapon = new Weapon("Weapon", validWeaponType, weaponDamage, startLevel);
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
        Weapon newWeapon = new Weapon("Weapon", invalidWeaponType, weaponDamage, startLevel);

        assertThrows(
                InvalidWeaponException.class,
                () -> hero.equip(newWeapon),
                "Expected equip(weapon) to throw, but it didn't"
        );
    }

    @Test
    void equip_weapon_preventsEquipWhenInvalidType() {
        Weapon newWeapon = new Weapon("Weapon", invalidWeaponType, weaponDamage, startLevel);
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
        Weapon newWeapon = new Weapon("Weapon", validWeaponType, weaponDamage, startLevel + 1);

        assertThrows(
                InvalidWeaponException.class,
                () -> hero.equip(newWeapon),
                "Expected equip(weapon) to throw, but it didn't"
        );
    }

    @Test
    void equip_weapon_preventEquipWhenInvalidLevel() {
        Weapon newWeapon = new Weapon("Weapon", validWeaponType, weaponDamage, startLevel + 1);
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
    void totalAttributes_shouldCalculateCorrectly_noArmor() {
        HeroAttribute totalAttr = hero.totalAttributes();

        assertEquals(startAttributes, totalAttr);
    }

    @Test
    void totalAttributes_shouldCalculateCorrectly_equippedOneArmor() {
        Armor newArmor = new Armor("Item", bodyArmorSlot, validArmorType, itemAttributes, startLevel);

        try {
            hero.equip(newArmor);
        } catch (Exception ignored) {
        }
        HeroAttribute totalAttr = hero.totalAttributes();

        int expectedStr = startAttributes.getStrength() + itemAttributes.getStrength();
        int expectedDex = startAttributes.getDexterity() + itemAttributes.getDexterity();
        int expectedInt = startAttributes.getIntelligence() + itemAttributes.getIntelligence();
        HeroAttribute expectedAttr = new HeroAttribute(expectedStr, expectedDex, expectedInt);

        assertEquals(expectedAttr, totalAttr);
    }

    @Test
    void totalAttributes_shouldCalculateCorrectly_equippedTwoArmor() {
        Armor newArmor = new Armor("Item", bodyArmorSlot, validArmorType, itemAttributes, startLevel);
        Armor newArmor2 = new Armor("Item", headArmorSlot, validArmorType, itemAttributes2, startLevel);

        try {
            hero.equip(newArmor);
            hero.equip(newArmor2);
        } catch (Exception ignored) {
        }
        HeroAttribute totalAttr = hero.totalAttributes();

        int expectedStr = startAttributes.getStrength() + itemAttributes.getStrength() + itemAttributes2.getStrength();
        int expectedDex = startAttributes.getDexterity() + itemAttributes.getDexterity() + itemAttributes2.getStrength();
        int expectedInt = startAttributes.getIntelligence() + itemAttributes.getIntelligence() + itemAttributes2.getStrength();
        HeroAttribute expectedAttr = new HeroAttribute(expectedStr, expectedDex, expectedInt);

        assertEquals(expectedAttr, totalAttr);
    }

    @Test
    void totalAttributes_shouldCalculateCorrectly_equippedReplaceArmor() {
        Armor newArmor = new Armor("Item", bodyArmorSlot, validArmorType, itemAttributes, startLevel);
        Armor newArmor2 = new Armor("Item", bodyArmorSlot, validArmorType, itemAttributes2, startLevel);

        try {
            hero.equip(newArmor);
            hero.equip(newArmor2);
        } catch (Exception ignored) {
        }
        HeroAttribute totalAttr = hero.totalAttributes();

        int expectedStr = startAttributes.getStrength() + itemAttributes2.getStrength();
        int expectedDex = startAttributes.getDexterity() + itemAttributes2.getDexterity();
        int expectedInt = startAttributes.getIntelligence() + itemAttributes2.getIntelligence();
        HeroAttribute expectedAttr = new HeroAttribute(expectedStr, expectedDex, expectedInt);

        assertEquals(expectedAttr, totalAttr);
    }

    @Test
    void damage_shouldCalculateCorrectly_weaponless() {
        //using the class-based damaging attribute
        int damagingAttribute = startAttributes.getDexterity();

        double expectedDmg = calculateDamage(weaponlessDamage, damagingAttribute);
        double actualDmg = hero.damage();

        assertEquals(expectedDmg, actualDmg, delta);
    }

    @Test
    void damage_shouldCalculateCorrectly_withWeapon() {
        //using the class-based damaging attribute
        int damagingAttribute = startAttributes.getDexterity();
        Weapon newWeapon = new Weapon("Weapon", validWeaponType, weaponDamage, startLevel);

        try {
            hero.equip(newWeapon);
        } catch (Exception ignored) {
        }
        double actualDmg = hero.damage();
        double expectedDmg = calculateDamage(weaponDamage, damagingAttribute);

        assertEquals(expectedDmg, actualDmg, delta);
    }

    @Test
    void damage_shouldCalculateCorrectly_withWeaponReplaced() {
        //using the class-based damaging attribute
        int damagingAttribute = startAttributes.getDexterity();
        Weapon newWeapon = new Weapon("Weapon", validWeaponType, weaponDamage, startLevel);
        int weaponDamage2 = weaponDamage + 1;
        Weapon newWeapon2 = new Weapon("Weapon2", validWeaponType, weaponDamage2, startLevel);

        try {
            hero.equip(newWeapon);
            hero.equip(newWeapon2);
        } catch (Exception ignored) {
        }
        double actualDmg = hero.damage();
        double expectedDmg = calculateDamage(weaponDamage2, damagingAttribute);

        assertEquals(expectedDmg, actualDmg, delta);
    }

    @Test
    void damage_shouldCalculateCorrectly_withWeaponAndArmor() {
        //using the class-based damaging attribute
        int damagingAttribute = startAttributes.getDexterity();
        damagingAttribute += itemAttributes.getDexterity();
        Weapon newWeapon = new Weapon("Weapon", validWeaponType, weaponDamage, startLevel);
        Armor newArmor = new Armor("Item", bodyArmorSlot, validArmorType, itemAttributes, startLevel);

        try {
            hero.equip(newWeapon);
            hero.equip(newArmor);
        } catch (Exception ignored) {
        }
        double actualDmg = hero.damage();
        double expectedDmg = calculateDamage(weaponDamage, damagingAttribute);

        assertEquals(expectedDmg, actualDmg, delta);
    }

    @Test
    void display_should_returnExpectedString() {
        //using the class-based damaging attribute
        double damage = calculateDamage(weaponlessDamage, startAttributes.getDexterity());
        String heroClass = "rogue";

        String expectedStr = "Name: " + heroName + "\r\n" +
                "Class: " + heroClass + "\r\n" +
                "Level: " + startLevel + "\r\n" +
                "Total strength: " + startAttributes.getStrength() + "\r\n" +
                "Total dexterity: " + startAttributes.getDexterity() + "\r\n" +
                "Total intelligence: " + startAttributes.getIntelligence() + "\r\n" +
                "Damage: " + damage + "\r\n";

        String actualStr = hero.display();

        assertEquals(expectedStr, actualStr);
    }

    double calculateDamage(int weaponDamage, int damagingAttribute) {
        return weaponDamage * (1 + damagingAttribute / 100.0);
    }
}