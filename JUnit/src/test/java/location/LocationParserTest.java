package location;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationParserTest {
    LocationParser locationParser;
    Location location;
    String text = "Budapest,47.497912,19.040235";

    @BeforeEach
    void setUP() {
        location = new Location("Valahol", 0, 0);
        locationParser = new LocationParser();
    }


    @Test
    void testParse() {


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


    @Test
    void parseTest() {
        Location location = locationParser.parse(text);
        Location location2 = locationParser.parse(text);

        assertNotSame(location, location2);
        assertEquals(location, location2);
    }

    @Test
    void distanceCalculator() {
        Location location = new Location("A", 42.990967, -71.463767, 63);
        Location location2 = new Location("B", 65.990967, 179.463767, 111);

        assertEquals(6481, location.distance(location2), 10);
    }

    @Test
    void parseTestAllAttribute() {
        Location location5 = locationParser.parse(text);
        assertAll(
                () -> assertEquals("Budapest", location5.getName()),
                () -> assertEquals(47.497912, location5.getLat()),
                () -> assertEquals(19.040235, location5.getLon()),
                () -> assertEquals(0, location5.getHeight())

        );
    }

    private Object[][] locations = {
            {new Location("Alma", 0, 0), true},
            {new Location("Alma", 90, 1), false},
            {new Location("Alma", 90, 1), false},
            {new Location("Alma", 90, 1), false},
            {new Location("Alma", 0, 0), true},

    };

    @RepeatedTest(value = 5, name = "Is on meridian {currentRepetition} / {totalRepetition}")
    void test(RepetitionInfo repetitionInfo) {


        assertEquals(locations[repetitionInfo.getCurrentRepetition() -1][1],
                locationParser.isOnEquator((Location) locations[repetitionInfo.getCurrentRepetition()-1][0]));
    }
}