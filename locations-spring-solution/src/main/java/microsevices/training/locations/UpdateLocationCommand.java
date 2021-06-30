package microsevices.training.locations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLocationCommand {
    private Optional<String> name;
    private Optional<Double> lat;
    private Optional<Double> lon;
}
