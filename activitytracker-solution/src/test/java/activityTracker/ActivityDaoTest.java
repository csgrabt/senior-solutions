package activityTracker;

import org.junit.jupiter.api.Test;

import javax.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActivityDaoTest {

    ActivityDao activityDao = new ActivityDao(Persistence.createEntityManagerFactory("pu"));


    @Test
    void testSaveActivity() {

        Activity activity = new Activity();
        activity.setDescription("Almafa keresés a Mátrában");
        activity.setStartTime(LocalDateTime.of(2022, 12, 10, 8, 12));
        activity.setType(ActivityType.HIKING);
        activityDao.saveActivity(activity);
        Activity anotherActivity = activityDao.findActivityById(activity.getId());
        assertEquals("Almafa keresés a Mátrában", anotherActivity.getDescription());

    }

    @Test
    void testFindAllToList() {
        for (int i = 0; i < 151; i++) {
            Activity activity = new Activity();
            activity.setDescription("Almafa keresés a Mátrában " + i + ".");
            activity.setStartTime(LocalDateTime.of(2022, 12, 10, 8, 0));
            activity.setType(ActivityType.HIKING);
            activityDao.saveActivity(activity);
        }

        List<Activity> activityList = activityDao.listActivities();
        System.out.println(activityList.size());
        assertEquals(151, activityList.size());
    }

    @Test
    void testUpdateActivity() {
        Activity activity = new Activity();
        activity.setDescription("Almafa keresés a Mátrában");
        activity.setStartTime(LocalDateTime.of(2022, 12, 10, 8, 12));
        activity.setType(ActivityType.HIKING);
        activityDao.saveActivity(activity);



        activityDao.updateActivity(activity.getId(), "Keresés a Bükkben");
        activityDao.findActivityById(activity.getId());
    }
}



