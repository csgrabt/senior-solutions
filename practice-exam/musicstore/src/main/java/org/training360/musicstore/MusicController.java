package org.training360.musicstore;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/instruments")
public class MusicController {
    private MusicStoreServie musicStoreServie;

    public MusicController(MusicStoreServie musicStoreServie) {
        this.musicStoreServie = musicStoreServie;
    }
    @GetMapping
    public List<InstrumentDto> listEmployees(@RequestParam Optional<Map<String, Double>> prefix) {
        return musicStoreServie.instruments(prefix);
    }

}
