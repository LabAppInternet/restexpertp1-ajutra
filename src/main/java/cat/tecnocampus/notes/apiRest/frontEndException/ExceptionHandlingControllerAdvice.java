package cat.tecnocampus.notes.apiRest.frontEndException;

import cat.tecnocampus.notes.application.exception.UserNotFoundException;
import cat.tecnocampus.notes.domain.exception.NotEditableNote;
import cat.tecnocampus.notes.domain.exception.NoteNotOwnedException;
import cat.tecnocampus.notes.persistence.exception.NoteDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlingControllerAdvice {
    @ExceptionHandler({UserNotFoundException.class, NoteDoesNotExistException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    String resourceNotFoundHandler(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler({NotEditableNote.class, NoteNotOwnedException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    String notEditableHandler(Exception e) {
        return e.getMessage();
    }
}
