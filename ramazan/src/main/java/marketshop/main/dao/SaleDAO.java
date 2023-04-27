package marketshop.main.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import marketshop.main.entity.Sale;

public interface SaleDAO extends JpaRepository<Sale, Integer>{
	
	 List<Sale> findBySaleDateBetween(LocalDate startDate, LocalDate endDate);	
	 public void getSaleById(Integer id);
	 
	
		

		//en chox satilan mehsul
		@Query(value="SELECT PRODUCT_NAME\r\n"
				+ "FROM SALES\r\n"
				+ "GROUP BY PRODUCT_NAME\r\n"
				+ "ORDER BY SUM(QUANTITY) DESC\r\n"
				+ "LIMIT 1;", nativeQuery = true)
		public List<Object[]> findBestSellingProduct();
		
		//en chox satilan 3  mehsul
		@Query(value="SELECT PRODUCT_NAME, SUM(QUANTITY) AS TOTAL_SALES\r\n"
				+ "FROM SALES\r\n"
				+ "GROUP BY PRODUCT_NAME\r\n"
				+ "ORDER BY TOTAL_SALES DESC\r\n"
				+ "LIMIT 3;", nativeQuery = true)
		public List<Object> top3SellingProduct();
		
}

