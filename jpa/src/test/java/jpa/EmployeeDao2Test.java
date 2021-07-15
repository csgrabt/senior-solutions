package jpa;


import kotlin.jvm.internal.MutablePropertyReference0;
import org.flywaydb.core.Flyway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;


import javax.persistence.Persistence;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class EmployeeDao2Test {


     EmployeeDao employeeDao = new EmployeeDao(Persistence.createEntityManagerFactory("pu"));
     MariaDbDataSource dataSource = new MariaDbDataSource();
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


    @Test
    void testNicknames() {
        Employee employee = new Employee(employeeId, "John Doe");
        employee.setNicknames(Set.of("Béla", "Géza", "HurrikánJó"));
        employeeDao.saveEmployee(employee);
        Employee anotherEmployee = employeeDao.findEmployeeByIdWithNicknames(employeeId);
        assertEquals(Set.of("Béla", "Géza", "HurrikánJó"), anotherEmployee.getNicknames());
    }

    @Test
    void testVacations() {
        Employee employee = new Employee(employeeId, "Jack");

        employee.setVacationBooking(Set.of(new VacationEntry(LocalDate.of(2020, 10, 10), 10), new VacationEntry(LocalDate.of(2018, 05, 02), 2)
        ));
        employeeDao.saveEmployee(employee);

        Employee anotherEmployee = employeeDao.findEmployeeByIdWithVacations(employeeId);

        assertEquals(Set.of(new VacationEntry(LocalDate.of(2020, 10, 10), 10), new VacationEntry(LocalDate.of(2018, 05, 02), 2)
        ), anotherEmployee.getVacationBooking());
    }

    @Test
    void testPhoneNumbers() {
        Employee employee = new Employee(employeeId, "Jack Doe");
        employee.setPhoneNumbers(Map.of("Home", "1234", "Work", "4321", "A", "23"));
        employeeDao.saveEmployee(employee);
        Employee employeeAnother = employeeDao.findEmployeeByIdWithPhoneNumbers(employee.getEmployeeId());

    assertEquals(Map.of("Home", "1234", "Work", "4321", "A", "23"), employeeAnother.getPhoneNumbers());

    }


}
