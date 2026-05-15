package lab.anubis.saasmultitenantapp.exceptions;

public class DuplicateResourceException extends BusinessException{
    public DuplicateResourceException(String message) {
        super(message);
    }
}
