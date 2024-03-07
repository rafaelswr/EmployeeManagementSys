package system.employee.employeesystem.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.employee.employeesystem.DTO.EmployeeDTO;
import system.employee.employeesystem.Models.Cargo;
import system.employee.employeesystem.Models.Employee;
import system.employee.employeesystem.Repositories.CargoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CargoService {

    private CargoRepository cargoRepository;

    @Autowired
    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public List<Cargo> getAll() {
        return cargoRepository.findAll();
    }

    public Optional<Cargo> getCargoById(Long id ){
        Optional<Cargo> cargo = cargoRepository.findByCargoId(id);
        if(cargo.isPresent()){
            return cargo;
        }else{
            log.info("NOT FOUND CARGO");
            throw new RuntimeException();
        }
    }

    public List<EmployeeDTO> getEmployeesByCargo(String cargoName) {
        return cargoRepository.getEmployeesByCargo(cargoName).stream().map(this::mapToEmpoyeeDTO).collect(Collectors.toList());
    }

    private EmployeeDTO mapToEmpoyeeDTO(Employee employee) {
        return EmployeeDTO.builder()
                .cargo(employee.getCargo().toString())
                .name(employee.getFirstName()+" "+employee.getLastName())
                .employeeId(employee.getEmployeeId())
                .build();
    }
}
