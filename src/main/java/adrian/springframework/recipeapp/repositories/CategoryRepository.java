package adrian.springframework.recipeapp.repositories;

import adrian.springframework.recipeapp.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
