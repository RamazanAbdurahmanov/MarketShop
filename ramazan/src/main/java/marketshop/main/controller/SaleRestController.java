package marketshop.main.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import marketshop.main.dao.SaleDAO;
import marketshop.main.entity.Product;
import marketshop.main.entity.Sale;
import marketshop.main.service.SaleService;

@RestController
@RequestMapping(path = "/sales")
public class SaleRestController {
	@Autowired
	SaleDAO saleDAO;

	@Autowired
	SaleService saleService;

	// qeyd olunan tarix araliginda edilen satishlari qaytarir
	@GetMapping
	public List<Sale> getSalesByDateRange(
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
		Date start = Date.valueOf(startDate);
		Date end = Date.valueOf(endDate);
		return saleService.getSalesByDateRange(start, end);
	}

	@PostMapping("/add-to-cart")
	public ResponseEntity<Void> addToCart(@RequestParam Integer productId, @RequestParam Integer quantity) {
		saleService.addToCart(productId, quantity);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/remove-from-cart")
	public ResponseEntity<Void> removeFromCart(@RequestParam Integer productId, @RequestParam Integer quantity) {
		saleService.removeFromCart(productId, quantity);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/view")
	public ResponseEntity<List<Product>> viewCart() {
		List<Product> productsInCart = saleService.viewCart();
		return ResponseEntity.ok().body(productsInCart);
	}

	@PostMapping("/checkout")
	public ResponseEntity<Void> checkout() {
		saleService.checkout();
		return ResponseEntity.ok().build();
	}
}