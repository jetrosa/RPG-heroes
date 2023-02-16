package hero;

public class InvalidWeaponException extends Exception{
    public InvalidWeaponException(String errorMessage){
        super(errorMessage);
    }
}
