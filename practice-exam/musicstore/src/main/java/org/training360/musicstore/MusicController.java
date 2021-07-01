package org.training360.musicstore;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.event.WindowStateListener;
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
    public List<InstrumentDTO> listInstruments(@RequestParam Optional<String> prefix, @RequestParam Optional<Double> prefix2 ) {
        return musicStoreServie.instruments(prefix, prefix2);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstrumentDTO createInstrument(@Valid @RequestBody CreateInstrumentCommand command) {
        return musicStoreServie.createInstrument(command);
    }


}
