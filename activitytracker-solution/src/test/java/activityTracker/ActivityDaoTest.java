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

    @Test
    void testFindActivityByIdWithLabels() {
        Activity activity = new Activity();
        activity.setDescription("Almafa keresés a Mátrában");
        activity.setStartTime(LocalDateTime.of(2022, 12, 10, 8, 12));
        activity.setType(ActivityType.HIKING);
        activity.setLabels(List.of("Mátra", "Ősz", "Falevél"));
        activityDao.saveActivity(activity);
        Activity anotherActivity = activityDao.findActivityByIdWithLabels(activity.getId());
        assertEquals(3, anotherActivity.getLabels().size());
    }


    @Test
    void findActivityByIdWithTrackPoints() {
        Activity activity = new Activity();
        activity.setDescription("Almafa keresés a Mátrában");
        activity.setStartTime(LocalDateTime.of(2022, 12, 10, 8, 12));
        activity.setType(ActivityType.HIKING);
        activity.setLabels(List.of("Mátra", "Ősz", "Falevél"));
        TrackPoint trackPoint1 = new TrackPoint(LocalDateTime.of(2000, 1, 1, 1, 1), 10, 10);
        TrackPoint trackPoint2 = new TrackPoint(LocalDateTime.of(2001, 1, 1, 1, 1), 11, 10);
        TrackPoint trackPoint3 = new TrackPoint(LocalDateTime.of(2002, 1, 1, 1, 1), 12, 10);
        TrackPoint trackPoint4 = new TrackPoint(LocalDateTime.of(2003, 1, 1, 1, 1), 13, 10);
        TrackPoint trackPoint5 = new TrackPoint(LocalDateTime.of(2004, 1, 1, 1, 1), 14, 10);
        TrackPoint trackPoint6 = new TrackPoint(LocalDateTime.of(1999, 1, 1, 1, 1), 9, 10);
        activity.addTrackPoint(trackPoint1, trackPoint2, trackPoint3, trackPoint4, trackPoint5, trackPoint6);
        activityDao.saveActivity(activity);

        Activity anotherActivity = activityDao.findActivityByIdWithTrackPoints(activity.getId());

        assertEquals(1999, anotherActivity.getTrackPoints().get(0).getTime().getYear());
    }
}



