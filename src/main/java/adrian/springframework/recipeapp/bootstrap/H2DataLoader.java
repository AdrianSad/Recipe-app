package adrian.springframework.recipeapp.bootstrap;

import adrian.springframework.recipeapp.models.*;
import adrian.springframework.recipeapp.repositories.CategoryRepository;
import adrian.springframework.recipeapp.repositories.RecipeRepository;
import adrian.springframework.recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Profile("default")
public class H2DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public H2DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> nullUomOptional = unitOfMeasureRepository.findByName("");
        if(!nullUomOptional.isPresent()){
            throw new RuntimeException("Expected Unit Of Measure Not Found");
        }
        Optional<UnitOfMeasure> poundUomOptional = unitOfMeasureRepository.findByName("Pound");
        if(!poundUomOptional.isPresent()){
            throw new RuntimeException("Expected Unit Of Measure Not Found");
        }
        Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByName("Teaspoon");
        if(!teaspoonUomOptional.isPresent()){
            throw new RuntimeException("Expected Unit Of Measure Not Found");
        }
        Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByName("Tablespoon");
        if(!tablespoonUomOptional.isPresent()){
            throw new RuntimeException("Expected Unit Of Measure Not Found");
        }
        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByName("Cup");
        if(!cupUomOptional.isPresent()){
            throw new RuntimeException("Expected Unit Of Measure Not Found");
        }
        Optional<UnitOfMeasure> clovesUomOptional = unitOfMeasureRepository.findByName("Cloves");
        if(!clovesUomOptional.isPresent()){
            throw new RuntimeException("Expected Unit Of Measure Not Found");
        }
        Optional<UnitOfMeasure> ounceUomOptional = unitOfMeasureRepository.findByName("Ounce");
        if(!ounceUomOptional.isPresent()){
            throw new RuntimeException("Expected Unit Of Measure Not Found");
        }
        Optional<UnitOfMeasure> sliceUomOptional = unitOfMeasureRepository.findByName("Slice");
        if(!sliceUomOptional.isPresent()){
            throw new RuntimeException("Expected Unit Of Measure Not Found");
        }

        UnitOfMeasure nullUom = nullUomOptional.get();
        UnitOfMeasure poundUom = poundUomOptional.get();
        UnitOfMeasure teaspoonUom = teaspoonUomOptional.get();
        UnitOfMeasure tablespoonUom = tablespoonUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();
        UnitOfMeasure clovesUom = clovesUomOptional.get();
        UnitOfMeasure ounceUom = ounceUomOptional.get();
        UnitOfMeasure sliceUom = sliceUomOptional.get();

        Optional<Category> chineseCategoryOptional = categoryRepository.findByName("Chinese");
        if(!chineseCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }
        Optional<Category> americanCategoryOptional = categoryRepository.findByName("American");
        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Category chineseCategory = chineseCategoryOptional.get();
        Category americanCategory = americanCategoryOptional.get();

        Recipe chickenRiceRecipe = new Recipe();
        chickenRiceRecipe.setDescription("Chicken Fried Rice");
        chickenRiceRecipe.setPreparationTime(10);
        chickenRiceRecipe.setCookTime(15);
        chickenRiceRecipe.setDifficulty(Difficulty.MODERATE);
        chickenRiceRecipe.setUrl("https://www.simplyrecipes.com/recipes/chicken_fried_rice/");
        chickenRiceRecipe.setSource("simplyrecipes.com");
        chickenRiceRecipe.setServings(2);
        chickenRiceRecipe.setDirections(
                "1 Prepare the chicken: Chop the chicken into small 1/4-inch to 1/2-inch cubes. Sprinkle 1/2 teaspoon of salt over the chicken and mix to combine. Set the chicken aside for about 10 minutes (I usually use this time to chop all the vegetables).\n" +
                "2 Scramble the egg: Heat a wok or large sauté pan over medium-high heat. Swirl in a tablespoon of oil and add the whisked eggs. Use a spatula to quickly scramble the eggs, breaking the curds into smaller pieces as they come together. Transfer the eggs to a plate.\n" +
                "3 Cook the chicken: Add another tablespoon of oil in the wok or pan. Add the chicken and cook for 4 to 5 minutes, stirring occasionally. Turn off the heat and transfer the cooked chicken to a plate.\n" +
                "4 Cook the vegetables: Swirl 1 tablespoon of oil into the wok over medium-high heat. Add the diced onions and cook them for 1 minute, until they start to soften. Mix in the minced garlic and ginger and cook until fragrant, about 30 seconds. Add the diced carrots and cook for 2 minutes, stirring frequently. Add 1/2 teaspoon salt and the peas, and stir to incorporate.\n" +
                "5 Cook the rice: Add the rice to the wok or pan on top of the vegetables and stir to combine. Using the back of your spatula, smash any large chunks of rice to break them apart. Add the white and green parts of the sliced scallions (save the dark green parts) and five-spice powder. Stir to incorporate. If the rice starts to stick to the pan, stir in a little more oil.\n" +
                "6 Serve immediately while hot.\n");
        Notes chickenRiceNotes = new Notes();
        chickenRiceNotes.setRecipeNotes(
                "Fried rice is best made with leftover rice that's at least a day old. Otherwise it becomes gummy in the skillet.\n" +
                "If you don’t have any leftover rice from the night before, cook a batch of rice and spread it on a large baking sheet or several large plates. Let the rice dry out for about 1 to 2 hours before using it for fried rice.\n" +
                "Rice sticks to the pan very easily, so make sure to use a wok or pan that doesn’t have a sticky surface. I usually cook stir-fries in my seasoned carbon steel wok, but cast iron or nonstick pans work well, too. You might need to add a little more oil if things aren’t releasing easily.\n");
        chickenRiceRecipe.setNotes(chickenRiceNotes);
        chickenRiceRecipe.addIngredient(new Ingredient("boneless skinless chicken thighs", new BigDecimal(".75"), poundUom));
        chickenRiceRecipe.addIngredient(new Ingredient("salt, divided", new BigDecimal(1), teaspoonUom));
        chickenRiceRecipe.addIngredient(new Ingredient("canola oil (or any high heat oil), divided", new BigDecimal(3), tablespoonUom));
        chickenRiceRecipe.addIngredient(new Ingredient("large eggs, whisked", new BigDecimal(3), nullUom));
        chickenRiceRecipe.addIngredient(new Ingredient("yellow onions, diced", new BigDecimal(".66"), cupUom));
        chickenRiceRecipe.addIngredient(new Ingredient("garlic, minced", new BigDecimal(2), clovesUom));
        chickenRiceRecipe.addIngredient(new Ingredient("minced ginger", new BigDecimal(2), teaspoonUom));
        chickenRiceRecipe.addIngredient(new Ingredient("large carrot, peeled and diced", new BigDecimal(1), nullUom));
        chickenRiceRecipe.addIngredient(new Ingredient("frozen peas, rinsed under warm tap water for a few seconds thaw", new BigDecimal(".66"), cupUom));
        chickenRiceRecipe.addIngredient(new Ingredient("cooked jasmine rice (preferably leftover from at least a day before, see Recipe Note)", new BigDecimal(4), cupUom));
        chickenRiceRecipe.addIngredient(new Ingredient("scallions, sliced (separate the white and light green parts from the dark green part)", new BigDecimal(2), nullUom));
        chickenRiceRecipe.addIngredient(new Ingredient("Chinese five-spice powder (optional)", new BigDecimal(".5"), teaspoonUom));
        chickenRiceRecipe.addIngredient(new Ingredient("soy sauce or tamari", new BigDecimal("2.5"), tablespoonUom));
        chickenRiceRecipe.addIngredient(new Ingredient("sesame oil", new BigDecimal(1), teaspoonUom));

        chickenRiceRecipe.getCategories().add(chineseCategory);
        recipes.add(chickenRiceRecipe);

        Recipe cocktailRecipe = new Recipe();
        cocktailRecipe.setDescription("Hurricane Cocktail");
        cocktailRecipe.setPreparationTime(5);
        cocktailRecipe.setCookTime(0);
        cocktailRecipe.setDifficulty(Difficulty.EASY);
        cocktailRecipe.setUrl("https://www.simplyrecipes.com/recipes/hurricane_cocktail/");
        cocktailRecipe.setSource("simplyrecipes.com");
        cocktailRecipe.setServings(1);
        cocktailRecipe.setDirections("1 Make the cocktail: Add the ingredients to a cocktail shaker. Fill with 8 to 10 ounces crushed ice, then shake vigorously until the cocktail shaker is too cold to hold, about 30 seconds. Pour into a hurricane glass (don't strain!). Pop in a straw and garnish with an orange slice and maraschino cherry.\n");
        Notes cocktailNotes = new Notes();
        cocktailNotes.setRecipeNotes("If using passion fruit syrup instead of purée, leave out the simple syrup – otherwise, the drink may be too sweet. ");
        cocktailRecipe.setNotes(cocktailNotes);
        cocktailRecipe.addIngredient(new Ingredient("dark rum (I used Myer's Original Dark Rum)", new BigDecimal(2), ounceUom));
        cocktailRecipe.addIngredient(new Ingredient("light rum (I used Mount Gay Silver Rum)", new BigDecimal(2), ounceUom));
        cocktailRecipe.addIngredient(new Ingredient("passion fruit purée", new BigDecimal("1.5"), ounceUom));
        cocktailRecipe.addIngredient(new Ingredient("freshly-squeezed orange juice", new BigDecimal(1), ounceUom));
        cocktailRecipe.addIngredient(new Ingredient("freshly-squeezed lime juice", new BigDecimal(1), ounceUom));
        cocktailRecipe.addIngredient(new Ingredient("simple syrup", new BigDecimal("0.5"), ounceUom));
        cocktailRecipe.addIngredient(new Ingredient("grenadine syrup", new BigDecimal("0.5"), ounceUom));
        cocktailRecipe.addIngredient(new Ingredient("Orange and maraschino cherry, for garnish", new BigDecimal(1), sliceUom));
        cocktailRecipe.getCategories().add(americanCategory);
        recipes.add(cocktailRecipe);

        return recipes;
    }

}
