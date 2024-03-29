package marketshop.main.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource dataSource;

	
@Override
protected void configure(HttpSecurity http)throws Exception{
	http.csrf().disable().authorizeRequests()
	.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
	.antMatchers(HttpMethod.POST,"/users/**").permitAll()
	.antMatchers("/h2-console/**").permitAll()
	.anyRequest().authenticated().and().httpBasic();
	http.headers().frameOptions().disable();
	http.formLogin().permitAll();
	
}
@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}
}

