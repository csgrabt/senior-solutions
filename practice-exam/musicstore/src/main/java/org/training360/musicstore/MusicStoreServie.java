package org.training360.musicstore;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
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

    public List<InstrumentDto> instruments(Optional<Map<String, Double>> prefix) {
        Type targetListType = new TypeToken<List<InstrumentDto>>() {
        }.getType();

    List<Instrument> filtered = instruments
            .stream()
            .filter(n-> prefix.isEmpty())
            .collect(Collectors.toList());
    return modelMapper.map(filtered, targetListType);

    }
}
