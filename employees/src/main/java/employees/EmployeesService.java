package employees;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


@Service
@Slf4j
@AllArgsConstructor
public class EmployeesService {
    private ModelMapper modelMapper;
    private EmployeesDao employeesDao;


    public List<EmployeeDto> employeeList(Optional<String> prefix) {
        return employeesDao.findAll()
                .stream()
                .filter(n -> prefix.isEmpty() || n.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .map(n -> modelMapper.map(n, EmployeeDto.class)).collect(Collectors.toList());

        // Type targetListType = new TypeToken<List<EmployeeDto>>() {
        // }.getType();
        // List<Employee> filtered = employeeList
        //         .stream()
        //         .filter(n -> prefix.isEmpty() || n.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
        //         .collect(Collectors.toList());

        // return modelMapper.map(filtered, targetListType);
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        Employee employee = new Employee(command.getName());
        employeesDao.creatEmployee(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public EmployeeDto findEmployeeById(long id) {
        return modelMapper.map(employeesDao.findById(id), EmployeeDto.class);

        // return modelMapper.map(employeeList
        //                 .stream()
        //                 .filter(n -> n.getId() == id)
        //                 .findAny()
        //                 .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id)),
        //         EmployeeDto.class);
    }

    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        Employee employee = new Employee(id, command.getName());
        employeesDao.updateEmployee(employee);
        return modelMapper.map(employee, EmployeeDto.class);


    }

    public void deleteEmployee(long id) {
        employeesDao.deleteById(id);
    }

    public void deleteAllEmployees() {
        employeesDao.deleteAll();
    }

}
