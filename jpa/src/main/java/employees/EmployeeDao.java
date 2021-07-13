package employees;

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

    public Employee findEmployeeById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Employee employee = em.find(Employee.class, id);
        em.close();
        return employee;
    }

    public List<Employee> listAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Employee> result = em.createQuery("select e from Employee e order by e.name", Employee.class).getResultList();
        em.close();
        return result;
    }


    public void changeName(Long id, String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Employee employee = em.find(Employee.class, id);
        employee.setName(name);
        em.getTransaction().commit();
        em.close();


    }

    public void deleteEntity(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Employee employee = em.find(Employee.class, id);
        em.remove(employee);
        em.getTransaction().commit();
        em.close();

    }
}
