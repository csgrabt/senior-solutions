package activityTracker;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.List;


public class ActivityDao {
    private EntityManagerFactory entityManagerFactory;


    public ActivityDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    public void saveActivity(Activity activity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(activity);
        em.getTransaction().commit();
        em.close();

    }

    public Activity findActivityById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Activity activity = em.find(Activity.class, id);
        em.close();
        return activity;
    }


    public List<Activity> listActivities() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Activity> activityList = em.createQuery("Select a from Activity a order by a.id", Activity.class).getResultList();
        em.close();
        return activityList;
    }

    public void updateActivity(long id, String description) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.find(Activity.class, id).setDescription(description);
        em.getTransaction().commit();
        em.close();

    }

    public Activity findActivityByIdWithLabels() {
        EntityManager em = entityManagerFactory.createEntityManager();
        Activity activity = em.createQuery("select a from Activity a join fetch a.labels", Activity.class)
                .getSingleResult();
        em.close();
        return activity;
    }

    public Activity findActivityByIdWithTrackPoints() {
        EntityManager em = entityManagerFactory.createEntityManager();
        Activity activity = em.createQuery("select a from Activity a join fetch a.trackPoints", Activity.class)
                .getSingleResult();
        em.close();
        return activity;
    }

    public Activity findActivityByIdWithAreas(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Activity activity = em.createQuery("select a from Activity a join fetch a.areaList where a.id = :id", Activity.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return activity;
    }


    public List<Coordinate> findTrackPointCoordinatesByDate(LocalDateTime afterThis, int start, int max) {

        EntityManager em = entityManagerFactory.createEntityManager();
        List<Coordinate> activityList = em.createNamedQuery("findCoordinate", Coordinate.class)
                .setParameter("afterThis", afterThis)
                .setFirstResult(start)
                .setMaxResults(max)
                .getResultList();
        em.close();

        return activityList;

    }

    public List<Object[]> findTrackPointCountByActivity() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Object[]> activities = em.createQuery("select a.description, a.trackPoints.size from Activity a order by a.description")
                .getResultList();
        em.close();
        return activities;
    }


}


