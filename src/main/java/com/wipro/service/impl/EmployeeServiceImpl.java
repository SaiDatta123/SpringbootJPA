package com.wipro.service.impl;

import com.wipro.exceptions.ResourceNotFoundException;
import com.wipro.model.Employee;
import com.wipro.repository.EmployeeRepository;
import com.wipro.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    EmployeeRepository repository;

    @Override
    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Employee> getById(int employeeId) {
        return repository.findById(employeeId);
    }

    @Override
    public Optional<Employee> getByFirstName(String firstName) {
        return repository.findByFirstName(firstName);
    }

    @Override
    public Optional<Employee> getByWorkLocation(String workLocation) {
        return repository.findByWorkLocation(workLocation);
    }

    @Override
    public Employee updateEmployee(Employee emp) {
        Optional<Employee> existingEmployee = repository.findById(emp.getId());
        if (existingEmployee.isPresent()) {
            log.info("Updating employee for given id : {} ", existingEmployee.get().getId());
            existingEmployee.get().setDepartment(emp.getDepartment());
            existingEmployee.get().setFirstName(emp.getFirstName());
            existingEmployee.get().setWorkLocation(emp.getWorkLocation());
            repository.save(existingEmployee.get());
            return existingEmployee.get();
        }else{
            throw new ResourceNotFoundException("Employee not found with id :"+ emp.getId());
        }
    }

    @Override
    public void deleteEmployee(int employeeId) {
        repository.deleteById(employeeId);
    }
}
