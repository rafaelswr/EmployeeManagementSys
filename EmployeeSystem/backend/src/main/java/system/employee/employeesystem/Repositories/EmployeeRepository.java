package system.employee.employeesystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system.employee.employeesystem.Models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
