package lab.anubis.saasmultitenantapp.auth.service;

import lab.anubis.saasmultitenantapp.auth.requests.LoginRequest;
import lab.anubis.saasmultitenantapp.auth.responses.LoginResponse;

public interface AuthenticationService {
    LoginResponse login(final LoginRequest request);
}
