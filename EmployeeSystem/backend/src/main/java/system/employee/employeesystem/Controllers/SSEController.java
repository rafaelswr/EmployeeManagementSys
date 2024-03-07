package system.employee.employeesystem.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import system.employee.employeesystem.DTO.EmployeeDTO;
import system.employee.employeesystem.Models.Employee;
import system.employee.employeesystem.Services.EmployeeService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/sse-employees")
public class SSEController {

    //all the clients connected
    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    private final EmployeeService employeeService;

    @Autowired
    public SSEController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/stream")
    public SseEmitter handleSSE() {
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        try {
            List<EmployeeDTO> employees = employeeService.getEmployees();
            emitter.send(SseEmitter.event().data(employees, MediaType.APPLICATION_JSON));
        } catch (IOException e) {
            emitter.completeWithError(e);
        }
        return emitter;
    }
    public void notifyClients(Employee employee) {
        emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event().data(employee, MediaType.APPLICATION_JSON));
            } catch (Exception e) {
                emitter.complete();
                emitters.remove(emitter);
            }
        });

    }
}
