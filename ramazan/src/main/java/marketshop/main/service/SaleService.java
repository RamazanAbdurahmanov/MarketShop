package marketshop.main.service;

import java.sql.Date;
import java.util.List;

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

	public Sale addSale(Sale sale) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		Product product = getProductByBarcode(sale.getProduct().getBarcode());
		if (product != null && product.getQuantity() >= sale.getQuantity()) {
			product.setQuantity(product.getQuantity() - sale.getQuantity());
			productDAO.save(product);
			sale.setTotalPrice(sale.getQuantity() * product.getPrice());
			sale.setCashier(username);
			return saleDAO.save(sale);
		}
		return null;
	}

	private Product getProductByBarcode(String barcode) {
		List<Product> productList = productDAO.findByBarcode(barcode);
		if (!productList.isEmpty()) {
			return productList.get(0);
		}
		return null;
	}

}
