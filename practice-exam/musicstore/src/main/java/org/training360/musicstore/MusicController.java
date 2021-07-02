package org.training360.musicstore;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.awt.event.WindowStateListener;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instruments")
public class MusicController {
    private MusicStoreServie musicStoreServie;

    public MusicController(MusicStoreServie musicStoreServie) {
        this.musicStoreServie = musicStoreServie;
    }

    @GetMapping
    public List<InstrumentDTO> listInstruments(@RequestParam Optional<String> prefix, @RequestParam Optional<Double> prefix2) {
        return musicStoreServie.instruments(prefix, prefix2);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstrumentDTO createInstrument(@Valid @RequestBody CreateInstrumentCommand command) {
        return musicStoreServie.createInstrument(command);
    }

    @DeleteMapping
    public void deleteInstruments() {
        musicStoreServie.deleteAllInstruments();
    }


    @GetMapping("/{id}")
    public InstrumentDTO findEmployeeById(@PathVariable("id") long id) {
        return musicStoreServie.findInstrumentById(id);
    }

    @PutMapping("/{id}")
    public InstrumentDTO updateInstrument(@PathVariable("id") long id, @Valid @RequestBody UpdatePriceCommand command) {
        return musicStoreServie.updateInstrument(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteInstrument(@PathVariable("id") long id) {
        musicStoreServie.deleteAllInstrument(id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException iae) {
        Problem problem =
                Problem.builder()
                        .withType(URI.create("instruments/not-found"))
                        .withTitle("Not found")
                        .withStatus(Status.NOT_FOUND)
                        .withDetail(iae.getMessage())
                        .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);

    }


}
