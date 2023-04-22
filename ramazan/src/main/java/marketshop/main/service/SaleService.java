package marketshop.main.service;

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
import marketshop.main.dao.ReceiptDAO;
import marketshop.main.dao.SaleDAO;
import marketshop.main.entity.Product;
import marketshop.main.entity.Receipt;
import marketshop.main.entity.Sale;

@Service
public class SaleService {
	@Autowired
	private SaleDAO saleDAO;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	ReceiptDAO receiptDAO;

	public List<Sale> getSalesBetweenDates(LocalDate startDate, LocalDate endDate) {
		return saleDAO.findBySaleDateBetween(startDate, endDate);
	}

	private Map<String, Integer> cart = new HashMap<>(); // səbətə məhsulların id və sayını saxlayacaq map

	public void addToCart(String barcode, Integer quantity) {
		Product product = getProductByBarcode(barcode);
		if (product != null && product.getQuantity() >= quantity) { // məhsul var və sərf ediləcək say sərhəddən azdırsa
			Integer existingQuantity = cart.get(barcode); // əgər bu məhsulun səbətdə olduğunu yoxlayaq
			if (existingQuantity != null) {
				quantity += existingQuantity; // səbətdə varsa sayını artırırıq
			}
			cart.put(barcode, quantity); // yeni məhsulu və ya artırılmış sayını səbətə əlavə edirik
		}
	}

	public void removeFromCart(String barcode, Integer quantity) {
		Integer existingQuantity = cart.get(barcode);
		if (existingQuantity != null) {
			existingQuantity -= quantity;
			if (existingQuantity > 0) {
				cart.put(barcode, existingQuantity);
			} else {
				cart.remove(barcode);
			}
		}
	}

	public List<Product> viewCart() {
		List<Product> productsInCart = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : cart.entrySet()) {
			String barcode = entry.getKey();
			Integer quantity = entry.getValue();
			Product product = getProductByBarcode(barcode);
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
		LocalDate ld = LocalDate.now();
		Receipt receipt = new Receipt();
		List<Sale> sales = new ArrayList<>();

		double totalPrice = 0;
		int soldProductCount = 0;
		List<String> names = new ArrayList<>();

		for (Map.Entry<String, Integer> entry : cart.entrySet()) {
			String barcode = entry.getKey();
			Integer quantity = entry.getValue();
			Product product = getProductByBarcode(barcode);
			if (product != null && product.getQuantity() >= quantity) {
				product.setQuantity(product.getQuantity() - quantity);
				productDAO.save(product);

				Sale sale = new Sale();
				sale.setSaleDate(ld);
				sale.setCashier(username);
				sale.setProduct(product);
				sale.setQuantity(quantity);
				sale.setBarcode(product.getBarcode());
				sale.setTotalPrice(product.getPrice() * quantity);
				sale.setProductName(product.getName());
				names.add(sale.getProductName());
				saleDAO.save(sale);

				sales.add(sale);
				totalPrice += sale.getTotalPrice();
				soldProductCount += sale.getQuantity();
				receipt.setSaleDate(ld);
				receipt.setCashier(username);
				String productName = product.getName();

				if (receipt.getProductName() == null) {
					receipt.setProductName(productName);
				} else {
					receipt.setProductName(receipt.getProductName() + ": x" + receipt.getProductCount() + ", "
							+ productName + ": x" + quantity);
				}

				receipt.setTotalAmount(totalPrice);
				receipt.setSales(sales);
				receipt.setProductCount(soldProductCount);
				receiptDAO.save(receipt);

			}
		}
		cart.clear();
	}

	public void returnProduct(String barcode, Integer quantity) {
		Product product = getProductByBarcode(barcode);
		product.setQuantity(product.getQuantity() + quantity);
		
		productDAO.save(product);

	}

	private Product getProductByBarcode(String barcode) {
		List<Product> productList = productDAO.findByBarcode(barcode);
		if (!productList.isEmpty()) {
			return productList.get(0);

		}
		return null;
	}
}
