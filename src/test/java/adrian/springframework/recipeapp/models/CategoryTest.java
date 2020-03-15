package adrian.springframework.recipeapp.models;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    Category category;

    @Before
    public void setUp() throws Exception {
        category = new Category();
    }

    @Test
    void getId() throws Exception {
        String idValue = "4";
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    void getName() throws Exception {
        category.setName("testCategory");
    }

    @Test
    void getRecipes() throws Exception {
    }
}