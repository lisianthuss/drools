/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package template_springboot_jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import template_springboot_jpa.Customer;
import template_springboot_jpa.CustomerRepository;

@SpringBootApplication
public class App {
    @Autowired
    private static CustomerRepository repository;

    private static final Logger log = LoggerFactory.getLogger(App.class);
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        repository.save(new Customer("Joy","Hello" ));
    }
}
