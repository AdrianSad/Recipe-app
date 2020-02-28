package adrian.springframework.recipeapp.repositories;

import adrian.springframework.recipeapp.models.UnitOfMeasure;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByName() throws Exception {
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByName("Teaspoon");
        assertEquals("Teaspoon", uomOptional.get().getName());
    }
}