package marketshop.main.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
	@NotEmpty(message = "Boş qoymaq olmaz")
	// @Size(min = 2, max = 20, message = "Istifadəçi adı 2-20 simvol arasında
	// olmalıdır.")

	private String username;

	@NotEmpty(message = "Boş qoymaq olmaz")
	// @Size(min = 2, max = 20, message = "Şifrə 2-20 simvol arasında olmalıdır.")
	// @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
	// message="Şifrə ən az 1 böyük hərf, 1 kiçik hərf, 1 rəqəm və 1 simvol ")
	private String password;

	private Boolean enabled;

}
