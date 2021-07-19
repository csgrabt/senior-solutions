package activityTracker;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Activity> activityList = new HashSet<>();

    public void addActivity(Activity activity) {
        activityList.add(activity);
        activity.getAreaList().add(this);
    }
}
