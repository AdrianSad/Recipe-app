package adrian.springframework.recipeapp.services;

import adrian.springframework.recipeapp.commands.RecipeCommand;
import adrian.springframework.recipeapp.models.Recipe;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecipeService {
    Flux<Recipe> getRecipes();
    Mono<Recipe> findById(String id);
    Mono<RecipeCommand> saveRecipeCommand(RecipeCommand recipeCommand);
    Mono<RecipeCommand> findRecipeCommandById(String id);
    void deleteById(String id);
}
