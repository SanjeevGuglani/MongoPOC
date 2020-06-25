package com.example.accessingdatamongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccessingDataMongodbApplication implements CommandLineRunner {

	@Autowired
	@Qualifier("customerService")
	private CustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataMongodbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerService.deleteAll();
		// save a couple of customers
		customerService.save(new Customer("Alice", "Smith"));
		customerService.save(new Customer("Bob", "Smith"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		Customer d=null;
		for (Customer customer : customerService.findAll()) {
			System.out.println(customer);
		}
		System.out.println();
		customerService.deleteById("Alice");

		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : customerService.findAll()) {
			System.out.println(customer);
		}

		System.out.println("Customers found with findAllAlongWithDeleted():");
		System.out.println("-------------------------------");
		for (Customer customer : customerService.findAllAlongWithDeleted()) {
			System.out.println(customer);
		}



	}

}
