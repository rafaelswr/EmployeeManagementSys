package system.employee.employeesystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import system.employee.employeesystem.DTO.EmployeeDTO;
import system.employee.employeesystem.Models.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT s from Employee s where s.email = ?1")
    Optional<Employee> findByEmail(String email);

    @Query("SELECT s from Employee s where s.employeeId= ?1")
    Optional<Employee> findById(long employeeId);

    @Query("SELECT s from Employee s where s.cargo.cargoName = :cargo")
    List<Employee> getAllByCargoName(@Param("cargo") String cargo);

}
