package marketshop.main.entity;


import javax.persistence.Entity; 
import javax.persistence.Id;
import javax.persistence.Table;


import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Component
@Entity
@Table(name = "users")
@Data 
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    
    private String username;
    
    
    private String password;
    
    private Boolean enabled;
    
    
   
    
    
}


