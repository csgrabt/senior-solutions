package microsevices.training.locations;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LocationsServiceTest {

    @Test
    void getLocation() {
        LocationsService locationsService = new LocationsService();

        List<Location> locations = locationsService.getLocation();

        assertEquals(2, locations.size());
        assertEquals("Xuzhou", locations.get(0).getName());
    }
}