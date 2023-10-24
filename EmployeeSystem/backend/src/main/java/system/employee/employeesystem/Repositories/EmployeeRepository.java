package system.employee.employeesystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import system.employee.employeesystem.Models.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT s from Employee s where s.email = ?1")
    Optional<Employee> findByEmail(String email);

}
