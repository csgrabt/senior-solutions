package com.example.locationspringsolutionagain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationControllerTest {

    @Mock
    LocationService locationService;

    @InjectMocks
    LocationController locationController;

    @Test
    void getLocations() {
        when(locationService.getLocations()).thenReturn(
                List.of(new Location("Alma", 1.0, 1.0)));
        List<Location> locations = List.of(new Location("Alma", 1.0, 1.0));

        String locationsAsAString = locationController.getLocations();

        assertThat(locationsAsAString).isEqualTo(locations.toString());
        verify(locationService).getLocations();
    }
}