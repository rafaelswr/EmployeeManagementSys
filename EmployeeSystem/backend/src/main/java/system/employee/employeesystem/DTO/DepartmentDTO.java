package system.employee.employeesystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import system.employee.employeesystem.Models.Cargo;
import system.employee.employeesystem.Models.Employee;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class DepartmentDTO {

    private String departmentName;
    private int totalCargos;
    private List<Cargo> CargoList;

}
