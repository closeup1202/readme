package api.readmeshop.advice;

import api.readmeshop.service.exception.ErrorResponse;
import api.readmeshop.service.exception.ReadmeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(ReadmeException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(ReadmeException e){

        ErrorResponse response = ErrorResponse.builder()
                                            .code(e.getErrorCases())
                                            .validation(e.getValidation())
                                            .build();

        return ResponseEntity.status(e.getErrorCases().getStatus()).body(response);
    }
}
