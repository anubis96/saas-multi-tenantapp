package lab.anubis.saasmultitenantapp.config;

import lab.anubis.saasmultitenantapp.entities.User;
import lab.anubis.saasmultitenantapp.entities.UserRole;
import lab.anubis.saasmultitenantapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findAll().isEmpty()) {
            log.info("Création de l'administrateur par défaut...");
            User admin = User.builder()
                    .username("anubis.pf")
                    .email("admin@anubis.lab")
                    .password(passwordEncoder.encode("Admin123!"))
                    .firstName("Admin")
                    .lastName("Platform")
                    .role(UserRole.ROLE_PLATFORM_ADMIN)
                    .enabled(true)
                    .deleted(false)
                    .createdBy("system")
                    .createdAt(LocalDateTime.now())
                    .build();
            userRepository.save(admin);
            log.info("Administrateur créé: admin / Admin123!");
        }
    }
}
