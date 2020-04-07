package adrian.springframework.recipeapp.services;

import adrian.springframework.recipeapp.commands.IngredientCommand;
import adrian.springframework.recipeapp.commands.UnitOfMeasureCommand;
import adrian.springframework.recipeapp.converters.IngredientCommandToIngredient;
import adrian.springframework.recipeapp.converters.IngredientToIngredientCommand;
import adrian.springframework.recipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import adrian.springframework.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import adrian.springframework.recipeapp.models.Ingredient;
import adrian.springframework.recipeapp.models.Recipe;
import adrian.springframework.recipeapp.repositories.UnitOfMeasureRepository;
import adrian.springframework.recipeapp.repositories.reactive.RecipeReactiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    @Mock
    RecipeReactiveRepository recipeReactiveRepository;

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
        ingredientService = new IngredientServiceImpl(recipeReactiveRepository, unitOfMeasureRepository, ingredientToIngredientCommand, ingredientCommandToIngredient);
    }

    @Test
    void findByRecipeIdAndIngredientId() {
        Recipe recipe = new Recipe();
        recipe.setId("1");
        Ingredient ingredient = new Ingredient();
        ingredient.setId("2");
        recipe.addIngredient(ingredient);
        Mono<Recipe> recipeOptional = Mono.just(recipe);

        when(recipeReactiveRepository.findById(anyString())).thenReturn(recipeOptional);

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId("1", "2").block();

        assertEquals("1", recipe.getId());
        assertEquals("2", ingredientCommand.getId());

        verify(recipeReactiveRepository,times(1)).findById(anyString()).block();
    }

    @Test
    void saveIngredientCommand() {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId("3");
        ingredientCommand.setRecipeId("2");
        ingredientCommand.setName("test");
        ingredientCommand.setUom(new UnitOfMeasureCommand());
        ingredientCommand.getUom().setId("4");

        Recipe recipe = new Recipe();
        recipe.addIngredient(new Ingredient());
        recipe.getIngredients().iterator().next().setId("3");

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(new Recipe()));
        when(recipeReactiveRepository.save(any())).thenReturn(Mono.just(recipe));

        IngredientCommand savedIngredient = ingredientService.saveIngredientCommand(ingredientCommand).block();

        assertEquals("3", savedIngredient.getId());
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, times(1)).save(any(Recipe.class));
    }
}