package marketshop.main.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message="Boş qoymaq olmaz")
	@Size(min=2, message="Minimum 2 simvol yazila biler")
	@Size(max=30, message="maksimum 30 simvol yazila biler")
	private String name;
	
	@NotEmpty(message="Boş qoymaq olmaz")
	@Size(min=2, message="Minimum 2 simvol yazila biler")
	@Size(max=30, message="maksimum 20 simvol yazila biler")
	private String barcode;
	
	
	@Min(value=0,message="Minimum 0 qiymet vermek olar")
	@Max(value=100000,message="Maksimum 1000000 qiymet vermek olar")
    @NotNull(message="boş qoymaq olmaz!")
	private Double price;
	
	@Min(value=0,message="Minimum 0 qiymet vermek olar")
	@Max(value=100000,message="Maksimum 1000000 qiymet vermek olar")
   @NotNull(message="boş qoymaq olmaz!")
	private Double cost;
	
	@Size(max=300,message="Maksiumum 200 simvol yaza bilersiz")
	private String description;
	
    
	private LocalDateTime registerDate;
    
    
	private LocalDateTime updateDate;
	
	@Min(value=1,message="Product sayı minimum 1 ola bilər")
	@Max(value=10000,message="Product sayı maksimum 10000 ola bilər")
	private Integer quantity;
	
	private Double percent;
	
	

}
