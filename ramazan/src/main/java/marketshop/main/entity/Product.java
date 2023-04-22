package marketshop.main.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	
	private String name;
	

	private String barcode;
	
	
	
	private Double price;
	
	
	private Double cost;
	
	private String description;
	
    
	private LocalDateTime registerDate;
    
   
	private LocalDateTime updateDate;
	
	private Integer quantity;
	
	private Double percent;
	
	

}
