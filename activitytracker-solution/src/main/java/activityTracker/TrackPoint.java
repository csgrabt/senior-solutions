package activityTracker;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class TrackPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime time;
    private double lat;
    private double lon;
    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Activity activity;

    public TrackPoint(LocalDateTime time, double lat, double lon) {
        this.time = time;
        this.lat = lat;
        this.lon = lon;
    }
}