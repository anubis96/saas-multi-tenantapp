package lab.anubis.saasmultitenantapp.exceptions;

public class InvalidRequestException extends BusinessException{
    public InvalidRequestException(String message) {
        super(message);
    }
}
