package location;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class LocationOperatorsTest {
    List<Location> locationList;

    @BeforeEach
    void setUp() {
        locationList = Arrays.asList(
                new Location("Somewhere", 1, 1),
                new Location("Somewherec", -1, 1),
                new Location("Somewherec", -1, 1),
                new Location("Somewherec", -1, 1),
                new Location("Somewherec", -1, 1),
                new Location("Somewherec", -1, 1),
                new Location("Somewherec", -1, 1),
                new Location("Somewhere", 1, 1)
        );

    }

    @Test
    void filterOnNorth() {
        List<Location> locationOnNorth = new LocationOperators().filterOnNorth(locationList);
        List<String> nameOfCity = locationOnNorth
                .stream()
                .map(Location::getName)
                .collect(Collectors.toList());
        assertEquals(Arrays.asList("Somewhere", "Somewhere"), nameOfCity);

    }
}