package marketshop.main.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import marketshop.main.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import marketshop.main.dao.ProductDAO;
import marketshop.main.entity.Product;
import marketshop.main.model.SearchBarcode;
import marketshop.main.model.SearchModel;

@Service
public class ProductService {
	

	@Autowired
	private ProductDAO productDAO;
	
	LocalDateTime ldt=LocalDateTime.now();
	
	// product model entity sinifindekileri butun hamisini qaytarir
	public List<Product> findAll() {
		
		return productDAO.findAll();
	}
	
	
	
	// yeni mehsul qeydiyyat edir(save edir)
	public Product save(Product product) {
		product.setPercent((product.getPrice()-product.getCost())/product.getCost()*100);
		product.setRegisterDate(ldt);
		return productDAO.save(product);
	}
	
	//ide gore product model entity sinifin ichindekileri update edir
	public Product updateProduct(Integer id, Product updatedProduct) {
		
		Optional<Product> optionalProduct = productDAO.findById(id);
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			product.setName(updatedProduct.getName());
			product.setBarcode(updatedProduct.getBarcode());
			product.setPrice(updatedProduct.getPrice());
			product.setCost(updatedProduct.getCost());
			product.setDescription(updatedProduct.getDescription());
			product.setRegisterDate(updatedProduct.getRegisterDate());
			product.setUpdateDate(ldt);
			product.setQuantity(updatedProduct.getQuantity());
			return productDAO.save(product);
		} else {
			throw new ProductNotFoundException("Mehsul tapilmadi");
		}
	}

	// id-sine gore product model entity sinifindeki melumatlari silir
	public void deleteById(Integer id) {
		boolean productExists = productDAO.findById(id).isPresent();
		if (productExists) {
			productDAO.deleteById(id);
		} else {
			throw new ProductNotFoundException("Mehsul tapilmadi");
		}

	}
	//butun deyishenlere gore axtarish edir
    public List <Product> findAllSearch(SearchModel search){
  return productDAO.findAllSearchAllFields(search.getSearch());
 }
    
    public List <Product> findByBarcodeSearch(SearchBarcode search){
    	  return productDAO.findByBarcode(search.getSearchByBarcode());
    	 }
}
