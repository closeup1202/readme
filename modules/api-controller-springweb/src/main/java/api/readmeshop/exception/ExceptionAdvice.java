package api.readmeshop.exception;

import api.readmeshop.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(ReadmeException.class)
    public ResponseEntity exceptionHandling(ReadmeException e){

        ErrorResponse response = ErrorResponse.builder()
                                            .code(e.getErrorCases())
                                            .validation(e.getValidation())
                                            .build();

        return ResponseEntity.status(e.getErrorCases().getStatus()).body(response);
    }
}
