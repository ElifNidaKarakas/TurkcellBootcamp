package com.turkcell.spring.starter.repositories;

import com.turkcell.spring.starter.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
