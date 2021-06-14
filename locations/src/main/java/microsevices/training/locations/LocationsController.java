package microsevices.training.locations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;


@RestController
public class LocationsController {

    private LocationsService locationsService;

    @Autowired
    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping("/location")
    public String geLocation() {
        return locationsService.getLocation()
                .stream()
                .map(Location::toString)
                .collect(Collectors.toList())
                .toString();
    }

}
