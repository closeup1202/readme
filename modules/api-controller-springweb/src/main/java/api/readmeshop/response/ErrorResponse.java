package api.readmeshop.response;

import api.readmeshop.exception.ErrorCases;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
@Slf4j
public class ErrorResponse {

    private final String code;
    private final HttpStatus status;
    private final String message;
    private final Map<String, String> validation;

    @Builder
    public ErrorResponse(ErrorCases code, Map<String, String> validation) {
        this.code = code.getCode();
        this.status = code.getStatus();
        this.message = code.getMessage();
        this.validation = validation != null ? validation : new HashMap<>();
    }
}
