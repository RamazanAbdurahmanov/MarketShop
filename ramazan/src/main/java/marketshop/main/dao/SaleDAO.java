package marketshop.main.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import marketshop.main.entity.Sale;

public interface SaleDAO extends JpaRepository<Sale, Integer>{
	
	List<Sale> findAllBySaleDateBetween(LocalDateTime startDate, LocalDateTime endDate);
	
}
