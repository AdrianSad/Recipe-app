package adrian.springframework.recipeapp.repositories;

import adrian.springframework.recipeapp.models.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,Long> {
    Optional<Category> findByName(String name);
}
