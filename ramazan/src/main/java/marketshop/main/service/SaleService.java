package marketshop.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import marketshop.main.dao.ProductDAO;
import marketshop.main.dao.SaleDAO;
import marketshop.main.entity.Product;
import marketshop.main.entity.Sale;
import marketshop.main.model.FindBetweenDateModel;
@Service
public class SaleService {
	 @Autowired
	    private SaleDAO saleDAO;
	 @Autowired
	 private ProductDAO productDAO;
	
	 

	    public List<Sale> getSalesBetweenDates(@RequestBody FindBetweenDateModel date) {
	        return saleDAO.findByBetweenSaleDate(date.getStartDate(),date.getEndDate());
	    }
	    
	    


	    public Sale addSale(Sale sale) {
	    	Product product = getProductByBarcode(sale.getProduct().getBarcode());
	    	if(product != null && product.getQuantity() >= sale.getQuantity()) {
	    		product.setQuantity(product.getQuantity() - sale.getQuantity());
	    		productDAO.save(product);
	    		sale.setTotalPrice(sale.getQuantity() * product.getPrice());
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
