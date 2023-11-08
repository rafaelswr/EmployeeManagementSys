package system.employee.employeesystem.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import system.employee.employeesystem.Models.Employee;
import system.employee.employeesystem.Services.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/employees")
public class EmployeeController {

    public SimpMessagingTemplate messagingTemplate;

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public ResponseEntity<List<Employee>> getEmployees(){
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Optional<Employee>> getEmployee(@PathVariable Long employeeId){
        return new ResponseEntity<>(employeeService.getEmployee(employeeId), HttpStatus.OK);
    }


  /*  @MessageMapping("/employees")
    @SendTo("/topic/employees")
    public List<Employee> updateEmployeesList() {
      List<Employee> employees = employeeService.getEmployees();
      return employees;
    }*/

    @MessageMapping("/employees")
    @SendTo("/topic/employees")
    public String updateEmployeesList() {
        return "Hello";
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
