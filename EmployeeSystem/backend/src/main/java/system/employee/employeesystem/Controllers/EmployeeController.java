package system.employee.employeesystem.Controllers;

import org.hibernate.metamodel.internal.EmbeddableInstantiatorPojoIndirecting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import system.employee.employeesystem.Models.Employee;
import system.employee.employeesystem.Services.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("")
    public ResponseEntity<List<Employee>> getEmployees(){
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Optional<Employee>> getEmployee(@PathVariable Long employeeId){
        return new ResponseEntity<>(employeeService.getEmployee(employeeId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public void addEmployee(@RequestBody Employee emp){
        employeeService.addEmployee(emp);
    }

    @DeleteMapping("/delete/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId){
        employeeService.deleteEmployee(employeeId);
    }

    @PutMapping("/{employeeId}")
    public void updateEmployee(@PathVariable Long employeeId, @RequestBody Employee emp){
        employeeService.updateEmployee(employeeId, emp);

    }

}
