package system.employee.employeesystem.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import system.employee.employeesystem.DTO.EmployeeDTO;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;

    private String departmentName;
    private String description;
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cargo> cargos = new LinkedList<>();

    @PrePersist
    public void prePersist() {
        creationDate = LocalDateTime.now();
    }

    public Department(String departmentName, String description) {
        this.departmentName = departmentName;
        this.description = description;
        prePersist();

    }

}
