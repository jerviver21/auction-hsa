package edu.auctionhsa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@SpringBootApplication
public class Application extends WebMvcAutoConfiguration{

    public static void main(String[] args) throws Exception {
    	System.out.println("...");
        SpringApplication.run(Application.class, args);
    }


}

