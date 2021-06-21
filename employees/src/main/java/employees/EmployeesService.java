package employees;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeesService {
    private ModelMapper modelMapper;



    private List<Employee> employeeList = Collections.synchronizedList(new ArrayList<>(
            List.of(
                    new Employee(1L, "John Doe"),
                    new Employee(1L, "Jack Doe")
            )
    ));

    public EmployeesService(ModelMapper modelMapper, List<Employee> employeeList) {
        this.modelMapper = modelMapper;

    }

     public List<EmployeeDto> employeeList() {
        Type targetListType = new TypeToken<List<EmployeeDto>>(){}.getType();
        return modelMapper.map(employeeList, targetListType);
     }

}
