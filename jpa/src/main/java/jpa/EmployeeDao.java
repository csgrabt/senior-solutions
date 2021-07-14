package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class EmployeeDao {
    private EntityManagerFactory entityManagerFactory;


    public EmployeeDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void saveEmployee(Employee employee) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        em.close();
    }

    public Employee findEmployeeById(EmployeeId employeeId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Employee employee = em.find(Employee.class, employeeId);
        em.close();
        return employee;
    }

    public List<Employee> listAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Employee> result = em.createQuery("select e from Employee e order by e.name", Employee.class).getResultList();
        em.close();
        return result;
    }


    public void changeName(EmployeeId employeeId, String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Employee employee = em.find(Employee.class, employeeId);
        employee.setName(name);
        em.getTransaction().commit();
        em.close();


    }

    public void deleteEntity(EmployeeId employeeId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Employee employee = em.find(Employee.class, employeeId);
        em.remove(employee);
        em.getTransaction().commit();
        em.close();

    }
}
