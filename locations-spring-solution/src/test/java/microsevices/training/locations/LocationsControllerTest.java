package microsevices.training.locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

    @Mock
    LocationsService locationsService;

    @InjectMocks
    LocationsController locationsController;

    @Test
     void geLocation() {
         when(locationsService.getLocation(Optional.empty())).thenReturn(Arrays.asList(
                 new LocationDto("Budapest", 0, 0),
                 new LocationDto("Xuzhou", 10, 15)
         ));

          String locations = locationsController.geLocation(Optional.empty());

          assertThat(locations).contains("Budapest", "Xuzhou");
      }
}