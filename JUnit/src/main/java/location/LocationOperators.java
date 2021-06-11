package location;

import java.util.List;
import java.util.stream.Collectors;

public class LocationOperators {

    public List<Location> filterOnNorth(List<Location> locations) {
        return locations
                .stream()
                .filter(n -> n.getLat() > 0)
                .collect(Collectors.toList());
    }
}
