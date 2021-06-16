package location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LocationService {


    public List<Location> locationReader(String fileName) {
        List<Location> locations = new ArrayList<>();
        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(Objects.requireNonNull(
                                LocationService.class.getResourceAsStream(fileName)
                        ))
                )
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                locations.add(new LocationParser().parse(line));
            }
        } catch (
                IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }
        return locations;
    }
}


