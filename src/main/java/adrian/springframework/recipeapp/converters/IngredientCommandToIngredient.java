package adrian.springframework.recipeapp.converters;

import adrian.springframework.recipeapp.commands.IngredientCommand;
import adrian.springframework.recipeapp.models.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) {
            return null;
        }

        final Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        ingredient.setAmount(source.getAmount());
        ingredient.setName(source.getName());
        ingredient.setUnitOfMeasure(source.getUnitOfMeasure());
        return ingredient;
    }
}