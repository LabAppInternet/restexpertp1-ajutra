package cat.tecnocampus.notes.apiRest.frontEndException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {
    private record Violation(String fieldName, String message) {}

    private static class ValidationErrorResponse {
        private final List<Violation> violations = new ArrayList<>();

        public List<Violation> getViolations() {
            return violations;
        }
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();

        for (ConstraintViolation violation : e.getConstraintViolations())
            error.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));

        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors())
            error.getViolations().add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));

        return error;
    }
}
