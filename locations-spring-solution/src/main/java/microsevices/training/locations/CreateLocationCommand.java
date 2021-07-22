package microsevices.training.locations;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class CreateLocationCommand {
    @NotBlank(message = "name cannot be black or empty")
    private String name;
    //@Min(-90)
    //@Max(90)
    @Coordinate(type = Type.LAT, message = "Invalid lat")
    private double lat;
    @Min(-180)
    //@Max(180)
    @Coordinate(type = Type.LON, message = "Invalid lon")
    private double lon;
}
