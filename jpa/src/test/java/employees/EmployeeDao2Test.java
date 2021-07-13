package employees;


import org.flywaydb.core.Flyway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;


import javax.persistence.Persistence;


import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

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

}
