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
@RequestMapping(path="/users")
@CrossOrigin(origins="*")
public class UserRestController {
	
	@Autowired
	UserService userService;
	
	
	@PostMapping(path="/register-new-cashier")
	public User registerNewCashier(@RequestBody User user) {
		return userService.cashierRegistiration(user);
			
	}
	@GetMapping(path="/{id}")
	public void deleteCashier(@PathVariable String Id) {
		userService.deleteCashierById(Id);
	}
  @PostMapping(path="/deactivate-cashier")
   public void deactivateYourCashier(@RequestBody User user) {
	  userService.deactivateCashier(user);
	  
  }
  @PostMapping(path="/activate-cashier")
  public void activeYourCashier(@RequestBody User user) {
	  userService.activateCashier(user);
	  	  
 }
 
  @GetMapping
  public List<User> getAllCashiers(){
	  return userService.findAll();
  }
  
}
