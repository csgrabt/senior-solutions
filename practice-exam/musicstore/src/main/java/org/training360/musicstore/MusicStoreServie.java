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
    private AtomicLong idGenerator = new AtomicLong();
    private List<Instrument> instruments = Collections.synchronizedList(new ArrayList<>());

    public MusicStoreServie(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

    }

    public List<InstrumentDTO> instruments(Optional<String> prefix, Optional<Double> prefix2) {
        Type targetListType = new TypeToken<List<InstrumentDTO>>() {
        }.getType();

        List<Instrument> filtered = instruments
                .stream()
                .filter(n -> prefix.isEmpty() || n.getBrand().equalsIgnoreCase(prefix.get()))
                .filter(n -> prefix2.isEmpty() || n.getPrice() == prefix2.get())
                .collect(Collectors.toList());
        return modelMapper.map(filtered, targetListType);

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
    }
}
