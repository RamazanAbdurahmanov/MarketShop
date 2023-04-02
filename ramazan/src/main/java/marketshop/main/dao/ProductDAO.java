package marketshop.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import marketshop.main.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {

}
