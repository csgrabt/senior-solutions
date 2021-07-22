package microsevices.training.locations;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
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
    public List<LocationDto> getLocation(@RequestParam Optional<String> prefix) {
        return locationsService.getLocation(prefix);

    }

    @GetMapping("/{latMax}/{latMin}/{lonMax}/{lonMin}")
    public List<LocationDto> findLocationByCoordinate(@PathVariable("latMax") Optional<Long> latMax, @PathVariable("latMin") Optional<Long> latMin, @PathVariable("lonMax") Optional<Long> lonMax, @PathVariable("lonMin") Optional<Long> lonMin) {
        return locationsService.findLocationByCoordinate(latMax, latMin, lonMax, lonMin);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDto createLocation(@Valid @RequestBody CreateLocationCommand command) {
        return locationsService.createLocation(command);
    }

    @PutMapping("/{id}")
    public LocationDto updateLocation(@PathVariable("id") long id, @Valid @RequestBody UpdateLocationCommand command) {
        return locationsService.updateLocation(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable("id") long id) {
        locationsService.deleteLocation(id);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Problem> handledValidException(MethodArgumentNotValidException exception) {
        List<Violation> violations =
                exception.getBindingResult().getFieldErrors().stream()
                        .map(item -> new Violation(item.getField(), item.getDefaultMessage()))
                        .collect(Collectors.toList());
        Problem problem =
                Problem.builder()
                        .withType(URI.create("location/not-valid"))
                        .withTitle("Validation error")
                        .withStatus(Status.BAD_REQUEST)
                        .withDetail(exception.getMessage())
                        .with("violations", violations)
                        .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);


    }

}