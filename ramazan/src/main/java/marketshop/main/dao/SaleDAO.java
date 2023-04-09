package marketshop.main.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import marketshop.main.entity.Sale;

public interface SaleDAO extends JpaRepository<Sale, Integer>{
	
	@Query(value="select*from sales where sale_date between '?1' AND '?2'",nativeQuery = true)
	public List<Sale> findByBetweenSaleDate(Date startDate,Date endDate);


}
