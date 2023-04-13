package marketshop.main.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import marketshop.main.dao.ProductDAO;
import marketshop.main.dao.SaleDAO;
import marketshop.main.entity.Product;
import marketshop.main.entity.Sale;

@Service
public class SaleService {
	@Autowired
	private SaleDAO saleDAO;
	@Autowired
	private ProductDAO productDAO;

	public List<Sale> getSalesByDateRange(Date startDate, Date endDate) {
		return saleDAO.findAllBySaleDateBetween(startDate, endDate);
	}

	private Map<Integer, Integer> cart = new HashMap<>(); // səbətə məhsulların id və sayını saxlayacaq map

	public void addToCart(Integer productId, Integer quantity) {
		Product product = productDAO.findById(productId).orElse(null);
		if (product != null && product.getQuantity() >= quantity) { // məhsul var və sərf ediləcək say sərhəddən azdırsa
			Integer existingQuantity = cart.get(productId); // əgər bu məhsulun səbətdə olduğunu yoxlayaq
			if (existingQuantity != null) {
				quantity += existingQuantity; // səbətdə varsa sayını artırırıq
			}
			cart.put(productId, quantity); // yeni məhsulu və ya artırılmış sayını səbətə əlavə edirik
		}
	}

	public void removeFromCart(Integer productId, Integer quantity) {
		Integer existingQuantity = cart.get(productId);
		if (existingQuantity != null) {
			existingQuantity -= quantity;
			if (existingQuantity > 0) {
				cart.put(productId, existingQuantity);
			} else {
				cart.remove(productId);
			}
		}
	}

	public List<Product> viewCart() {
		List<Product> productsInCart = new ArrayList<>();
		for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
			Integer productId = entry.getKey();
			Integer quantity = entry.getValue();
			Product product = productDAO.findById(productId).orElse(null);
			if (product != null) {
				product.setQuantity(quantity); // məhsulun səbətdəki sayını məhsul obyektində saxlayırıq
				productsInCart.add(product);
			}
		}
		return productsInCart;
	}

	public void checkout() {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();

		for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
			Integer productId = entry.getKey();
			Integer quantity = entry.getValue();
			Product product = productDAO.findById(productId).orElse(null);
			if (product != null && product.getQuantity() >= quantity) {
				product.setQuantity(product.getQuantity() - quantity);
				productDAO.save(product);

				Sale sale = new Sale();
				sale.setSaleDate(LocalDate.now());
				sale.setCashier(username);
				sale.setProduct(product);
				sale.setQuantity(quantity);
				sale.setTotalPrice(product.getPrice() * quantity);
				saleDAO.save(sale);
			}
		}
		cart.clear();
	}

}
