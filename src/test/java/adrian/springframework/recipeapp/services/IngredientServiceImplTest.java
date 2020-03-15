package adrian.springframework.recipeapp.services;

import adrian.springframework.recipeapp.commands.IngredientCommand;
import adrian.springframework.recipeapp.converters.IngredientCommandToIngredient;
import adrian.springframework.recipeapp.converters.IngredientToIngredientCommand;
import adrian.springframework.recipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import adrian.springframework.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import adrian.springframework.recipeapp.models.Ingredient;
import adrian.springframework.recipeapp.models.Recipe;
import adrian.springframework.recipeapp.repositories.RecipeRepository;
import adrian.springframework.recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;

    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    IngredientServiceImplTest() {
        ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(recipeRepository, unitOfMeasureRepository, ingredientToIngredientCommand, ingredientCommandToIngredient);
    }

    @Test
    void findByRecipeIdAndIngredientId() {
        Recipe recipe = new Recipe();
        recipe.setId("1");
        Ingredient ingredient = new Ingredient();
        ingredient.setId("2");
        recipe.addIngredient(ingredient);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId("1", "2");

        assertEquals(Long.valueOf(1L), recipe.getId());
        assertEquals(Long.valueOf(2L), ingredientCommand.getId());

        verify(recipeRepository,times(1)).findById(anyString());
    }

    @Test
    void saveIngredientCommand() {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId("3");
        ingredientCommand.setRecipeId("2");

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe recipe = new Recipe();
        recipe.addIngredient(new Ingredient());
        recipe.getIngredients().iterator().next().setId("1");

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(recipe);

        IngredientCommand savedIngredient = ingredientService.saveIngredientCommand(ingredientCommand);

        assertEquals(Long.valueOf(3L), savedIngredient.getId());
        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }
}