package activityTracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AreaDao {

    private EntityManagerFactory entityManagerFactory;

    public AreaDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    public void saveArea(Area area) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(area);
        em.getTransaction().commit();
        em.close();
    }
}
