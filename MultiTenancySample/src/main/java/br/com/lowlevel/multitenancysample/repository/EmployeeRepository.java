package br.com.lowlevel.multitenancysample.repository;

import br.com.lowlevel.multitenancysample.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
