package in.mallikarjun.expenseTrackerAPI.exceptions;

public class ItemAlreadyExistException extends RuntimeException{
    public ItemAlreadyExistException(String message){
        super(message);
    }
}
