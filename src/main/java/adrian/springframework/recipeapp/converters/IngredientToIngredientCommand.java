package adrian.springframework.recipeapp.converters;

import adrian.springframework.recipeapp.commands.IngredientCommand;
import adrian.springframework.recipeapp.models.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setName(ingredient.getName());
        ingredientCommand.setUnitOfMeasure(ingredient.getUnitOfMeasure());
        return ingredientCommand;
    }
}