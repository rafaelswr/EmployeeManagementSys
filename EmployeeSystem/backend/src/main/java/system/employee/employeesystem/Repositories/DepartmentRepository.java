package system.employee.employeesystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import system.employee.employeesystem.Models.Department;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT d from Department d Where d.deptId = ?1 ")
    Optional<Department> getByDeptId(Long id);
}
