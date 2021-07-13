package employees;


import org.flywaydb.core.Flyway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;


import javax.persistence.Persistence;


import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class EmployeeDao2Test {


    private EmployeeDao employeeDao = new EmployeeDao(Persistence.createEntityManagerFactory("pu"));
    private MariaDbDataSource dataSource = new MariaDbDataSource();
    private Flyway flyway;

    @BeforeEach
    void init() {
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/Employees?useUnicode=true");
            dataSource.setUser("employees");
            dataSource.setPassword("employees");
        } catch (SQLException se) {
            throw new IllegalArgumentException("Some problem with dataSource", se);
        }
        flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.clean();
        flyway.migrate();

    }

    @Test
    void testSaveThenFind() {
        Employee employee = new Employee("John Doe");
        employeeDao.saveEmployee(employee);
        Employee loadedEmployee = employeeDao
                .findEmployeeById(employee.getId());
        assertEquals("John Doe", loadedEmployee.getName());
    }


    @Test
    void testSaveTheListAll() {
        employeeDao.saveEmployee(new Employee("Jane Doe"));
        employeeDao.saveEmployee(new Employee("John Doe"));
        employeeDao.saveEmployee(new Employee("Jack Doe"));

        List<Employee> employees = employeeDao.listAll();

        assertEquals(List.of("Jack Doe", "Jane Doe", "John Doe"),
                employees.stream().map(Employee::getName).collect(Collectors.toList()));
    }

    @Test
    void testChangeName() {
        Employee employee = new Employee("John Doe");
        employeeDao.saveEmployee(employee);
        long id = employee.getId();
        employeeDao.changeName(id, "Jane Doe");
        Employee anotherEmployee = employeeDao.findEmployeeById(id);
        assertEquals("Jane Doe", anotherEmployee.getName());
    }

    @Test
    void testDelete() {
        Employee employee = new Employee("John Doe");
        employeeDao.saveEmployee(employee);
        long id = employee.getId();

        employeeDao.deleteEntity(id);

        List<Employee> employees = employeeDao.listAll();

        assertTrue(employees.isEmpty());
    }
}
