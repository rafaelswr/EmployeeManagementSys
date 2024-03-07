package system.employee.employeesystem.Controllers;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import system.employee.employeesystem.DTO.EmployeeDTO;
import system.employee.employeesystem.Models.Cargo;
import system.employee.employeesystem.Services.CargoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cargos")
public class CargoController {

    private CargoService cargoService;

    @Autowired
    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping("")
    public ResponseEntity<List<Cargo>> getAllCargos(){
        return new ResponseEntity<>(cargoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{cargoId}")
    public ResponseEntity<Optional<Cargo>> getCargoById(@PathVariable Long cargoId){
        return new ResponseEntity<>(cargoService.getCargoById(cargoId), HttpStatus.OK);

    }

    @GetMapping("/employees/{cargoName}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByCargo(@PathVariable String cargoName){
        return new ResponseEntity<>(cargoService.getEmployeesByCargo(cargoName), HttpStatus.OK);
    }

}
