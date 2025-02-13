package victoremanuelvieiradev.to_do_list.web.exception;

public class JwtVerificationException extends RuntimeException{
    public JwtVerificationException(String message){
        super(message);
    }
}
