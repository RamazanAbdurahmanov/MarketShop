package marketshop.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import marketshop.main.entity.Receipt;

public interface ReceiptDAO extends JpaRepository<Receipt, Integer> {

}
