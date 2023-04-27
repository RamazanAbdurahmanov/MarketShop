package marketshop.main.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import marketshop.main.dao.ReceiptDAO;
import marketshop.main.dao.SaleDAO;
import marketshop.main.entity.Sale;

@Service
public class ReportService {
	@Autowired
	SaleDAO saleDAO;
	@Autowired
	ReceiptDAO receiptDAO;
	
	// 2 tarix araligindaki satislari qaytarir
	public List<Sale> getSalesBetweenDates(LocalDate startDate, LocalDate endDate) {
		return saleDAO.findBySaleDateBetween(startDate, endDate);
	}
	
	

	//en chox satish eden kassiri tapir
	public ResponseEntity<Object> getBestCashier() {
		List<Object[]> results = receiptDAO.findBestCashier();
		
		if (results.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		HashMap<String, Object> response = new HashMap<>();
		response.put("cashier", results.get(0)[0]);
		response.put("totalProductsSold", results.get(0)[1]);

		return ResponseEntity.ok(response);
	

}
	
	 public List<Object[]> findBestSellingProduct() {
	        return saleDAO.findBestSellingProduct();
	    }
	 
	 public List<Object> findTop3SellingProduct() {
		 return saleDAO.top3SellingProduct();
	 }
}
