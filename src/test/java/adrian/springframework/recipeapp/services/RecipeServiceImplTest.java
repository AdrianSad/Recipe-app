package adrian.springframework.recipeapp.services;

import adrian.springframework.recipeapp.converters.RecipeCommandToRecipe;
import adrian.springframework.recipeapp.converters.RecipeToRecipeCommand;
import adrian.springframework.recipeapp.models.Recipe;
import adrian.springframework.recipeapp.repositories.reactive.RecipeReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

   RecipeServiceImpl recipeService;
   @Mock
   RecipeReactiveRepository recipeReactiveRepository;

   @Mock
   RecipeToRecipeCommand recipeToRecipeCommand;

   @Mock
   RecipeCommandToRecipe recipeCommandToRecipe;

   @Before
    public void setUp() throws Exception{

       MockitoAnnotations.initMocks(this);
       recipeService = new RecipeServiceImpl(recipeReactiveRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipeByIdTest() throws Exception {
       Recipe recipe = new Recipe();
       recipe.setId("1");
       when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));
       Recipe recipeReturned = recipeReactiveRepository.findById("1").block();
       assertNotNull(recipeReturned,"Null recipe returned");
       verify(recipeReactiveRepository, times(1)).findById(anyString());
       verify(recipeReactiveRepository, never()).findAll();
    }

    @Test
    public void getRecipes() throws Exception{
       Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);
        when(recipeService.getRecipes()).thenReturn(Flux.just(recipe));
        List<Recipe> recipes = recipeService.getRecipes().collectList().block();
        assertEquals(recipes.size(), 1);
        verify(recipeReactiveRepository,times(1)).findAll();
        verify(recipeReactiveRepository, never()).findById(anyString());
    }
}