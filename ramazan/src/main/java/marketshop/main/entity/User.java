package marketshop.main.entity;

import javax.persistence.Entity; 
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    private String username;
    private String password;
    private Boolean enabled;
    
}


