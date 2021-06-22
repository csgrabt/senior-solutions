package location;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class LocationServiceTest {
    LocationService locationService = new LocationService();
    String fileName = "data.csv";

    @Test
    void locationReader() {
        List<Location> locationList = locationService.locationReader(fileName);

        Location location = locationList.get(0);

        assertThat(location.getName()).isEqualTo("Budapest")
                .startsWith("Bu")
                .endsWith("t");

        assertThat(locationList)
                .as("Contains element, Bukarest")
                .hasSize(2)
                .extracting(Location::getName, Location::getLat)
                .contains(tuple("Bukarest", 47.497912));

    }
}