package adrian.springframework.recipeapp.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Set;

@Getter
@Setter
@Document
public class Category {

    @Id
    private String id;
    private String name;

    @DBRef
    private Set<Recipe> recipes;

}
