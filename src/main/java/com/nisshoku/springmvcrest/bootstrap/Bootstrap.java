package com.nisshoku.springmvcrest.bootstrap;

import com.nisshoku.springmvcrest.domain.Category;
import com.nisshoku.springmvcrest.domain.Customer;
import com.nisshoku.springmvcrest.repositories.CategoryRepository;
import com.nisshoku.springmvcrest.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    // Good upgrade would bo create new method for every load data
    @Override
    public void run(String... args) {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Category Data Loaded = " + categoryRepository.count());

        Customer customer1 = new Customer();
        customer1.setFirstName("Taka");
        customer1.setLastName("Hayami");

        Customer customer2 = new Customer();
        customer2.setFirstName("Ryuki");
        customer2.setLastName("Rafa");

        Customer customer3 = new Customer();
        customer3.setFirstName("Ayumi");
        customer3.setLastName("Hayami");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);

        System.out.println("Customer Data Loaded = " + customerRepository.count());

    }
}
