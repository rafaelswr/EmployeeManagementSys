package system.employee.employeesystem.Services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.employee.employeesystem.Models.Employee;
import system.employee.employeesystem.Repositories.EmployeeRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployee(Long employeeId) {
        Optional<Employee> emp = employeeRepository.findById(employeeId);
        if(emp.isPresent()){
            return emp;
        }else{
            throw new IllegalStateException("Employee "+employeeId+" not found");
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
}
