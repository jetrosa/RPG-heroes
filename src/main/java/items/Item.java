package items;

import attributes.HeroAttribute;

public abstract class Item {
    public enum Slot{ weapon, head, body, legs }
    private String name;
    private int requiredLevel;
    private final Slot SLOT;

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public String getName() {
        return name;
    }

    public Slot getSlot() {
        return SLOT;
    }

    public Item(Slot slot, int requiredLevel){
        this.SLOT=slot;
        this.requiredLevel = requiredLevel;
    }
}
