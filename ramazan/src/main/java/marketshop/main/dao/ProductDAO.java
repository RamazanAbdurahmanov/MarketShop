package marketshop.main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import marketshop.main.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
	//prductun butun deyishenlerine gore axtarish edir
	@Query(value = "select*from product where name like %?1% or barcode like %?1% or price like %?1% or cost like %?1% or description like %?1% or quantity like %?1%  or percent like %?1% ",nativeQuery = true)
	public List<Product> findAllSearchAllFields(String search);
   
	//barcoda gore mehsulu qaytarir
	@Query(value="select*from product where barcode =?",nativeQuery = true)
	public List<Product> findByBarcode(String barcode);
	
	
	

}

