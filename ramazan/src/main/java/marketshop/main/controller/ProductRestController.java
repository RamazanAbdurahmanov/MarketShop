package marketshop.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import marketshop.main.entity.Product;
import marketshop.main.service.ProductService;

@RestController
@RequestMapping(path = "/products")
public class ProductRestController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public List<Product> getAllProducts() {
		return productService.findAll();
	}

	@PostMapping(path = "/register-new-product")
	public Product newProduct(@RequestBody Product product) {
		return productService.save(product);
	}

	@GetMapping("/{id}")
	public Product getById(@PathVariable Integer id) {
		return productService.findById(id);
	}

	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable Integer id, @RequestBody Product updatedProduct) {
		return productService.updateProduct(id, updatedProduct);
	}

	@GetMapping(path = "/delete/{id}")
	public void deleteById(@PathVariable Integer productId) {
		productService.deleteById(productId);
	}

//	@GetMapping(path = "/{barcode}")
//	public Product getProductByBareCode(@PathVariable String barcode, Product product) {
//		return productService.findByBarcode(barcode);
//	}
}
