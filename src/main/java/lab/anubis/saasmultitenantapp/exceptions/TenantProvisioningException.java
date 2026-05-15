package lab.anubis.saasmultitenantapp.exceptions;

public class TenantProvisioningException extends BusinessException {
    public TenantProvisioningException(final String message) {
        super(message);
    }
}