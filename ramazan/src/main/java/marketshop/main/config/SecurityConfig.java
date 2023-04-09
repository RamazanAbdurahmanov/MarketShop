package marketshop.main.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import marketshop.main.entity.Authority;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
	DataSource dataSource;
    @Autowired
    Authority authority;
    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/**").hasRole("ADMIN")
				.anyRequest().authenticated().and().httpBasic();
		        http.headers().frameOptions().disable();
                http.formLogin(); 

	} 

    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
auth.inMemoryAuthentication()
.withUser("admin")
       .password("{noop}admin")
     	.roles("ADMIN").and()
	    .withUser("Cashier")
	    .password("{noop}12") 
       .roles("CASHIER");
		
		auth.jdbcAuthentication().dataSource(dataSource);
		
	}

	
 
	
	
}
