package microsevices.training.locations;


import org.springframework.http.HttpStatus;
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
    public List<LocationDto> geLocation(@RequestParam Optional<String> prefix) {
        return locationsService.getLocation(prefix);

    }

    @GetMapping("/{latMax}/{latMin}/{lonMax}/{lonMin}")
    public List<LocationDto> findLocationByCoordinate(@PathVariable("latMax") Optional<Long> latMax, @PathVariable("latMin") Optional<Long> latMin, @PathVariable("lonMax") Optional<Long> lonMax, @PathVariable("lonMin") Optional<Long> lonMin) {
        return locationsService.findLocationByCoordinate(latMax, latMin, lonMax, lonMin);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDto createLocation(@RequestBody CreateLocationCommand command) {
        return locationsService.createLocation(command);
    }

    @PutMapping("/{id}")
    public LocationDto updateLocation(@PathVariable("id") long id, @RequestBody UpdateLocationCommand command) {
        return locationsService.updateLocation(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable("id") long id) {
        locationsService.deleteLocation(id);
    }
}
