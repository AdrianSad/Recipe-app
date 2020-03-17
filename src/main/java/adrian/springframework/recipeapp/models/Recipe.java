package adrian.springframework.recipeapp.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document
public class Recipe {

    private String id;
    private String description;
    private Integer preparationTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private List<Ingredient> ingredients = new ArrayList<>();
    private Byte[] image;
    private Notes notes;
    private Difficulty difficulty;

    @DBRef
    private List<Category> categories = new ArrayList<>();

    public Recipe addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
        return this;
    }

    public void setNotes(Notes notes) {
        if(notes != null) {
            this.notes = notes;
        }
    }

}
