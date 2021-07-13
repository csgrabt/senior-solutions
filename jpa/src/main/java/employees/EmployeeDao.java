package employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
        return em.find(Employee.class, id);
    }

}
