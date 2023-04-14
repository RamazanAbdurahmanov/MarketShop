package marketshop.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import marketshop.main.dao.ProductDAO;
import marketshop.main.entity.Product;
import marketshop.main.model.SearchBarcode;
import marketshop.main.model.SearchModel;

@Service
public class ProductService {
	

	@Autowired
	private ProductDAO productDAO;
	
	// product model entity sinifindekileri butun hamisini qaytarir
	public List<Product> findAll() {
		return productDAO.findAll();
	}
	
	
	
	// yeni mehsul qeydiyyat edir(save edir)
	public Product save(@RequestBody Product product) {
		return productDAO.save(product);
	}
	
	//ide gore product model entity sinifin ichindekileri update edir
	public Product updateProduct(@PathVariable Integer id, @RequestBody Product updatedProduct) {
		Optional<Product> optionalProduct = productDAO.findById(id);
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			product.setName(updatedProduct.getName());
			product.setBarcode(updatedProduct.getBarcode());
			product.setPrice(updatedProduct.getPrice());
			product.setCost(updatedProduct.getCost());
			product.setDescription(updatedProduct.getDescription());
			product.setRegisterDate(updatedProduct.getRegisterDate());
			product.setUpdateDate(updatedProduct.getUpdateDate());
			product.setQuantity(updatedProduct.getQuantity());
			product.setPercent(updatedProduct.getPercent());
			return productDAO.save(product);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	// id-sine gore product model entity sinifindeki melumatlari silir
	public void deleteById(@PathVariable(name = "id") Integer id) {
		boolean productExists = productDAO.findById(id).isPresent();
		if (productExists) {
			productDAO.deleteById(id);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}
	//butun deyishenlere gore axtarish edir
    public List <Product> findAllSearch(@RequestBody SearchModel search){
  return productDAO.findAllSearchAllFields(search.getSearch());
 }
    
    public List <Product> findByBarcodeSearch(@RequestBody SearchBarcode search){
    	  return productDAO.findByBarcode(search.getSearchByBarcode());
    	 }
}
