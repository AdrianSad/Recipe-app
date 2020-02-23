package adrian.springframework.recipeapp.repositories;

import adrian.springframework.recipeapp.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
