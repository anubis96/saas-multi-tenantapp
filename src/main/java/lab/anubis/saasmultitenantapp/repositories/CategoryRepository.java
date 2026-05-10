package lab.anubis.saasmultitenantapp.repositories;

import lab.anubis.saasmultitenantapp.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByNameIgnoreCase(String name);

//    @Modifying
//    @Query("UPDATE Category c SET e.deleted = true, e.deletedAt = :deletedAt, e.deletedBy = :deletedBy WHERE e.id = :employeeId")
//    void softDelete(@Param("employeeId") Long employeeId, @Param("deletedAt") LocalDateTime deletedAt, @Param("deletedBy") String deletedBy);

    @Modifying
    @Query("UPDATE Category c SET c.deleted = true WHERE c.id = :id")
    void softDelete(@Param("id") String id);
}
