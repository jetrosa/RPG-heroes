package rpgheroes.items;

import org.junit.jupiter.api.Test;
import rpgheroes.hero.HeroAttribute;

import static org.junit.jupiter.api.Assertions.*;

class ArmorTest {

    @Test
    void constructor_correctValuesGetters() {
        String name = "itemName";
        Item.Slot slot = Item.Slot.body;
        Armor.ArmorType armorType = Armor.ArmorType.cloth;
        int defaultRequiredLevel = 2;
        int defaultStrength = 2;
        int defaultDexterity = 2;
        int defaultIntelligence = 2;

        HeroAttribute attributes = new HeroAttribute(defaultStrength,defaultDexterity,defaultIntelligence);
        Armor armor = new Armor(name,slot, armorType, attributes, defaultRequiredLevel);

        assertEquals(name, armor.getName());
        assertEquals(defaultRequiredLevel, armor.getRequiredLevel());
        assertEquals(slot, armor.getSlot());
        assertEquals(armorType, armor.getArmorType());
        assertEquals(attributes, armor.getArmorAttribute());
    }
}