package system.employee.employeesystem.Services;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.employee.employeesystem.DTO.EmployeeDTO;
import system.employee.employeesystem.Models.Employee;
import system.employee.employeesystem.Repositories.EmployeeRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> getEmployees() {
        return employeeRepository.findAll().stream().map(this::mapToEmployeeDTO).toList();
    }

    private EmployeeDTO mapToEmployeeDTO(Employee employee) {
        return EmployeeDTO.builder().employeeId(employee.getEmployeeId())
                .name(employee.getFirstName()+" "+employee.getLastName())
                .cargo(employee.getCargo().getCargoName())
                .build();
    }

    public Optional<Employee> getEmployee(Long employeeId) {
        Optional<Employee> emp = employeeRepository.findById(employeeId);
        if(emp.isPresent()){
            return emp;
        }else{
            throw new IllegalStateException("Employee " + employeeId + " not found");
        }
    }

    public void addEmployee(Employee emp) {
        Optional<Employee> employee = employeeRepository.findByEmail(emp.getEmail());
        if(employee.isPresent()){
            throw new IllegalStateException("Ja existe");
        }else{
            employeeRepository.save(emp);
        }

    }

    public void deleteEmployee(Long employeeId) {
        Optional<Employee> findEmp = employeeRepository.findById(employeeId);
        if(findEmp.isPresent()){
            employeeRepository.delete(findEmp.get());
        }else {
            throw new IllegalStateException("Not found to delete");
        }
    }
    @Transactional
    public void updateEmployee(Long employeeId, Employee emp) {
        Employee findEmp = employeeRepository.findById(employeeId).orElseThrow(()->new IllegalStateException("Error finding employee"));
        if(emp.getEmail()!=null && emp.getEmail().length()>0 &&  !Objects.equals(findEmp.getEmail(), emp.getEmail())) {
            if(employeeRepository.findByEmail(emp.getEmail()).isEmpty()){
                findEmp.setEmail(emp.getEmail());
            }
        }else {
            throw new IllegalStateException("Error updating object employee");
        }
    }

    public void saveEmployee(Employee emp) {
        Optional<Employee> employee = employeeRepository.findByEmail(emp.getEmail());
        if(employee.isPresent()){
            throw new IllegalStateException("Ja existe");
        }else{
            employeeRepository.save(emp);
        }

    }

    public List<EmployeeDTO> getAllByCargo(String cargoName) {
        return employeeRepository.getAllByCargoName(cargoName).stream().map(this::mapToEmployeeDTO).collect(Collectors.toList());
    }
}
