package adrian.springframework.recipeapp.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(){
        super();
    }

    public NotFoundException(String exception){
        super(exception);
    }

    public NotFoundException(String exception, Throwable cause){
        super(exception, cause);
    }
}
