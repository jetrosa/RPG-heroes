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

    /**
     * Adds the attribute values in the parameter object to the attribute values contained in this object.
     *
     * @param addedAttribute Object containing the values to be added
     */
    public void addAttributes(@NotNull HeroAttribute addedAttribute) {
        this.strength += addedAttribute.strength;
        this.dexterity += addedAttribute.dexterity;
        this.intelligence += addedAttribute.intelligence;
    }

    /**
     * Compares the attribute values (strength, dexterity, intelligence) contained in the class.
     *
     * @param o Object to compare
     * @return boolean match
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof HeroAttribute oh)) {
            return false;
        }

        return strength == oh.strength
                && dexterity == oh.dexterity
                && intelligence == oh.intelligence;
    }
}
