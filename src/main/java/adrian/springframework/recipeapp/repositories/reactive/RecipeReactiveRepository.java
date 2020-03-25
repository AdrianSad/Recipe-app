package adrian.springframework.recipeapp.repositories.reactive;

import adrian.springframework.recipeapp.models.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import javax.swing.*;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, Spring> {


}
