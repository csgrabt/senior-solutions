package jpa;

import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;

public class EmployeeServiceTest {

    private EmployeeDao employeeDao;

    @Before
    public void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        employeeDao = new EmployeeDao(entityManagerFactory);
    }

    @Test
    public void testSaveThenFind() {
        Employee employee = new Employee("John Doe");
        employeeDao.saveEmployee(employee);
        Employee loadedEmployee = employeeDao
                .findEmployeeById(employee.getId());
        assertEquals("John Doe", loadedEmployee.getName());
    }

}
