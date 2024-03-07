package system.employee.employeesystem.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.employee.employeesystem.DTO.DepartmentDTO;
import system.employee.employeesystem.DTO.EmployeeDTO;
import system.employee.employeesystem.Models.Department;
import system.employee.employeesystem.Repositories.DepartmentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void createDept(Department dept){
        departmentRepository.save(dept);
    }

    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream().map(this::mapToDepartmentDTO).toList();
    }

    private DepartmentDTO mapToDepartmentDTO(Department department) {
        return DepartmentDTO.builder()
                .CargoList(department.getCargos())
                .totalCargos(department.getCargos().size())
                .departmentName(department.getDepartmentName())
                .build();
    }

    public DepartmentDTO getDepartmentInfo(Long departmentId) throws Exception {
        Optional<Department> department = departmentRepository.getByDeptId(departmentId);
        if(department.isPresent()){
            return DepartmentDTO.builder().departmentName(department.get().getDepartmentName())
                    .totalCargos(department.get().getCargos().size())
                    .CargoList(department.get().getCargos())
                    .build();
        }else{
            throw new Exception("NOT FOUND DEPT ");
        }
    }
/*
    public List<DepartmentDTO> getAll() {
        return departmentRepository.findAll().stream().map(this::mapToDepartmentDTO)
                .collect(Collectors.toList());
    }

    private DepartmentDTO mapToDepartmentDTO(Department department) {
        return DepartmentDTO.builder()
                .departmentName(department.getDepartmentName())
                .totalEmployees(department.getEmployees().size())
                .employeeDTOList(department.getEmployees().stream().map(ele->{
                     return EmployeeDTO.builder().employeeId(ele.getEmployeeId())
                            .name(ele.getFirstName()+" "+ele.getLastName())
                            .build();
                }).toList())
                .build();
    }
*/
}
