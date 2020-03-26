package adrian.springframework.recipeapp.repositories.reactive;

import adrian.springframework.recipeapp.models.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {


}
