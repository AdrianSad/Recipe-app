package adrian.springframework.recipeapp.controllers;

import adrian.springframework.recipeapp.commands.RecipeCommand;
import adrian.springframework.recipeapp.exceptions.NotFoundException;
import adrian.springframework.recipeapp.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("recipe/{id}/show")
    public String getRecipe(@PathVariable String id, Model model){

        model.addAttribute("recipe", recipeService.findById(new Long(id)));
        return "show";
    }

    @GetMapping
    @RequestMapping("recipe/new")
    public String newRecipe(Model model){

        model.addAttribute("recipe", new RecipeCommand());
        return "recipeForm";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand){

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){

        model.addAttribute("recipe",recipeService.findRecipeCommandById(Long.valueOf(id)));
        return "recipeForm";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id){

        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errors/404ErrorPage");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormat(Exception exception){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errors/400ErrorPage");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }
}
