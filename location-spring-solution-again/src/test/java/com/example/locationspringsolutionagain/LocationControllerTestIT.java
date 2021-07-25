package com.example.locationspringsolutionagain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocationControllerTestIT {

    @Autowired
    LocationController locationController;

    @Test
    void testLocations() {
        String locations = locationController.getLocations();

        String locationsFromList = Collections.synchronizedList(new ArrayList<>(Arrays.asList(
                new Location("Xuzhou", 0d, 0d),
                new Location("Budapest", 12.14, -82.2)
        ))).toString();

        assertEquals(locationsFromList, locations);

    }

}