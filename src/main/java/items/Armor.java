package items;

import attributes.HeroAttribute;

public class Armor extends Item {
    public enum ArmorType{
        cloth,leather,mail,plate
    }
    ArmorType armorType;

    HeroAttribute armorAttribute;

    public Armor(ArmorType armorType, HeroAttribute armorAttribute) {
        this.armorType = armorType;
        this.armorAttribute = armorAttribute;
    }
}
