package system.employee.employeesystem;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import system.employee.employeesystem.Models.Cargo;
import system.employee.employeesystem.Models.Department;
import system.employee.employeesystem.Models.Employee;
import system.employee.employeesystem.Repositories.CargoRepository;
import system.employee.employeesystem.Repositories.DepartmentRepository;
import system.employee.employeesystem.Repositories.EmployeeRepository;

import java.time.LocalDate;

@Configuration
public class AppConfig {

    @Bean
    CommandLineRunner commandLineRunner (
            DepartmentRepository departmentRepository,
            EmployeeRepository employeeRepository,
            CargoRepository cargoRepository

    ){
        return args -> {
            Department development = new Department("Development", "Where things are created!");
            Department HR = new Department("Human Resources", "All human contact");

            Employee ana = new Employee("Ana", "Silva", "ana@gmail.com", LocalDate.of(1999,1,13));
            Employee rafa = new Employee("Rafael", "Borges", "rafa@gmail.com", LocalDate.of(1999,2,23));

            Cargo junior = new Cargo("Junior");
            Cargo senior = new Cargo("Senior");
            Cargo ChiefRecruitment = new Cargo("Head Recruitment");

            junior.getEmployees().add(rafa);
            ChiefRecruitment.getEmployees().add(ana);

            development.getCargos().add(junior);
            development.getCargos().add(senior);
            HR.getCargos().add(ChiefRecruitment);

            ana.setCargo(ChiefRecruitment);
            rafa.setCargo(junior);

            junior.setDepartment(development);
            senior.setDepartment(development);
            ChiefRecruitment.setDepartment(HR);

            departmentRepository.save(HR);
            departmentRepository.save(development);

            cargoRepository.save(junior);
            cargoRepository.save(senior);
            cargoRepository.save(ChiefRecruitment);

        };
    }

}
