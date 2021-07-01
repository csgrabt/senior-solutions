package org.training360.musicstore;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MusicStoreServie {
    private ModelMapper modelMapper;
    private AtomicLong idGenerator = new AtomicLong();
    private List<Instrument> instruments = Collections.synchronizedList(new ArrayList<>());

    public MusicStoreServie(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

    }
}
