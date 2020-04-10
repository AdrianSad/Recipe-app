package adrian.springframework.recipeapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;

@ControllerAdvice
public class ControllerExceptionHandler {

   @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException.class)
    public String handleNumberFormat(Exception exception, Model model){
       ;
        model.addAttribute("exception", exception);
        return "errors/400ErrorPage";
    }
}
