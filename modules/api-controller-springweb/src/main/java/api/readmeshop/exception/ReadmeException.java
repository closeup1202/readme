package api.readmeshop.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ReadmeException extends RuntimeException{

    private final ErrorCases errorCases;
    private final Map<String, String> validation = new HashMap<>();

    public ReadmeException(ErrorCases errorCases) {
        this.errorCases = errorCases;
    }

    public ReadmeException(ErrorCases errorCases, String fieldName, String message) {
        this.errorCases = errorCases;
        this.validation.put(fieldName, message);
    }
}
