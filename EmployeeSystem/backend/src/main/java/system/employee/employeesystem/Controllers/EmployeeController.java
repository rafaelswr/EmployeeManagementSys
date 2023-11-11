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
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import system.employee.employeesystem.Models.Employee;
import system.employee.employeesystem.Services.EmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@CrossOrigin(origins="http://localhost:3000"/*,allowCredentials)"true"*/)
@RequestMapping("/employees")
public class EmployeeController {
    /*
    @Autowired
    public SimpMessagingTemplate messagingTemplate;
    */
    @Autowired
    private SSEController sseController;

    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

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

    @PostMapping("/add")
    public void addEmployee(@RequestBody Employee emp){
        employeeService.addEmployee(emp);
        sseController.notifyClients(emp);

    }

    @DeleteMapping("/delete/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId){
        Optional<Employee> empById = employeeService.getEmployee(employeeId);
        if(empById.isPresent()){
            employeeService.deleteEmployee(employeeId);
            sseController.notifyClients(empById.get());
        }
    }

    @PutMapping("/{employeeId}")
    public void updateEmployee(@PathVariable Long employeeId, @RequestBody Employee emp){
        employeeService.updateEmployee(employeeId, emp);
        sseController.notifyClients(emp);

    }

}
