package activityTracker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Activity {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "act_id_gen")
    @TableGenerator(name = "act_id_gen", pkColumnName = "id_gen", valueColumnName = "id_val")
    private Long id;
    @Column(name = "start_Time", nullable = false)
    private LocalDateTime startTime;
    @Column(length = 200, nullable = false)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActivityType type;
    private LocalDateTime creatAt;
    private LocalDateTime updateAt;
    @ElementCollection
    private List<String> labels;


    @PrePersist
    public void setCreatAt() {
        this.setCreatAt(LocalDateTime.now());

    }

    @PreUpdate
    public void setUpdateAt() {
        this.setUpdateAt(LocalDateTime.now());
    }
}
