package com.example.accessingdatamongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("customerService")
public class CustomerService {

    @Autowired
    @Qualifier("customerRepository") // inject Spring implementation here
    private CustomerRepository customerRepository;

    public void deleteAll(){
        customerRepository.deleteAll();
    }
    public void deleteById(String s) {
        Customer customer =  customerRepository.findOne(s);
        customer.isDeleted=true;
        customer.deletedDate=new Date();
        customerRepository.save(customer);
    }

    public void save(Customer customer){
        customerRepository.save(customer);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll().stream().filter(customer ->
                !customer.isDeleted).collect(Collectors.toList());
    }


    public List<Customer> findAllAlongWithDeleted() {
        return customerRepository.findAll();
    }


}
