package marketshop.main.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import marketshop.main.entity.Receipt;

public interface ReceiptDAO extends JpaRepository<Receipt, Integer> {
	// en chox satish eden kassiri tapir
		 @Query(value="SELECT CASHIER, SUM(PRODUCT_COUNT) AS TOTAL_PRODUCTS_SOLD\r\n"
					+ "FROM RECEIPTS\r\n"
					+ "GROUP BY CASHIER\r\n"
					+ "ORDER BY TOTAL_PRODUCTS_SOLD DESC\r\n"
					+ "LIMIT 1;",nativeQuery = true)
			public List<Object[]> findBestCashier();

	

	
	

	
}
