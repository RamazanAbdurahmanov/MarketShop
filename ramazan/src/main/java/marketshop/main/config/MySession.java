package marketshop.main.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class MySession {
	
	public MySession() {
		System.out.println("My Session > constructor");
		Authentication loggedInUser=SecurityContextHolder.getContext().getAuthentication();
		String username=loggedInUser.getName();
	}

}
