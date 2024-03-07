package system.employee.employeesystem.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
public class EmployeeDTO {

    private Long employeeId;
    private String name;
    private String cargo;

}
