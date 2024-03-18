package com.wipro.service;

import com.wipro.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee createEmployee(Employee employee);
    List<Employee> getAll();
    Optional<Employee> getById(int employeeId);
    Optional<Employee> getByWorkLocation(String workLocation);
    Optional<Employee> getByFirstName(String firstName);
    Employee updateEmployee(Employee emp);
    void deleteEmployee(int employeeId);

}

