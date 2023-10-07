package med.voll.api.model.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerClass {

    //dto_exception to return exception body
        private record dtoException(String field, String errorMessage){
            public dtoException(FieldError e){
                this(e.getField(), e.getDefaultMessage());
            }
        }

    //404 NOT FOUND
        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity notFound(){
            return ResponseEntity.notFound().build();
        }

    //400 MISMATCH TYPE DATA
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity typeMismatch(MethodArgumentNotValidException e) {
            var errors= e.getFieldErrors().stream().map(dtoException::new).toList();
            return ResponseEntity.badRequest().body(errors);
        }

    //400 SQL INTEGRITY DATA EXCEPTION
        @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity sqlIntegrityException(DataIntegrityViolationException e) {
            var messageError= e.getCause().getCause().getMessage();
            String errorType= "sql_field";
            dtoException exception= new dtoException(errorType, messageError);

            return ResponseEntity.badRequest().body(exception);
        }
}
