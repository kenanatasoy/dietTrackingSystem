package api.exceptionHandlers;

public class EntitiesNotFoundException extends RuntimeException {
    public EntitiesNotFoundException(String message){
        super(message);
    }
}
