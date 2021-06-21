package microsevices.training.locations;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/locations")
@RestController
public class LocationsController {

    private LocationsService locationsService;


    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }



    @GetMapping
    public String geLocation(@RequestParam Optional<String> prefix) {
        return locationsService.getLocation(prefix)
                .stream()
                .map(LocationDto::toString)
                .collect(Collectors.toList())
                .toString();
    }

}
