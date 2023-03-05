package co.com.sofka.catalog.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus;

@Data
public class ExceptionsHandler extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String message;


    public ExceptionsHandler(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
