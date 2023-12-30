package system.employee.employeesystem.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Data
@NoArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    private String firstName;
    private String lastName;
    private String email;
    private String job;
    private LocalDate birthday;

    @Transient
    private int age;

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public Employee(String firstName, String job, String lastName, String email, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday= dob;
        this.job = job;
    }
}
