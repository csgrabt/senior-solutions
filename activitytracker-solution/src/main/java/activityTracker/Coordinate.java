package activityTracker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coordinate {
    private double lat;
    private double lon;


    public List<Coordinate> coordinateList(List<Activity> activityList) {
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates =
                activityList.stream()
                        .flatMap(l -> l.getTrackPoints().stream())
                        .map(n -> new Coordinate(n.getLat(), n.getLon()))
                        .collect(Collectors.toList());


        return coordinates;
    }


    public static void main(String[] args) {
        Activity activity = new Activity();

        List<Activity> activityList = new ArrayList<>();

        List<TrackPoint> trackPoints = new ArrayList<>();

        TrackPoint trackPoint = new TrackPoint(LocalDateTime.now(), 1, 1);
        TrackPoint trackPoint1 = new TrackPoint(LocalDateTime.now(), 2, 2);
        TrackPoint trackPoint2 = new TrackPoint(LocalDateTime.now(), 3, 3);
        TrackPoint trackPoint3 = new TrackPoint(LocalDateTime.now(), 4, 4);

        activity.addTrackPoint(trackPoint, trackPoint1, trackPoint2, trackPoint3);

        activityList.add(activity);

        Coordinate coordinate = new Coordinate();

        System.out.println(
                coordinate.coordinateList(activityList));

    }
}
