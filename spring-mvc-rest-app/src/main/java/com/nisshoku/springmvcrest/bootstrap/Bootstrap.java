package com.nisshoku.springmvcrest.bootstrap;

import com.nisshoku.springmvcrest.domain.Category;
import com.nisshoku.springmvcrest.domain.Customer;
import com.nisshoku.springmvcrest.domain.Vendor;
import com.nisshoku.springmvcrest.repositories.CategoryRepository;
import com.nisshoku.springmvcrest.repositories.CustomerRepository;
import com.nisshoku.springmvcrest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository,
                     VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    // Good upgrade would bo create new method for every load data
    @Override
    public void run(String... args) {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadCustomers() {
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

    private void loadCategories() {
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
    }

    private void loadVendors() {

        Vendor vendor1 = new Vendor();
        vendor1.setName("Some Name");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Some other name");

        Vendor vendor3 = new Vendor();
        vendor3.setName("Rys");

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);
        vendorRepository.save(vendor3);

        System.out.println("Vendor Data Loaded = " + vendorRepository.count());
    }
}
