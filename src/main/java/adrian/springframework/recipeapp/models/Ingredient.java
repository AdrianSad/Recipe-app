package adrian.springframework.recipeapp.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;

@Getter
@Setter
public class Ingredient {

    @Id
    private String id;
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
