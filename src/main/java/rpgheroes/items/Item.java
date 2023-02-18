package rpgheroes.items;

public abstract class Item {
    /**
     * All available item slots.
     */
    public enum Slot {weapon, head, body, legs}

    private final String name;
    //Level requirement of the item
    private final int requiredLevel;
    //Slot where the item can be equipped
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

    public Item(String name, Slot slot, int requiredLevel) {
        this.name = name;
        this.SLOT = slot;
        this.requiredLevel = requiredLevel;
    }
}
