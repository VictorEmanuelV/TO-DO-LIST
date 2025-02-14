package victoremanuelvieiradev.to_do_list.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SpringConfigSwagger {
    
    @Bean
    public OpenAPI configOpenApi(){

        Contact myContact = new Contact();
        myContact.setName("Victor Emanuel");
        myContact.setEmail("victoremanuelvieira@gmail.com");
        
        return new OpenAPI()
                .info(new Info()
                        .title("API To Do List")
                        .version("1.0")
                        .contact(myContact) 
                        .description("API-TO DO LIST")); 
                              
    }
}

