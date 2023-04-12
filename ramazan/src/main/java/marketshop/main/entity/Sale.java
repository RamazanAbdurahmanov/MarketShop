package marketshop.main.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Data 
@Table(name="sales")
public class Sale {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    private Product product;
    
    private String cashier;
    
    private Date saleDate;
    
    private Integer quantity;
    
    private Double totalPrice;
}
