package lab.anubis.saasmultitenantapp.exceptions;

public class UnauthorizedException extends BusinessException{
    public UnauthorizedException(String message) {
        super(message);
    }
}
