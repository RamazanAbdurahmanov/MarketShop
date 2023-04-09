package marketshop.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import marketshop.main.dao.SaleDAO;
import marketshop.main.entity.Sale;
import marketshop.main.model.FindBetweenDateModel;
import marketshop.main.service.SaleService;
@RestController
@RequestMapping(path="/sales")
public class SaleRestController {
	@Autowired
	SaleDAO saleDAO;
	
	@Autowired 
	SaleService saleService;
	
	// qeyd olunan tarix araliginda edilen satishlari qaytarir
	@GetMapping(path="/between-dates")
	public List<Sale>getSalesBetweenDates(@RequestBody FindBetweenDateModel date){
		return saleService.getSalesBetweenDates(date);
	}
	
	
	@PostMapping("/add-sale")
    public Sale addSale(@RequestBody Sale sale) {
        return saleService.addSale(sale);
    }
}
