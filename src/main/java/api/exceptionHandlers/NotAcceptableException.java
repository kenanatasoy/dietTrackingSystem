package api.exceptionHandlers;

public class NotAcceptableException extends RuntimeException{
    public NotAcceptableException(String message) {
        super(message);
    }
}
