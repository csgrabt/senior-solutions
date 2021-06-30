package microsevices.training.locations;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLocationCommand {
    //@NotBlank
    private String name;
    private double lat;
    private double lon;
}
