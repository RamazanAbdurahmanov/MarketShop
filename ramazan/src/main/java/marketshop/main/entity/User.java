package marketshop.main.entity;


import javax.persistence.Entity; 
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
    @Size(min = 1, message = "İstifadəçi adı minimum 1 simvol ola bilər")
	@Size(max = 30, message = "İstifadəçi adı maksimum 30 simvol ola bilər")
	@NotEmpty(message = "Boş qoymaq olmaz")
    private String username;
    
    @Size(min = 1, message = "Şifrə  minimum 1 simvol ola bilər")
	@Size(max = 30, message = "Şifrə maksimum 30 simvol ola bilər")
    private String password;
    
    private Boolean enabled;
    
    
   
    
    
}


