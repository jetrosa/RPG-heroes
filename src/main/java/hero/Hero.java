package hero;

import attributes.HeroAttribute;
import items.Armor;
import items.Item;
import items.Weapon;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Hero {
    private String name;
    private int level = 1;
    private int weaponlessDamage = 1;
    private Map<Item.Slot, Item> equipment = new HashMap<>();
    private Set<Armor.ArmorType> validArmorTypes;
    private Set<Weapon.WeaponType> validWeaponTypes;
    private HeroAttribute levelAttributesTotal;
    private final HeroAttribute LEVEL_UP_ATTRIBUTES;


    Hero(String name, Set<Armor.ArmorType> allowedArmorTypes, Set<Weapon.WeaponType> allowedWeaponTypes, HeroAttribute startAttributes, HeroAttribute levelAttributes){
        this.name = name;
        this.validArmorTypes =allowedArmorTypes;
        this.validWeaponTypes =allowedWeaponTypes;
        this.levelAttributesTotal = startAttributes;
        this.LEVEL_UP_ATTRIBUTES = levelAttributes;

//        equipment.put(Item.Slot.weapon,null);
//        equipment.put(Item.Slot.body,null);
//        equipment.put(Item.Slot.head,null);
//        equipment.put(Item.Slot.legs,null);

        for (Item.Slot s : Item.Slot.values()) {
            equipment.put(s, null);
        }
    }

    public void levelUp(){
        this.level++;
        this.levelAttributesTotal.addAttributes(LEVEL_UP_ATTRIBUTES);
    }

    public void equip(Weapon weapon){
        equipment.put(weapon.getSlot(),weapon);
    }

    public void equip(Armor armor){
        equipment.put(armor.getSlot(),armor);
    }

    public int damage(){
        HeroAttribute total = totalAttributes();
        int damagingAttribute;

        int levelDex = levelAttributesTotal.getDexterity();
        int levelInt = levelAttributesTotal.getIntelligence();
        int levelStr = levelAttributesTotal.getStrength();
        damagingAttribute = levelDex>levelInt?total.getDexterity():total.getIntelligence();
        damagingAttribute = levelStr>damagingAttribute?total.getStrength():damagingAttribute;

        int weaponDamage = weaponlessDamage;
        Weapon w = (Weapon)equipment.get(Item.Slot.weapon);
        if(w!=null)
            weaponDamage = w.getWeaponDamage();

        return weaponDamage * (1 + damagingAttribute/100);
    }

    public HeroAttribute totalAttributes() {
        HeroAttribute total = new HeroAttribute(
                levelAttributesTotal.getStrength(),
                levelAttributesTotal.getDexterity(),
                levelAttributesTotal.getIntelligence());

        for (Item item : equipment.values()) {
            if (item instanceof Armor) {
                total.addAttributes(((Armor) item).getArmorAttribute());
            }
        }
        return total;
    }

    public void display(){

    }
}
