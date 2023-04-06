package marketshop.main.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marketshop.main.dao.SaleDAO;
import marketshop.main.entity.Sale;

@Service
public class SaleService {
	 @Autowired
	    private SaleDAO saleDAO;

	    public List<Sale> getSalesBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
	        return saleDAO.findBySaleDateBetween(startDate, endDate);
	    }

}
