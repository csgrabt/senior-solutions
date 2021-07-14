package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesMain {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Employee employee = new Employee("John Doe");
        em.persist(employee);
        em.getTransaction().commit();

        System.out.println(employee.getId());

        long id = employee.getId();

        employee = em.find(Employee.class, id);
        System.out.println(employee);

        em.getTransaction().begin();
        employee = em.find(Employee.class, id);
        employee.setName("Jhon Jhon Doe");
        em.getTransaction().commit();

        List<Employee> employees = em.createQuery("select e from Employee e", Employee.class)
                .getResultList();
        System.out.println(employees);


        em.getTransaction().begin();
        employee = em.find(Employee.class, id);
        em.remove(employee);


        List<Employee> employees1 = em.createQuery("select e from Employee e", Employee.class)
                .getResultList();
        System.out.println(employees1);

        em.close();
        factory.close();

    }
}
