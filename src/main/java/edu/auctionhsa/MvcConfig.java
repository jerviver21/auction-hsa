package edu.auctionhsa;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{
	
	@Value("${images.dir}")
	public String RELATIVE_ITEMS_IMAGES;
	
	@Value("${images.path}")
	public String PATH_ITEMS_IMAGES;
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(RELATIVE_ITEMS_IMAGES+"**")
        //.addResourceLocations("file:ext-resources/")
        .addResourceLocations("file://"+PATH_ITEMS_IMAGES+File.separator)
        .setCachePeriod(0);
    }
	
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("view/index.html");
    }

}
