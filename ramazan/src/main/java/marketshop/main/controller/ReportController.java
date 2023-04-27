package marketshop.main.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import marketshop.main.dao.ReceiptDAO;
import marketshop.main.entity.Sale;
import marketshop.main.service.ReportService;

@RestController
@RequestMapping(path = "/reports")
public class ReportController {
	
	@Autowired
	ReceiptDAO receiptDAO;
	@Autowired
	ReportService reportService;
	
	
	
	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@GetMapping("/between-dates")
	public List<Sale> getSalesBetweenDates(
			@RequestParam("start-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
			@RequestParam("end-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
		return reportService.getSalesBetweenDates(startDate, endDate);
	}
	
	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@GetMapping("/bestcashier")
	public ResponseEntity<Object> getBestCashier() {
		return reportService.getBestCashier();
	}
	
	 @GetMapping("/best-selling-product")
	    public ResponseEntity<Object[]> getBestSellingProduct() {
	        List<Object[]> result = reportService.findBestSellingProduct();
	        if (result.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>(result.get(0), HttpStatus.OK);
	    }
	    @GetMapping("/top3-selling-product")
	    public ResponseEntity<Object> getTop3SellingProduct() {
	    	List<Object> result = reportService.findTop3SellingProduct();
	    	if (result.isEmpty()) {
	    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	}
	    	return new ResponseEntity<>(result,HttpStatus.OK);
	    }
	

	
}
