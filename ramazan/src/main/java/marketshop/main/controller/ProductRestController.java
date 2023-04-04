package marketshop.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import marketshop.main.dao.ProductDAO;
import marketshop.main.entity.Product;

@RestController
@RequestMapping(path = "/products")
public class ProductRestController {

	@Autowired
	private ProductDAO productDAO;

	// product model entity sinifindekileri butun hamisini qaytarir
	@GetMapping
	public List<Product> getAllProducts() {
		return productDAO.findAll();
	}
      
	// yeni mehsul qeydiyyat edir(save edir)
	@PostMapping(path = "/register-new-product")
	public Product newProduct(@RequestBody Product product) {
		return productDAO.save(product);
	}
    
	//id-sine gore product model entity sinifindeki melumatlari qaytarir
	@GetMapping("/{id}")
	public Product getById(@PathVariable Integer id) {
		return productDAO.findById(id).get();
	}
     
	//ide gore product model entity sinifin ichindekileri update edir
	@PutMapping("/{id}")
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
	@GetMapping(path = "/delete/{id}")
	public void deleteProductById(@PathVariable(name = "id") Integer id) {
		boolean productExists = productDAO.findById(id).isPresent();
		if (productExists) { 
			productDAO.deleteById(id);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

}
