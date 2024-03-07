package system.employee.employeesystem.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import system.employee.employeesystem.DTO.DepartmentDTO;
import system.employee.employeesystem.Models.Department;
import system.employee.employeesystem.Services.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String createDepartment(@RequestBody Department dept){
        departmentService.createDept(dept);
        return "Department Successfully created!";
    }

    @GetMapping()
    public ResponseEntity<List<DepartmentDTO>> getAll(){
        return new ResponseEntity<>(departmentService.getAllDepartments(),HttpStatus.OK);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentInfo(@PathVariable Long departmentId) throws Exception {
        return new ResponseEntity<>(departmentService.getDepartmentInfo(departmentId), HttpStatus.OK);
    }

}

