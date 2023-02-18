package rpgheroes.hero;

import rpgheroes.items.Armor;
import rpgheroes.items.Item;
import rpgheroes.items.Weapon;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Hero {
    //Hero name
    private final String name;
    //Hero level - starts with level 1
    private int level = 1;
    //Contains all equipped items and empty item slots.
    private Map<Item.Slot, Item> equipment = new HashMap<>();
    //Contains all armor types that the hero can use
    private Set<Armor.ArmorType> validArmorTypes;
    private Set<Weapon.WeaponType> validWeaponTypes;
    //Base and accumulated level attributes
    private HeroAttribute levelAttributesTotal;
    //Attributes added to the total on each gained level
    private final HeroAttribute LEVEL_UP_ATTRIBUTES;
    //Hero class name
    private final String heroClass;

    public int getLevel() {
        return level;
    }

    public HeroAttribute getLevelAttributesTotal() {
        return levelAttributesTotal;
    }

    public String getHeroClass() {
        return heroClass;
    }

    /**
     * Returns the class-specific attributes that are added to the total attributes on each level.
     *
     * @return LEVEL_UP_ATTRIBUTES
     */
    public HeroAttribute getLEVEL_UP_ATTRIBUTES() {
        return LEVEL_UP_ATTRIBUTES;
    }

    /**
     * Returns a map containing all the items equipped by the hero.
     *
     * @return equipped items
     */
    public Map<Item.Slot, Item> getEquipment() {
        return equipment;
    }

    public String getName() {
        return name;
    }

    Hero(String name, String heroClass, Set<Armor.ArmorType> allowedArmorTypes, Set<Weapon.WeaponType> allowedWeaponTypes, HeroAttribute startAttributes, HeroAttribute levelAttributes) {
        this.name = name;
        this.heroClass = heroClass;
        this.validArmorTypes = allowedArmorTypes;
        this.validWeaponTypes = allowedWeaponTypes;
        this.levelAttributesTotal = startAttributes;
        this.LEVEL_UP_ATTRIBUTES = levelAttributes;

        for (Item.Slot s : Item.Slot.values()) {
            equipment.put(s, null);
        }
    }

    /**
     * Increases the hero level by one and adds level-up attributes to the total.
     */
    public void levelUp() {
        this.level++;
        this.levelAttributesTotal.addAttributes(LEVEL_UP_ATTRIBUTES);
    }

    /**
     * Equips a weapon if the level and type requirements are met.
     * Heroes can only use class-specific item types.
     *
     * @param weapon Weapon to be equipped
     * @throws InvalidWeaponException Level or type requirements are not met
     */
    public void equip(Weapon weapon) throws InvalidWeaponException {
        if (!validWeaponTypes.contains(weapon.getWeaponType()))
            throw new InvalidWeaponException("Not able to equip weapons of type " + weapon.getWeaponType());

        if (level < weapon.getRequiredLevel())
            throw new InvalidWeaponException("Unable to equip the weapon. Required level: " + weapon.getRequiredLevel() + " Hero level: " + level);

        equipment.put(weapon.getSlot(), weapon);
    }

    /**
     * Equips armor if the level and type requirements are met.
     * Heroes can only use class-specific item types.
     *
     * @param armor Armor to be equipped
     * @throws InvalidArmorException Level or type requirements are not met
     */
    public void equip(Armor armor) throws InvalidArmorException {
        if (!validArmorTypes.contains(armor.getArmorType()))
            throw new InvalidArmorException("Not able to equip armor of type " + armor.getArmorType());

        if (level < armor.getRequiredLevel())
            throw new InvalidArmorException("Unable to equip the armor. Required level: " + armor.getRequiredLevel() + " Hero level: " + level);

        equipment.put(armor.getSlot(), armor);
    }

    /**
     * Calculates the total attributes of the hero including base, accumulated level attributes and
     * attributes from the equipped items.
     *
     * @return Object containing the total attributes
     */
    public HeroAttribute totalAttributes() {
        HeroAttribute total = new HeroAttribute(
                levelAttributesTotal.getStrength(),
                levelAttributesTotal.getDexterity(),
                levelAttributesTotal.getIntelligence());

        for (Item item : equipment.values()) {
            if (item instanceof Armor) {
                total.addAttributes(((Armor) item).getArmorAttribute());
            }
        }
        return total;
    }

    /**
     * Calculates the hero damage based on total attributes of the hero and weapon damage.
     * Only the class-specific main attribute is applied to the damage calculation.
     *
     * @return calculated damage
     */
    public double damage() {
        HeroAttribute total = totalAttributes();
        int damagingAttribute;

        int levelDex = levelAttributesTotal.getDexterity();
        int levelInt = levelAttributesTotal.getIntelligence();
        int levelStr = levelAttributesTotal.getStrength();

        //Damaging (main) attribute is extracted from the highest level attribute.
        damagingAttribute = levelDex > levelInt ? total.getDexterity() : total.getIntelligence();
        damagingAttribute = levelStr > damagingAttribute ? total.getStrength() : damagingAttribute;

        int weaponDamage = 1; //weaponless damage
        Weapon w = (Weapon) equipment.get(Item.Slot.weapon);
        if (w != null)
            weaponDamage = w.getWeaponDamage();

        return weaponDamage * (1 + damagingAttribute / 100.0);
    }

    /**
     * Displays hero status including: name, class, level, total strength, total dexterity, total intelligence, damage.
     *
     * @return String presentation of the hero status
     */
    public String display() {
        HeroAttribute total = totalAttributes();
        StringBuilder str = new StringBuilder();
        str.append("Name: ").append(name).append("\r\n");
        str.append("Class: ").append(heroClass).append("\r\n");
        str.append("Level: ").append(level).append("\r\n");
        str.append("Total strength: ").append(total.getStrength()).append("\r\n");
        str.append("Total dexterity: ").append(total.getDexterity()).append("\r\n");
        str.append("Total intelligence: ").append(total.getIntelligence()).append("\r\n");
        str.append("Damage: ").append(damage()).append("\r\n");
        return str.toString();
    }
}
