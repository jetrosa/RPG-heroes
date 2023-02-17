package rpgheroes.hero;

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

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        if (!(o instanceof HeroAttribute)) {
            return false;
        }

        HeroAttribute oh = (HeroAttribute) o;

        // Compare the data members and return accordingly
        return strength == oh.strength
                && dexterity == oh.dexterity
                && intelligence == oh.intelligence;
    }
}