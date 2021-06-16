package location;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void constructorTestLat() {
        double lat = 500;
        IllegalArgumentException ieo = assertThrows(IllegalArgumentException.class, () ->
                new Location("Budapest", lat, 0));

        assertEquals("Latitude " + lat +" is not valid!", ieo.getMessage());

    }

    @Test
    void constructorTestLon() {
        double lon = 180.1;
        IllegalArgumentException ieo = assertThrows(IllegalArgumentException.class, () ->
                new Location("Budapest", 90, lon));

        assertEquals("Longitude "+ lon +" is not valid!", ieo.getMessage());

    }
}