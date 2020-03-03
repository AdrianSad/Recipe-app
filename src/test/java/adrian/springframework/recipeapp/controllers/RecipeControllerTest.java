package adrian.springframework.recipeapp.controllers;

import adrian.springframework.recipeapp.commands.RecipeCommand;
import adrian.springframework.recipeapp.models.Recipe;
import adrian.springframework.recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    RecipeController recipeController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    void testGetRecipe() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void testGetNewRecipeForm() throws Exception {
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipeForm"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void testPostNewRecipeForm() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(2L);
        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/show"));
    }

    @Test
    void testGetUpdateView() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(2L);
        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipeForm"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void testDeleteAction() throws Exception {
        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(recipeService, times(1)).deleteById(anyLong());
    }
}