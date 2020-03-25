package adrian.springframework.recipeapp.repositories.reactive;

import adrian.springframework.recipeapp.models.UnitOfMeasure;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import javax.swing.*;

public interface UnitOfMeasureReactiveRepository extends ReactiveMongoRepository<UnitOfMeasure, Spring> {
}
