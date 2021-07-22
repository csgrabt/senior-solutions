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

    @CoordinateAsOptionalDouble(type = Type.LAT)
    private Optional<Double> lat;
    @CoordinateAsOptionalDouble(type = Type.LON)
    private Optional<Double> lon;
}
