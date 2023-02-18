package rpgheroes.items;

import rpgheroes.hero.HeroAttribute;

public class Armor extends Item {
    public enum ArmorType {
        cloth, leather, mail, plate
    }

    //Armor type of the item (specified in the constructor)
    private final ArmorType armorType;
    //Attributes attached to the armor
    private final HeroAttribute armorAttribute;

    public ArmorType getArmorType() {
        return armorType;
    }

    public HeroAttribute getArmorAttribute() {
        return armorAttribute;
    }

    public Armor(String name, Slot slot, ArmorType armorType, HeroAttribute armorAttribute, int requiredLevel) {
        super(name, slot, requiredLevel);
        this.armorType = armorType;
        this.armorAttribute = armorAttribute;
    }
}
