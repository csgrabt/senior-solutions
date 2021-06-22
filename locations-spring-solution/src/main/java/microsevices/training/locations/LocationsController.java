package microsevices.training.locations;


import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/{latMax}/{latMin}/{lonMax}/{lonMin}")
    public List<LocationDto> findLocationByCoordinate(@PathVariable("latMax") Optional<Long> latMax, @PathVariable("latMin") Optional<Long> latMin, @PathVariable("lonMax") Optional<Long> lonMax, @PathVariable("lonMin") Optional<Long> lonMin) {
        return locationsService.findLocationByCoordinate(latMax, latMin, lonMax, lonMin);
    }


}
