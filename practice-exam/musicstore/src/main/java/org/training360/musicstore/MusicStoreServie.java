package org.training360.musicstore;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MusicStoreServie {
    private ModelMapper modelMapper;
    private AtomicLong idGenerator;
    private List<Instrument> instruments = Collections.synchronizedList(new ArrayList<>());

    public MusicStoreServie(ModelMapper modelMapper, AtomicLong idGenerator) {
        this.modelMapper = modelMapper;
        this.idGenerator = idGenerator;
    }


    public List<InstrumentDTO> instruments(Optional<String> prefix, Optional<Double> prefix2) {
        return instruments
                .stream()
                .filter(n -> prefix.isEmpty() || n.getBrand().equalsIgnoreCase(prefix.get()))
                .filter(n -> prefix2.isEmpty() || n.getPrice() == prefix2.get())
                .map(i -> modelMapper.map(i, InstrumentDTO.class))
                .collect(Collectors.toList());


    }

    public InstrumentDTO createInstrument(CreateInstrumentCommand command) {
        Instrument instrument = new Instrument(
                idGenerator.incrementAndGet(),
                command.getBrand(),
                command.getType(),
                command.getPrice(),
                LocalDate.now()
        );
        instruments.add(instrument);

        return modelMapper.map(instrument, InstrumentDTO.class);

    }

    public void deleteAllInstruments() {
        instruments.clear();
        idGenerator.set(0);
    }

    public InstrumentDTO findInstrumentById(long id) {
        Instrument instrument =
                getInstrument(id);
        return modelMapper.map(instrument, InstrumentDTO.class);
    }

    private Instrument getInstrument(long id) {
        return instruments
                .stream()
                .filter(n -> n.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Instrument not found"));
    }

    public InstrumentDTO updateInstrument(long id, UpdatePriceCommand command) {
        Instrument instrument = getInstrument(id);
        if (instrument.getPrice() != command.getPrice()) {
            instrument.setPrice(command.getPrice());
            instrument.setPostDate(LocalDate.now());
        }
        return modelMapper.map(instrument, InstrumentDTO.class);
    }

    public void deleteAllInstrument(long id) {
        Instrument instrument = getInstrument(id);
        instruments.remove(instrument);
    }
}
