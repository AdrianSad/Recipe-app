package adrian.springframework.recipeapp.repositories.reactive;

import adrian.springframework.recipeapp.models.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import javax.swing.*;

public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category, Spring> {

    Mono<Category> findByName(String name);
}
