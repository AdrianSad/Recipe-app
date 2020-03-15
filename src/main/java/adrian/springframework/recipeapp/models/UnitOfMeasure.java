package adrian.springframework.recipeapp.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Document
public class UnitOfMeasure {

    @Id
    private String id;
    private String name;

}
