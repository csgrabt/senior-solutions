package microsevices.training.locations;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class LocationsServiceTest {
    LocationsService locationsService = new LocationsService(new ModelMapper());

    @Test
    void getLocation() {


        List<LocationDto> locations = locationsService.getLocation(Optional.empty());

        assertEquals(2, locations.size());
        assertEquals("Xuzhou", locations.get(0).getName());
    }
}