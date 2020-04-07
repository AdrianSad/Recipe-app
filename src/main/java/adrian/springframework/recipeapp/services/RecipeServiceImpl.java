package adrian.springframework.recipeapp.services;

import adrian.springframework.recipeapp.commands.RecipeCommand;
import adrian.springframework.recipeapp.converters.RecipeCommandToRecipe;
import adrian.springframework.recipeapp.converters.RecipeToRecipeCommand;
import adrian.springframework.recipeapp.models.Recipe;
import adrian.springframework.recipeapp.repositories.reactive.RecipeReactiveRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeReactiveRepository recipeReactiveRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeReactiveRepository recipeReactiveRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeReactiveRepository = recipeReactiveRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Flux<Recipe> getRecipes() {
        return recipeReactiveRepository.findAll();
    }

    @Override
    public Mono<Recipe> findById(String id) {

        return recipeReactiveRepository.findById(id);
    }

    @Override
    public Mono<RecipeCommand> saveRecipeCommand(RecipeCommand recipeCommand) {

        return recipeReactiveRepository.save(recipeCommandToRecipe.convert(recipeCommand))
                .map(recipeToRecipeCommand::convert);
    }

    @Override
    public Mono<RecipeCommand> findRecipeCommandById(String id) {

        return recipeReactiveRepository.findById(id)
                .map(recipe -> {
                   RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);

                   recipeCommand.getIngredients().forEach(rc -> {
                       rc.setRecipeId(recipeCommand.getId());
                   });
                   return recipeCommand;
                });


        /*RecipeCommand recipeCommand =  recipeToRecipeCommand.convert(findById(id).block());

        if(recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0){
            recipeCommand.getIngredients().forEach(rc -> {
                rc.setRecipeId(recipeCommand.getId());
            });
        }

        return recipeCommand;*/

    }

    @Override
    public void deleteById(String id) {
        recipeReactiveRepository.deleteById(id).block();
    }
}
