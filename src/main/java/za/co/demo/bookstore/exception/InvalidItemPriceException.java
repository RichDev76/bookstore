package za.co.demo.bookstore.exception;

public class InvalidItemPriceException extends RuntimeException {

    public InvalidItemPriceException(String message) {
        super(message);
    }


}
