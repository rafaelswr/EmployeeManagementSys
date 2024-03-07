package system.employee.employeesystem.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cargoId;
    private String cargoName;
    private int totalEmployees;

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  //  @JsonManagedReference
    private List<Employee> employees = new LinkedList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "deptId", referencedColumnName = "deptId")
    private Department department;

    public int getTotalEmployees() {
        return employees.size();
    }

    public Cargo(String cargoName) {
        this.cargoName = cargoName;
    }
}
