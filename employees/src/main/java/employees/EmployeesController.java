package employees;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/employees")
@RestController
public class EmployeesController {
    private EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping
    public List<EmployeeDto> listEmployees(@RequestParam Optional<String> prefix) {
        return employeesService.employeeList(prefix);
    }


    @GetMapping("/{id}")
    public ResponseEntity findEmployeeById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(employeesService.findEmployeeById(id));
        } catch (IllegalArgumentException iea) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody CreateEmployeeCommand command) {
        return employeesService.createEmployee(command);
    }

    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable("id") long id, @RequestBody UpdateEmployeeCommand command) {
        return employeesService.updateEmployee(id, command);

    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") long id) {
        employeesService.deleteEmployee(id);
    }

}
