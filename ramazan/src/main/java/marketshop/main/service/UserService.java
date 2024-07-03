package marketshop.main.service;

import java.util.List;
import java.util.Optional;

import marketshop.main.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public User cashierRegistiration(User user) {
    Optional<User> userOptional=userDAO.findById(user.getUsername());
    if(userOptional.isPresent()) {
        user.setUsername("");
        return user;
    } else {
       
    } 
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    user.setPassword("{bcrypt}"+encoder.encode(user.getPassword())) ;
    user.setEnabled(true);
    authority.setUsername(user.getUsername()); 
    authority.setAuthority("CASHIER");
    authorityDAO.save(authority);
    return userDAO.save(user);
}
	//kassiri silir
public void deleteCashierByUsername(String username) {
	boolean cashierExists=userDAO.findById(username).isPresent();
	if(cashierExists) {
		userDAO.deleteById(username);
	}else {
		throw new UserNotFoundException("Istifadeci tapilmadi");
	}
}

// butun kassirleri qaytarir
 public List<User> findAll() {
 return userDAO.findAll();
}

 //aktiv olan useri deaktiv edir
 public void deactivateUser(String username) {
	 Optional<User> user = userDAO.findById(username);
	   
	   if(user.isPresent()) {
	      if(user.get().getEnabled() == true) {
	         user.get().setEnabled(false);
	         userDAO.save(user.get());
	      }
	   } else {
		   throw new UserNotFoundException("Istifadeci tapilmadi");
	   }
 }
 //deaktiv olan useri aktiv edir
 public void activateUser(String username) {
	 Optional<User> user = userDAO.findById(username);
	   
	   if(user.isPresent()) {
	      if(user.get().getEnabled() == false) {
	         user.get().setEnabled(true);
	         userDAO.save(user.get());
	      }
	   } else {
		   throw new UserNotFoundException("Istifadeci tapilmadi");
	   }
}
}