package system.employee.employeesystem.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.LinkedList;
import java.util.List;

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
    private LocalDate birthday;
    private LocalDateTime creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargoId", referencedColumnName = "cargoId")
    @JsonBackReference
    private Cargo cargo;

    @Transient
    private int age;

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public Employee(String firstName, String lastName, String email, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday= dob;
    }

}
