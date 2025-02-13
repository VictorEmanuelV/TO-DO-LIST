package victoremanuelvieiradev.to_do_list.web.exception.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ValidatorError extends StandardError{
    private List<FieldMessage> erros = new ArrayList<>();
    
    public void addErrors(BindingResult r){
        for (FieldError f: r.getFieldErrors()) {

           erros.add(new FieldMessage(f.getField(),f.getDefaultMessage()));
        }
    }
}
