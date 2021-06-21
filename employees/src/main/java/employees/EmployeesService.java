package employees;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeesService {
    private ModelMapper modelMapper;
    private List<Employee> employeeList = Collections.synchronizedList(new ArrayList<>(
            List.of(
                    new Employee(1L, "John Doe"),
                    new Employee(2L, "Jack Doe")
            )
    ));

    public EmployeesService(ModelMapper modelMapper, List<Employee> employeeList) {
        this.modelMapper = modelMapper;

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
}
