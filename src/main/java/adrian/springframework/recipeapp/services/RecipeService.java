package adrian.springframework.recipeapp.services;

import adrian.springframework.recipeapp.commands.RecipeCommand;
import adrian.springframework.recipeapp.models.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
    RecipeCommand findRecipeCommandById(Long id);
}
