package lab.anubis.saasmultitenantapp.repositories;

import lab.anubis.saasmultitenantapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findByReferenceIgnoreCase(String reference);
}
