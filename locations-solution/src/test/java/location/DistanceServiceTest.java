package location;


import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class DistanceServiceTest {

    @Mock
    LocationRepository locationRepository;

    @InjectMocks
    DistanceService distanceService;


    @Test
    void calculateDistanceTestBothCityIsNotValid() {


        assertEquals(Optional.empty(), distanceService.calculateDistance("H", "L"));
        verify(locationRepository).findByName(any());
    }


    @Test
    void testCalculateDistance() {
        when(locationRepository.findByName("A")).thenReturn(Optional.of(new Location("A", 42.990967, -71.463767, 63)));
        when(locationRepository.findByName("B")).thenReturn(Optional.of(new Location("B", 65.990967, 179.463767, 111)));

        Optional<Double> result = distanceService.calculateDistance("A", "B");

        assertEquals(6481, result.get(), 10);
    }


}