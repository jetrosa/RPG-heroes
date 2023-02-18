package rpgheroes.hero;

/**
 * Exception raised by unsuccessfully trying to equip armor
 * (for example caused by level requirements or invalid type)
 */
public class InvalidArmorException extends Exception {
    public InvalidArmorException(String errorMessage) {
        super(errorMessage);
    }
}
