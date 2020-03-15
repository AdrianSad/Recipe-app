package adrian.springframework.recipeapp.services;

import adrian.springframework.recipeapp.converters.RecipeCommandToRecipe;
import adrian.springframework.recipeapp.converters.RecipeToRecipeCommand;
import adrian.springframework.recipeapp.models.Recipe;
import adrian.springframework.recipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

   RecipeServiceImpl recipeService;
   @Mock
   RecipeRepository recipeRepository;

   @Mock
   RecipeToRecipeCommand recipeToRecipeCommand;

   @Mock
   RecipeCommandToRecipe recipeCommandToRecipe;

   @Before
    public void setUp() throws Exception{

       MockitoAnnotations.initMocks(this);
       recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipeByIdTest() throws Exception {
       Recipe recipe = new Recipe();
       recipe.setId("1");
       Optional<Recipe> recipeOptional = Optional.of(recipe);
       when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);
       Recipe recipeReturned = recipeService.findById("1");
       assertNotNull(recipeReturned,"Null recipe returned");
       verify(recipeRepository, times(1)).findById(anyString());
       verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getRecipes() throws Exception{
       Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);
        when(recipeService.getRecipes()).thenReturn(recipesData);
        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(), 1);
        verify(recipeRepository,times(1)).findAll();
        verify(recipeRepository, never()).findById(anyString());
    }
}