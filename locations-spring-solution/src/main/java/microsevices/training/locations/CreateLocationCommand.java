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
    @Min(-90)
    @Max(90)
    private double lat;
    @Min(-180)
    @Max(180)
    private double lon;
}
