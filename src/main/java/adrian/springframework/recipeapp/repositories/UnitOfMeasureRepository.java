package adrian.springframework.recipeapp.repositories;

import adrian.springframework.recipeapp.models.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
