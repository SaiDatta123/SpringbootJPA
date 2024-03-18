package com.wipro.controller;

import com.wipro.exceptions.CustomBadRequestException;
import com.wipro.exceptions.ResourceNotFoundException;
import com.wipro.model.Employee;
import com.wipro.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/employees/")
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody @Validated Employee employee) {
        log.info("Creating an Employee.");
        Employee savedEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllUsers() {
        log.info("Fetching all the users from database.");
        List<Employee> employeeList = employeeService.getAll();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int employeeId) {
        log.info("Fetching user details from database for the given id : {}",employeeId );
        Optional<Employee> optionalEmployee = employeeService.getById(employeeId);
        Employee employee = optionalEmployee.
                orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + employeeId ));

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/name/{byName}")
    public ResponseEntity<Employee> getEmployeeByName(@PathVariable("byName") String firstName) {
        log.info("Fetching user details from database for the given first name : {}", firstName );
        Optional<Employee> optionalEmployee = employeeService.getByFirstName(firstName);
        Employee employee = optionalEmployee.
                orElseThrow(() -> new ResourceNotFoundException("Employee not found by name : " + firstName));

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/location/{byWorkLocation}")
    public ResponseEntity<Employee> getEmployeeByWorkLocation(@PathVariable("byWorkLocation") String workLocation) {
        log.info("Fetching user details from database for the given work location : {}", workLocation );
        Optional<Employee> optionalEmployee = employeeService.getByWorkLocation(workLocation);
        Employee employee = optionalEmployee.
                orElseThrow(() -> new ResourceNotFoundException("Employee not found by work location : " + workLocation));

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@Validated @RequestBody Employee employee) {
        log.info("Updating user details.");
        Employee updatedEmployee = null;
        try {
            int empId = employee.getId();
            if (empId < 1) {
                throw new CustomBadRequestException("Invalid Id.ID must be not null and positive integer");
            }
            updatedEmployee = employeeService.updateEmployee(employee);
        } catch (Exception e) {
            log.error("Error came while updating employee object \n {}", e.getMessage());
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int employeeId) {
        log.info("Deleting the user for the given id : {} ", employeeId);
        if (employeeId < 1) {
            throw new CustomBadRequestException("Invalid Id.ID must be not null and positive integer");
        }

        Optional<Employee> optionalEmployee = employeeService.getById(employeeId);
        Employee employee = optionalEmployee.
                orElseThrow(() -> new ResourceNotFoundException("Employee not found with id :" + employeeId));

        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee successfully deleted!", HttpStatus.OK);
    }

}


