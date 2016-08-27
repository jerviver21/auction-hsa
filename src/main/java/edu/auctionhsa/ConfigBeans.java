package edu.auctionhsa;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;


@SpringBootApplication
public class ConfigBeans {
	
	/*
 		Este bean de configuración PERSONALIZA el mapeo entre objetos y json que realiza Spring MVC
 		en este caso fue necesario puesto que al usar entidades con asociaciones Lazy, se presentaba 
 		LazyInitializedException en vista de que cuando se tiene un Entity, en realidad se tiene un proxy
 		y al Jackson intentar hacer un get de un atributo lazy loaded, se lanzaba la Excepción. Para esto se 
 		usa la dependencia jackson-datatype-hibernate4 (configurada en build.gradle)
	 */
	@Bean
    public MappingJackson2HttpMessageConverter jackson2Converter() {
		ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);    
        Hibernate4Module hibernateModule = new Hibernate4Module();
        hibernateModule.configure(Hibernate4Module.Feature.FORCE_LAZY_LOADING, false);          
        objectMapper.registerModule(hibernateModule);  
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);          
        return converter;
    } 

}
