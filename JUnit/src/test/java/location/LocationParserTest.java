package location;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationParserTest {
    LocationParser locationParser;
    Location location;

    @BeforeEach
    void setUP() {
        location = new Location("Valahol", 0, 0);
        locationParser = new LocationParser();
    }


    @Test
    void testParse() {
        String text = "Budapest,47.497912,19.040235";

        Location location = locationParser.parse(text);

        assertEquals("Budapest", location.getName());
        assertEquals(47.497912, location.getLat());
        assertEquals(19.040235, location.getLon());

    }

    @Test
    void testParseTextIsNull() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                locationParser.parse(null));
        assertEquals("Invalid text!", ex.getMessage());

    }

    @Test
    void isOnEquatorTest() {
        assertTrue(locationParser.isOnEquator(location));
    }

    @Test
    void isOnMeridian() {
        assertTrue(locationParser.isOnPrimeMeridian(location));
    }
}