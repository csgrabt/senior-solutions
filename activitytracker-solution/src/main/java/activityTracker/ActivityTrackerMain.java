package activityTracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class ActivityTrackerMain {
    public static void main(String[] args) {
        Activity activity = new Activity();

        activity.setType(ActivityType.BIKING);
        activity.setStartTime(LocalDateTime.now());
        activity.setDescription("Valami");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(activity);
        em.getTransaction().commit();
        em.close();
        entityManagerFactory.close();

    }
}
