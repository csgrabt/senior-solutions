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

    @Coordinate(type = Type.LAT)
    private Optional<Double> lat;
    @Coordinate(type = Type.LON)
    private Optional<Double> lon;
}
