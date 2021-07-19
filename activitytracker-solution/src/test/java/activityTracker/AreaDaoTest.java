package activityTracker;

import org.junit.jupiter.api.Test;

import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AreaDaoTest {
    AreaDao areaDao = new AreaDao(Persistence.createEntityManagerFactory("pu"));
    ActivityDao activityDao = new ActivityDao(Persistence.createEntityManagerFactory("pu"));

    @Test
    void testSaveArea() {


        Area area = new Area();

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
        activity.addTrackPoint(trackPoint2, trackPoint3, trackPoint4, trackPoint5, trackPoint6);
        activityDao.saveActivity(activity);


        Activity activity1 = new Activity();
        activity1.setDescription("Almafa keresés a Mátrában");
        activity1.setStartTime(LocalDateTime.of(2023, 12, 10, 8, 12));
        activity1.setType(ActivityType.HIKING);
        activity1.setLabels(List.of("Mátra", "Ősz", "Falevél"));
        activity1.addTrackPoint(new TrackPoint(LocalDateTime.of(2020, 01, 01, 01, 01), 10, 10));
        activityDao.saveActivity(activity1);

        Activity activity2 = new Activity();
        activity2.setDescription("Almafa keresés a Mátrában");
        activity2.setStartTime(LocalDateTime.of(2023, 12, 10, 8, 12));
        activity2.setType(ActivityType.HIKING);
        activity2.setLabels(List.of("Mátra", "Ősz", "Falevél"));
        activity2.addTrackPoint(trackPoint1);
        activityDao.saveActivity(activity2);

        area.setName("Mecsek");
        area.addActivity(activity);
        area.addActivity(activity1);
        areaDao.saveArea(area);

        Area area1 = new Area();
        area1.setName("Ez is mecsek");
        area1.addActivity(activity);
        area1.addActivity(activity1);
        area1.addActivity(activity2);
        areaDao.saveArea(area1);


        Activity anotherActivity = activityDao.findActivityByIdWithAreas(activity.getId());

        assertEquals(2, anotherActivity.getAreaList().size());

    }

}