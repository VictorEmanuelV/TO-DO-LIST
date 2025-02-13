package victoremanuelvieiradev.to_do_list.web.exception;

public class JwtCreationException  extends RuntimeException{
    public JwtCreationException(String message){
        super(message);
    }
}
