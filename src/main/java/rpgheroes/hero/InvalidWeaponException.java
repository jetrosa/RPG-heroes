package rpgheroes.hero;

/**
 * Exception raised by unsuccessfully trying to equip a weapon
 * (for example caused by level requirements or invalid type)
 */
public class InvalidWeaponException extends Exception {
    public InvalidWeaponException(String errorMessage) {
        super(errorMessage);
    }
}
