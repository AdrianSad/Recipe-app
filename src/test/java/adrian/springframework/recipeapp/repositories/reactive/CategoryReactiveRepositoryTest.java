package adrian.springframework.recipeapp.repositories.reactive;

import adrian.springframework.recipeapp.models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
class CategoryReactiveRepositoryTest {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @BeforeEach
    void setUp() {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    void save(){
        Category category = new Category();
        category.setName("Polish");

        categoryReactiveRepository.save(category).then().block();

        assertEquals(Long.valueOf(1L),categoryReactiveRepository.count().block());
    }

    @Test
    void findByName() {
        Category category = new Category();
        category.setName("Polish");

        categoryReactiveRepository.save(category).then().block();

        Category foundCategory = categoryReactiveRepository.findByName("Polish").block();

        assertNotNull(foundCategory.getId());
    }
}