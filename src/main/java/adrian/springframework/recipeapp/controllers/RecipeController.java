package adrian.springframework.recipeapp.controllers;

import adrian.springframework.recipeapp.commands.RecipeCommand;
import adrian.springframework.recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    private WebDataBinder webDataBinder;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        this.webDataBinder = webDataBinder;
    }

    @GetMapping
    @RequestMapping("recipe/{id}/show")
    public String getRecipe(@PathVariable String id, Model model){

        model.addAttribute("recipe", recipeService.findById(id));
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
    public String saveOrUpdate(@ModelAttribute("recipe") RecipeCommand recipeCommand){

        webDataBinder.validate();
        BindingResult bindingResult = webDataBinder.getBindingResult();

        if(bindingResult.hasErrors()){
            return "recipeForm";
        }

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand).block();
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){

        model.addAttribute("recipe",recipeService.findRecipeCommandById(id));
        return "recipeForm";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id){

        recipeService.deleteById(id);
        return "redirect:/";
    }

  /*  @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errors/404ErrorPage");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }*/
}
