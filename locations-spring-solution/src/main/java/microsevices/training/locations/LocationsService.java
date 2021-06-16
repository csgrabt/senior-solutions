package microsevices.training.locations;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class LocationsService {
    private List<Location> favoriteLocations = Arrays.asList(new Location("Xuzhou", 56.12, 98.23),
            new Location("Peru", 52.54, 154.26));


    public List<Location> getLocation() {
        return new ArrayList<>(favoriteLocations);
    }
}
