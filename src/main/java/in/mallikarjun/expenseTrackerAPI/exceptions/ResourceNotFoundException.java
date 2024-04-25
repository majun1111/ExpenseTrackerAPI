package in.mallikarjun.expenseTrackerAPI.exceptions;


public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message){
        super(message);
    }
}
