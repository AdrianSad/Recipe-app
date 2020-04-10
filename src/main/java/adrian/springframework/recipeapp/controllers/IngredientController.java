package adrian.springframework.recipeapp.controllers;

import adrian.springframework.recipeapp.commands.IngredientCommand;
import adrian.springframework.recipeapp.commands.RecipeCommand;
import adrian.springframework.recipeapp.commands.UnitOfMeasureCommand;
import adrian.springframework.recipeapp.services.IngredientService;
import adrian.springframework.recipeapp.services.RecipeService;
import adrian.springframework.recipeapp.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    private WebDataBinder webDataBinder;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @InitBinder("ingredient")
    public void initBinder(WebDataBinder webDataBinder){
        this.webDataBinder = webDataBinder;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String ingredientList(@PathVariable String recipeId, Model model){

        model.addAttribute("recipe", recipeService.findRecipeCommandById(recipeId));
        return "ingredient/list";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model){

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));
        return "ingredient/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateIngredient(@PathVariable String recipeId, @PathVariable String id, Model model){

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));
        return "ingredient/ingredientForm";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdateIngredient(@ModelAttribute("ingredient") IngredientCommand ingredientCommand,
                                         @PathVariable String recipeId,
                                         Model model){

        webDataBinder.validate();
        BindingResult bindingResult = webDataBinder.getBindingResult();

        if(bindingResult.hasErrors()){
            model.addAttribute("uomList", unitOfMeasureService.uomCommandsList());
            return "ingredient/ingredientForm";
        }

        IngredientCommand savedIngredient = ingredientService.saveIngredientCommand(ingredientCommand).block();
        return "redirect:/recipe/" + ingredientCommand.getRecipeId() + "/ingredient/" + savedIngredient.getId() + "/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/new")
    public String newIngredientForm(@PathVariable String recipeId, Model model){

        RecipeCommand recipeCommand = recipeService.findRecipeCommandById(recipeId).block();

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipeId);

        model.addAttribute("ingredient", ingredientCommand);
        ingredientCommand.setUom(new UnitOfMeasureCommand());

        return "ingredient/ingredientForm";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId, @PathVariable String id){

        ingredientService.deleteIngredientById(recipeId, id).block();

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

    @ModelAttribute("uomList")
    Flux<UnitOfMeasureCommand> uomListAttribute(){
        return unitOfMeasureService.uomCommandsList();
    }
}
