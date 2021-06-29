package employees;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


@Service
public class EmployeesService {
    private ModelMapper modelMapper;
    private AtomicLong idGenerator = new AtomicLong();


    private List<Employee> employeeList = Collections.synchronizedList(new ArrayList<>(
            List.of(
                    new Employee(idGenerator.incrementAndGet(), "John Doe"),
                    new Employee(idGenerator.incrementAndGet(), "Jack Doe")
            )
    ));

    public EmployeesService(ModelMapper modelMapper, List<Employee> employeeList) {
        this.modelMapper = modelMapper;

    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        Employee employee = new Employee(idGenerator.incrementAndGet(), command.getName());
        employeeList.add(employee);
        return modelMapper.map(employee, EmployeeDto.class);

    }

    public List<EmployeeDto> employeeList(Optional<String> prefix) {
        Type targetListType = new TypeToken<List<EmployeeDto>>() {
        }.getType();
        List<Employee> filtered = employeeList
                .stream()
                .filter(n -> prefix.isEmpty() || n.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .collect(Collectors.toList());

        return modelMapper.map(filtered, targetListType);
    }

    public EmployeeDto findEmployeeById(long id) {
        return modelMapper.map(employeeList
                        .stream()
                        .filter(n -> n.getId() == id)
                        .findAny()
                        .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id)),
                EmployeeDto.class);
    }

    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        Employee employee = employeeList
                .stream()
                .filter(n -> n.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
        employee.setName(command.getName());
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public void deleteEmployee(long id) {
        Employee employee = employeeList
                .stream()
                .filter(n -> n.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
        employeeList.remove(employee);
    }

    public void deleteAllEmployees() {
        idGenerator = new AtomicLong();
        employeeList.clear();
    }

}
