package com.example.locationspringsolutionagain;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class LocationService {
    private List<Location> locations = Collections.synchronizedList(new ArrayList<>(Arrays.asList(
            new Location("Xuzhou", 0d, 0d),
            new Location("Budapest", 12.14, -82.2)
    )));


    public List<Location> getLocations() {
        return new ArrayList<>(locations);
    }
}
