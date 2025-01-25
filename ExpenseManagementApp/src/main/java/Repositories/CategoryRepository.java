package Repositories;

import Model.Category;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(value = "SELECT * FROM category WHERE scope_id = :scopeId", nativeQuery = true)
    List<Category> findCategoriesByScopeId(@Param("scopeId") Long scopeId);
}
