package marketshop.main.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import marketshop.main.dao.SaleDAO;
import marketshop.main.entity.Sale;

@RestController
@RequestMapping(path="/sales")
public class SaleRestController {
	@Autowired
	SaleDAO saleDAO;
	@Autowired Sale sale;
	// qeyd olunan tarix araliginda edilen satishlari qaytarir
	@GetMapping(path="/between-dates")
	public List<Sale>getSalesBetweenDates(@PathVariable LocalDateTime startDate, LocalDateTime endDate){
		return saleDAO.findBySaleDateBetween(startDate,endDate);
	}
	

}
