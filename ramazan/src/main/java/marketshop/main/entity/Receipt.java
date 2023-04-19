package marketshop.main.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "receipts")
public class Receipt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate saleDate;
	private double totalAmount;
	
	private String productName;
	private Integer productCount;
	private String cashier;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "receipt_id")
	private List<Sale> sales = new ArrayList<>();



}
 