package adrian.springframework.recipeapp.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class Ingredient {

    private String id = UUID.randomUUID().toString();
    private String name;
    private BigDecimal amount;

    @DBRef
    private UnitOfMeasure unitOfMeasure;

    public Ingredient(){}

    public Ingredient(String name, BigDecimal amount, UnitOfMeasure unitOfMeasure){
        this.name = name;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }

    public Ingredient(String name, BigDecimal amount, UnitOfMeasure unitOfMeasure, Recipe recipe){
        this.name = name;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
        //this.recipe = recipe;
    }
}
