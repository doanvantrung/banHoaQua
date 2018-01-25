package application;

import application.data.service.ProductService;
import application.data.service.ProductServiceIpml;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class Application {
    @Bean
    public ProductService getProductService(){
        return new ProductService();
    }
    @Bean
    public ProductServiceIpml getProductServiceIpml(){
        return new ProductServiceIpml();
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class , args);
    }
}

