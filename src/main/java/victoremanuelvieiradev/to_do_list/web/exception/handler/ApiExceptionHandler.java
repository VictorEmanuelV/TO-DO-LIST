package victoremanuelvieiradev.to_do_list.web.exception.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import jakarta.servlet.http.HttpServletRequest;
import victoremanuelvieiradev.to_do_list.web.exception.EmailAlreadyExistException;
import victoremanuelvieiradev.to_do_list.web.exception.UserNotFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {
    
    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<StandardError>emailAlreadyExistException(EmailAlreadyExistException ex,HttpServletRequest req){
        HttpStatus status = HttpStatus.CONFLICT;
        var error = new StandardError();
        error.setMessage(ex.getMessage());
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setPath(req.getRequestURI());
        error.setError(status.getReasonPhrase());
        return ResponseEntity.status(status.value()).body(error);
    }

    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity<StandardError>jwtCreationException(JWTCreationException ex,HttpServletRequest req){
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        var error = new StandardError();
        error.setMessage(ex.getMessage());
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setPath(req.getRequestURI());
        error.setError(status.getReasonPhrase());
        return ResponseEntity.status(status.value()).body(error);
    }
    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<StandardError>jwtVerificationException(JWTVerificationException ex,HttpServletRequest req){
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        var error = new StandardError();
        error.setMessage(ex.getMessage());
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setPath(req.getRequestURI());
        error.setError(status.getReasonPhrase());
        return ResponseEntity.status(status.value()).body(error);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StandardError>userNotFoundException(UserNotFoundException ex,HttpServletRequest req){
        HttpStatus status = HttpStatus.NOT_FOUND;
        var error = new StandardError();
        error.setMessage(ex.getMessage());
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setPath(req.getRequestURI());
        error.setError(status.getReasonPhrase());
        return ResponseEntity.status(status.value()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidatorError>methodArgumentNotValidException (HttpServletRequest req,BindingResult r){
                   
        HttpStatus status = HttpStatus.CONFLICT;
        var error = new ValidatorError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setPath(req.getRequestURI());
        error.setError(status.getReasonPhrase());
        error.addErrors(r);
        return ResponseEntity.status(status.value()).body(error);
    }
}
