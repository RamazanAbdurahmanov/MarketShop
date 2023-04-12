package marketshop.main.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import marketshop.main.entity.Sale;

public interface SaleDAO extends JpaRepository<Sale, Integer>{
	
	List<Sale> findAllBySaleDateBetween(Date startDate, Date endDate);
	
}
