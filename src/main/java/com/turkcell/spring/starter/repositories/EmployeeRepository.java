package com.turkcell.spring.starter.repositories;

import com.turkcell.spring.starter.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
