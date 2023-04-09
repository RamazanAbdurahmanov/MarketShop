package marketshop.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import marketshop.main.dao.AuthorityDAO;
import marketshop.main.dao.UserDAO;
import marketshop.main.entity.Authority;
import marketshop.main.entity.User;

@Service
public class UserService { 
	
@Autowired
private UserDAO userDAO;

@Autowired
private Authority authority;
 

@Autowired
private AuthorityDAO authorityDAO;


//yeni kassir istifadeci yaradir
public User cashierRegistiration(@RequestBody User user) { 
	Optional<User> userOptional=userDAO.findById(user.getUsername());
	if(userOptional.isPresent()) {
		user.setUsername("");
		return user;
	}else {
	user.setPassword("{noop}"+user.getPassword());
	user.setEnabled(true);
	authority.setUsername(user.getUsername()); 
	authority.setAuthority("CASHIER");
	authorityDAO.save(authority);
	return userDAO.save(user);
} 
}
	//kassiri silir
public void deleteCashierByUsername(@PathVariable(name = "username") String username) {
	boolean cashierExists=userDAO.findById(username).isPresent();
	if(cashierExists) {
		userDAO.deleteById(username);
	}else {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
}

// butun kassirleri qaytarir
 public List<User> findAll() {
 return userDAO.findAll();
}


	
}
