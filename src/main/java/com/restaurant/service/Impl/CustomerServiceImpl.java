package com.restaurant.service.Impl;

import com.restaurant.entity.Customer;
import com.restaurant.repository.CustomerRepository;
import com.restaurant.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    // Add a customer
    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Update customer
    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Delete customer
    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    // Get all customers
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get by ID
    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Search by name
    @Override
    public List<Customer> searchCustomersByName(String name) {
        return customerRepository.findByNameContainingIgnoreCase(name);
    }
}
