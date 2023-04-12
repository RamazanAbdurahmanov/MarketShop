package marketshop.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import marketshop.main.entity.User;
import marketshop.main.service.UserService;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin(origins = "*")
public class UserRestController {

	@Autowired
	UserService userService;

	@PostMapping(path = "/register-new-cashier")
	public User registerNewCashier(@RequestBody User user) {
		return userService.cashierRegistiration(user);

	}

	@GetMapping(path = "/delete-cashier/{username}")
	public void deleteCashier(@PathVariable String username) {
		userService.deleteCashierByUsername(username);
	}

	@GetMapping(path = "/get-all-cashier")
	public List<User> getAllCashiers() {
		return userService.findAll();
	}
     @PostMapping(path="/deactivate-user/{id}")
	 public void deactivateUser(@PathVariable String id) {
		    userService.deactivateUser(id);
		 }
	 
     @PostMapping(path="/activate-user/{id}")
	 public void activateUser(@PathVariable String id) {
		    userService.activateUser(id);
		 }
	
}
