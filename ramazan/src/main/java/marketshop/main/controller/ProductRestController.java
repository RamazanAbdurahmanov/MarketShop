package marketshop.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import marketshop.main.entity.Product;
import marketshop.main.model.SearchBarcode;
import marketshop.main.model.SearchModel;
import marketshop.main.service.ProductService;

@RestController
@RequestMapping(path = "/products")
public class ProductRestController {

	@Autowired
	private ProductService productService;
	@Autowired
     
	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@GetMapping(path="/get-all-products")
	public List<Product> getAllProducts() {
		return productService.findAll();
	}
    
	
	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@PostMapping(path = "/register-new-product")
	public Product newProduct(@RequestBody Product product) {
		return productService.save(product); 
	}

	
	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@PutMapping("/update-product/{id}")
	public Product updateProduct(@PathVariable Integer id, @RequestBody Product updatedProduct) {
		return productService.updateProduct(id, updatedProduct);
	}
	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@DeleteMapping(path = "/delete-product/{id}")
	public void deleteById(@PathVariable Integer  id ) {
		productService.deleteById(id); 
	}
	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@PostMapping(path="/search")
    public List<Product> searchProducts(@RequestBody SearchModel search) {
        return productService.findAllSearch(search);
    }
	@PreAuthorize(value = "hasAnyAuthority('ADMIN','CASHIER')")
	@PostMapping(path="/search-barcode")
    public List<Product> searchProductByBarcode(@RequestBody SearchBarcode search) {
        return productService.findByBarcodeSearch(search);
    }

}
