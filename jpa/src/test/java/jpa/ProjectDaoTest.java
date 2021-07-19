package jpa;

import org.junit.jupiter.api.Test;

import javax.persistence.Persistence;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDaoTest {
    ProjectDao projectDao = new ProjectDao(Persistence.createEntityManagerFactory("pu"));
    EmployeeDao employeeDao = new EmployeeDao(Persistence.createEntityManagerFactory("pu"));

    @Test
    public void testSaveProject() {
        Employee employee1 = new Employee(new EmployeeId("x", 1L), "John Doe");
        Employee employee2 = new Employee(new EmployeeId("x", 2L), "Jack Doe");
        Employee employee3 = new Employee(new EmployeeId("x", 3L), "Jane Doe");

        employeeDao.saveEmployee(employee1);
        employeeDao.saveEmployee(employee2);
        employeeDao.saveEmployee(employee3);

        Project project1 = new Project("Java");
        Project project2 = new Project("dotNet");
        Project project3 = new Project("Python");

        project1.addEmployee(employee1);
        project1.addEmployee(employee2);
        project1.addEmployee(employee3);

        project2.addEmployee(employee2);
        project2.addEmployee(employee3);

        project3.addEmployee(employee1);

       // projectDao.saveProject(project1);
       // projectDao.saveProject(project2);
       // projectDao.saveProject(project3);

       // Project project = projectDao.findProjectByName("Java");
      //  assertEquals(Set.of("Jack Doe", "Jane Doe", "John Doe"), project.getEmployees().stream().map(Employee::getName).collect(Collectors.toSet()));
    }
}