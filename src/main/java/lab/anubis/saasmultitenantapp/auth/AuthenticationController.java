package lab.anubis.saasmultitenantapp.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lab.anubis.saasmultitenantapp.auth.requests.LoginRequest;
import lab.anubis.saasmultitenantapp.auth.responses.LoginResponse;
import lab.anubis.saasmultitenantapp.auth.service.AuthenticationService;
import lab.anubis.saasmultitenantapp.requests.RegisterTenantRequest;
import lab.anubis.saasmultitenantapp.services.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final TenantService tenantService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid
            @RequestBody
            final LoginRequest request
    ) {
        String encoded = passwordEncoder.encode("123456");

        System.out.println("encoded "+encoded);
        final LoginResponse response = this.authenticationService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @Valid
            @RequestBody
            final RegisterTenantRequest request
    ) {
        this.tenantService.registerTenant(request);
        return ResponseEntity.ok().build();
    }

}
