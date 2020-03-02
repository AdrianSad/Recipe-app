package adrian.springframework.recipeapp.commands;

import adrian.springframework.recipeapp.models.UnitOfMeasure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private String name;
    private BigDecimal amount;
    private UnitOfMeasure unitOfMeasure;
}
