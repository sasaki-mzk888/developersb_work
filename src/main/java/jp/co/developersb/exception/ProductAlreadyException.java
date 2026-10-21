package jp.co.developersb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProductAlreadyException extends RuntimeException {
    public ProductAlreadyException(String message) {
        super(message);
    }
}