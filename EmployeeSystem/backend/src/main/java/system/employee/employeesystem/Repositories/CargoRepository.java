package system.employee.employeesystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import system.employee.employeesystem.DTO.EmployeeDTO;
import system.employee.employeesystem.Models.Cargo;
import system.employee.employeesystem.Models.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface CargoRepository extends   JpaRepository<Cargo, Long> {

    @Query("SELECT c from Cargo c where c.cargoId = ?1 ")
    Optional<Cargo> findByCargoId(Long id);

    @Query("SELECT c from Cargo c JOIN c.employees r where r.cargo.cargoName = :cargo")
    List<Employee> getEmployeesByCargo(@Param("cargo") String cargo);


}
