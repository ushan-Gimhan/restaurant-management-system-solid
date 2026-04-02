package com.restaurant.service;

import com.restaurant.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    // Add a customer
    Customer addCustomer(Customer customer);

    // Update customer
    Customer updateCustomer(Customer customer);

    // Delete customer
    void deleteCustomer(Long id);

    // Get all customers
    List<Customer> getAllCustomers();

    // Get by ID
    Optional<Customer> getCustomerById(Long id);

    // Search by name
    List<Customer> searchCustomersByName(String name);
}
