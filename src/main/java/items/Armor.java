package items;

import attributes.HeroAttribute;

public class Armor extends Item {
    public enum ArmorType{
        cloth,leather,mail,plate
    }
    private ArmorType armorType;

    public ArmorType getArmorType() {
        return armorType;
    }

    private HeroAttribute armorAttribute;

    public HeroAttribute getArmorAttribute() {
        return armorAttribute;
    }

    public Armor(Slot slot, ArmorType armorType, HeroAttribute armorAttribute, int requiredLevel) {
        super(slot, requiredLevel);
        this.armorType = armorType;
        this.armorAttribute = armorAttribute;
    }
}
