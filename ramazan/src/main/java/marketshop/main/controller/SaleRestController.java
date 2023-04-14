package marketshop.main.controller;

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
//	@GetMapping
//	public List<Sale> getSalesByDateRange(
//			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime startDate,
//			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime endDate) {
//		LocalDateTime start = LocalDateTime.from(startDate);
//		LocalDateTime end = LocalDateTime.from(endDate);
//		return saleService.getSalesByDateRange(start, end);
//	}

	@GetMapping("/between-dates")
	public List<Sale> getSalesBetweenDates(
			@RequestParam("start-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
			@RequestParam("end-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
		return saleService.getSalesBetweenDates(startDate, endDate);
	}

	@PostMapping("/add-to-cart")
	public ResponseEntity<Void> addToCart(@RequestParam String barcode, @RequestParam Integer quantity) {
		saleService.addToCart(barcode, quantity);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/remove-from-cart")
	public ResponseEntity<Void> removeFromCart(@RequestParam String barcode, @RequestParam Integer quantity) {
		saleService.removeFromCart(barcode, quantity);
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