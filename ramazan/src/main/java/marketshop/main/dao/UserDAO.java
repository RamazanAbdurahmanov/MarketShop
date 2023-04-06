package marketshop.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import marketshop.main.entity.User;



public interface UserDAO extends JpaRepository<User, String>{

}
