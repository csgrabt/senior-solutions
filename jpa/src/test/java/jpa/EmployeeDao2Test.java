package jpa;


import kotlin.jvm.internal.MutablePropertyReference0;
import org.flywaydb.core.Flyway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;


import javax.persistence.Persistence;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class EmployeeDao2Test {


    private EmployeeDao employeeDao = new EmployeeDao(Persistence.createEntityManagerFactory("pu"));
    private MariaDbDataSource dataSource = new MariaDbDataSource();
    EmployeeId employeeId;
    EmployeeId employeeId2;

    private Flyway flyway;

    @BeforeEach
    void init() {
      /*  try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/Employees?useUnicode=true");
            dataSource.setUser("employees");
            dataSource.setPassword("employees");
        } catch (SQLException se) {
            throw new IllegalArgumentException("Some problem with dataSource", se);
        }
        flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.clean();
        flyway.migrate();*/
        employeeId = new EmployeeId("x", 1L);
        employeeId2 = new EmployeeId("x", 2L);
    }

    @Test
    void testSaveThenFind() {
        Employee employee = new Employee(employeeId, "John Doe");
        employeeDao.saveEmployee(employee);
        Employee loadedEmployee = employeeDao
                .findEmployeeById(employeeId);
        assertEquals("John Doe", loadedEmployee.getName());
    }


    @Test
    void testSaveTheListAll() {
        employeeDao.saveEmployee(new Employee(employeeId, "Jane Doe"));
        employeeDao.saveEmployee(new Employee(employeeId2, "John Doe"));


        List<Employee> employees = employeeDao.listAll();

        assertEquals(List.of("Jane Doe", "John Doe"),
                employees.stream().map(Employee::getName).collect(Collectors.toList()));
    }

    @Test
    void testChangeName() {
        Employee employee = new Employee(employeeId, "John Doe");
        employeeDao.saveEmployee(employee);
        EmployeeId employeeIdBack = employee.getEmployeeId();
        employeeDao.changeName(employeeIdBack, "Jane Doe");
        Employee anotherEmployee = employeeDao.findEmployeeById(employeeIdBack);
        assertEquals("Jane Doe", anotherEmployee.getName());
    }

    @Test
    void testDelete() {
        Employee employee = new Employee(employeeId, "John Doe");
        employeeDao.saveEmployee(employee);
        EmployeeId employeeIdBack = employee.getEmployeeId();

        employeeDao.deleteEntity(employeeIdBack);

        List<Employee> employees = employeeDao.listAll();

        assertTrue(employees.isEmpty());
    }

    @Test
    void testEmployeeWithAttributes() {
        for (int i = 0; i < 10; i++) {
            employeeDao.saveEmployee(new Employee(new EmployeeId("x" + i, 1L), "John Doe", Employee.EmployeeType.HALF_TIME, LocalDate.of(2000, 01, 01)));
        }

        Employee employee = employeeDao.listAll().get(0);

        assertEquals(LocalDate.of(2000, 01, 01), employee.getDateOfBirth());

    }

    @Test
    void testSaveEmployeeChangeState() {
        Employee employee = new Employee(employeeId, "John Doe");
        employeeDao.saveEmployee(employee);
        employee.setName("Jack Doe");

        Employee employee1 = employeeDao.findEmployeeById(employeeId);

    }

    @Test
    void testMerge() {
        Employee employee = new Employee(employeeId, "John Doe");
        employeeDao.saveEmployee(employee);
        employee.setName("Jack Doe");
        employeeDao.updateEmployee(employee);
        Employee employee1 = employeeDao.findEmployeeById(employeeId);
        assertEquals("Jack Doe", employee1.getName());
    }

}
