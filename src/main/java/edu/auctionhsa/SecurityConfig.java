package edu.auctionhsa;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@SpringBootApplication
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	/*Spring Security permite controlar a que puedo acceder y a que no dependiendo de la autenticación del usuario
	 * /login permite conectarse con la página de facebook para autenticacion
	 * index.html, es la plantilla de angularJS (El flujo visual de permitido o no es controlado por angular)
	 * el resto es acceso a js, css, images, etc
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.antMatcher("/**")
	    .authorizeRequests()   
	    .antMatchers("/css/**", 
	    		"/fonts/**", 
	    		"/images/**", 
	    		"/js/**",
	    		"/view/index.html",
	    		"/view/main.html",
	    		"/view/login.html",
	    		"/login",
	    		"/auctions",
	    		"/auctions/*",
	    		"/").permitAll()
	    .anyRequest().authenticated()
	    .and().logout().logoutSuccessUrl("/").permitAll();;
	}

}
