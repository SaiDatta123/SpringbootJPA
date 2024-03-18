package com.wipro.repository;

import com.wipro.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public Optional<Employee> findByWorkLocation(String workLocation);
    public Optional<Employee> findByFirstName(String firstName);
}
