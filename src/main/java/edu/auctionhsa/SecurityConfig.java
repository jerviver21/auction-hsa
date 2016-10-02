package edu.auctionhsa;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.filter.CompositeFilter;


/**
 * Oauth2 es una especificación estándar que permite implementar la seguridad de servicios REST a través de un token, esta especificación 
 * ha sido adoptada por todos o la gran mayoría de los gigantes tecnológicos.
 * Tiene 3 actores el cliente, el recurso y el servidor de autorización. en el que basicamente el servidor de autorización dará un token al cliente para que acceda al recurso
 * Flujo:
 * Recurso:  (/oauth/autorize) GET  user-authorization-uri (cliente-id, redirect-url)
 *                                                                                                    Auth Server: retorna un code y un state
 * Recurso:  (oauth/token) POST access-token-uri (client-id, client-secret, code, state, redirect-url)
 *                                                                                                    Auth Server: retorna el token
 * Una vez el recurso obtiene el access token, puede consultar un API en el authorization server, pasando como parametro el token.
 * Un scope (oauth2) define que tipo de recursos puede consultar en el api
 * 
 * Por ahora se trabajará solamente clientes github y facebook, quedará pendiente implementar el authorization server, para que autorice después de
 * proveidas las credenciales.
 * 
 * doc: http://projects.spring.io/spring-security-oauth/docs/oauth2.html
 */

@SpringBootApplication
@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	OAuth2ClientContext oauth2ClientContext;
	
	
	/*Spring Security permite controlar a que puedo acceder y a que no dependiendo de la autenticación del usuario
	 * /login permite conectarse con la página de facebook para autenticacion
	 * index.html, es la plantilla de angularJS (El flujo visual de permitido o no es controlado por angular)
	 * el resto es acceso a js, css, images, etc
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.antMatcher("/**")
	    .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)
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
	    .and().logout().logoutSuccessUrl("/").permitAll().and().csrf()
		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());;
	}
	
	
	
	private Filter ssoFilter() {

	  CompositeFilter filter = new CompositeFilter();
	  List<Filter> filters = new ArrayList<>();

	  OAuth2ClientAuthenticationProcessingFilter facebookFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/facebook");
	  OAuth2RestTemplate facebookTemplate = new OAuth2RestTemplate(facebook(), oauth2ClientContext);
	  facebookFilter.setRestTemplate(facebookTemplate);
	  facebookFilter.setTokenServices(new UserInfoTokenServices(facebookResource().getUserInfoUri(), facebook().getClientId()));
	  filters.add(facebookFilter);

	  OAuth2ClientAuthenticationProcessingFilter githubFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/github");
	  OAuth2RestTemplate githubTemplate = new OAuth2RestTemplate(github(), oauth2ClientContext);
	  githubFilter.setRestTemplate(githubTemplate);
	  githubFilter.setTokenServices(new UserInfoTokenServices(githubResource().getUserInfoUri(), github().getClientId()));
	  filters.add(githubFilter);

	  filter.setFilters(filters);
	  return filter;

	}
	
	@Bean
	@ConfigurationProperties("facebook.client")
	public AuthorizationCodeResourceDetails facebook() {
	  return new AuthorizationCodeResourceDetails();
	}
	
	@Bean
	@ConfigurationProperties("facebook.resource")
	public ResourceServerProperties facebookResource() {
	  return new ResourceServerProperties();
	}
	
	@Bean
	@ConfigurationProperties("github.client")
	public AuthorizationCodeResourceDetails github() {
		return new AuthorizationCodeResourceDetails();
	}

	@Bean
	@ConfigurationProperties("github.resource")
	public ResourceServerProperties githubResource() {
		return new ResourceServerProperties();
	}
	
	@Bean
	public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
	  FilterRegistrationBean registration = new FilterRegistrationBean();
	  registration.setFilter(filter);
	  registration.setOrder(-100);
	  return registration;
	}
	

}
