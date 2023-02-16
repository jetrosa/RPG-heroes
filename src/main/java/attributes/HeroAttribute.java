package attributes;

import org.jetbrains.annotations.NotNull;

public class HeroAttribute {
    private int strength;
    private int dexterity;
    private int intelligence;

    public HeroAttribute(int strength, int dexterity, int intelligence) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

    public int getStrength() {
        return strength;
    }
    public int getDexterity() {
        return dexterity;
    }
    public int getIntelligence() {
        return intelligence;
    }

    public void addAttributes(@NotNull HeroAttribute addedAttribute) {
        this.strength += addedAttribute.strength;
        this.dexterity += addedAttribute.dexterity;
        this.intelligence += addedAttribute.intelligence;
    }
}
