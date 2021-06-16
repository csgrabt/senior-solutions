package location;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LocationRepositoryInMemoryTest {
    LocationRepositoryInMemory locationRepositoryInMemory = new LocationRepositoryInMemory();
    Location location = new Location("Pécs", 0, 0);
    Location location1 = new Location("Budapest", 0, 0);
    Location location2 = new Location("Siófok", 0, 0);
    Location location3 = new Location("Mezőtúr", 0, 0);

    @BeforeEach
    void setUp() {


        locationRepositoryInMemory.add(location);
        locationRepositoryInMemory.add(location1);
        locationRepositoryInMemory.add(location2);
        locationRepositoryInMemory.add(location3);

    }


    @Test
    void findByName() {
        Location result = locationRepositoryInMemory.findByName("Mezőtúr").get();
        assertEquals(location3, result);

    }
@Test
    void findByNameNotValid() {
        assertEquals(Optional.empty(), locationRepositoryInMemory.findByName("Mezőtr"));
    }

}