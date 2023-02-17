package rpgheroes.items;

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

    public Item(String name, Slot slot, int requiredLevel){
        this.name = name;
        this.SLOT=slot;
        this.requiredLevel = requiredLevel;
    }
}
