package adrian.springframework.recipeapp.repositories;

import adrian.springframework.recipeapp.bootstrap.DataLoader;
import adrian.springframework.recipeapp.models.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RecipeRepository recipeRepository;


    @Before
    public void setUp() throws Exception {

        unitOfMeasureRepository.deleteAll();
        categoryRepository.deleteAll();
        recipeRepository.deleteAll();

        DataLoader dataLoader = new DataLoader(recipeRepository, categoryRepository, unitOfMeasureRepository);
        dataLoader.onApplicationEvent(null);
    }

    @Test
    public void findByName() throws Exception {
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByName("Teaspoon");
        assertEquals("Teaspoon", uomOptional.get().getName());
    }
}