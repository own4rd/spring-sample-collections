package br.com.lowlevel.multitenancysample.controller;

import br.com.lowlevel.multitenancysample.entity.Employee;
import br.com.lowlevel.multitenancysample.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping(path = "/employee")
    public ResponseEntity<?> createEmployee() {
        Employee newEmployee = new Employee();
        newEmployee.setName("Testing");
        employeeRepository.save(newEmployee);
        return ResponseEntity.ok(newEmployee);
    }
}

