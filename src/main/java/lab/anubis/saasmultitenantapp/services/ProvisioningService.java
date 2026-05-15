package lab.anubis.saasmultitenantapp.services;

import lab.anubis.saasmultitenantapp.entities.Tenant;

public interface ProvisioningService {
    void provisionTenant(final Tenant tenant);
}
