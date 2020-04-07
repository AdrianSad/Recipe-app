package adrian.springframework.recipeapp.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ControllerExceptionHandler {

   /* @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormat(Exception exception){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errors/400ErrorPage");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }*/
}
