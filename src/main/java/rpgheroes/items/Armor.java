package rpgheroes.items;

import rpgheroes.hero.HeroAttribute;

public class Armor extends Item {
    public enum ArmorType {
        cloth, leather, mail, plate
    }

    private final ArmorType armorType;

    public ArmorType getArmorType() {
        return armorType;
    }

    private final HeroAttribute armorAttribute;

    public HeroAttribute getArmorAttribute() {
        return armorAttribute;
    }

    public Armor(String name, Slot slot, ArmorType armorType, HeroAttribute armorAttribute, int requiredLevel) {
        super(name, slot, requiredLevel);
        this.armorType = armorType;
        this.armorAttribute = armorAttribute;
    }
}
