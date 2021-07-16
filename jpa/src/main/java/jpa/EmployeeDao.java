package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    public void updateEmployee(Employee employee) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(employee);
        em.getTransaction().commit();
        em.close();
    }

    public Employee findEmployeeByIdWithNicknames(EmployeeId employeeId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Employee employee = em.createQuery(
                "select e from Employee e join fetch e.nicknames where e.employeeId.id = :id and e.employeeId.depName = :depName", Employee.class
        ).setParameter("id", employeeId.getId()).setParameter("depName", employeeId.getDepName())
                .getSingleResult();
        em.close();
        return employee;
    }

    public Employee findEmployeeByIdWithVacations(EmployeeId employeeId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Employee employee = em.createQuery(
                "select e from Employee e join fetch e.vacationBooking where e.employeeId.id = :id and e.employeeId.depName = :depName", Employee.class
        ).setParameter("id", employeeId.getId()).setParameter("depName", employeeId.getDepName())
                .getSingleResult();
        em.close();
        return employee;
    }

    //  public Employee findEmployeeByIdWithPhoneNumbers(EmployeeId employeeId) {
    //      EntityManager em = entityManagerFactory.createEntityManager();
    //      Employee employee = em.createQuery(
    //              "select e from Employee e join fetch e.phoneNumbers where id = :id and depName = :depName", Employee.class
    //      ).setParameter("id", employeeId.getId()).setParameter("depName", employeeId.getDepName())
    //              .getSingleResult();
    //      em.close();
    //      return employee;
    //  }

    public void addPhoneNumber(EmployeeId employeeId, PhoneNumber phoneNumber) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Employee employee = em.find(Employee.class, employeeId);
        employee.addPhoneNumber(phoneNumber);
        em.getTransaction().commit();
        em.close();
    }

    //SELECT * FROM employees JOIN phone_numbers WHERE employees.depName = "x" AND employees.id = 1
    public Employee findEmployeeByIdWithPhoneNumbers(EmployeeId employeeId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Employee employee = em.createQuery(
                "select e from Employee e join fetch e.phoneNumbers where e.employeeId.id = :id and e.employeeId.depName = :depName", Employee.class
        ).setParameter("id", employeeId.getId()).setParameter("depName", employeeId.getDepName())
                .getSingleResult();
        em.close();
        return employee;
    }

    public Employee findEmployeeByName(String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(emp).where(cb.equal(emp.get("name"), name));
        Employee employee = em.createQuery(c).getSingleResult();
        em.close();
        return employee;

    }

    public List<Employee> listEmployee(int start, int maxResult) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Employee> employees = em.createNamedQuery("listEmployees", Employee.class)
                .setFirstResult(start)
                .setMaxResults(maxResult)
                .getResultList();
        em.close();
        return employees;
    }

    public int findParkingPlaceNumberByEmployeeName(String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        int i = em.createQuery("select p.number from Employee e join e.parkingPlace p where e.name = :name ",
                Integer.class)
                .setParameter("name", name)
                .getSingleResult();
        em.close();
        return i;
    }


}
