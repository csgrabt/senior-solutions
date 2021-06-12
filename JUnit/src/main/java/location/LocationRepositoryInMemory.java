package location;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LocationRepositoryInMemory implements LocationRepository {

    private List<Location> locations = new ArrayList<>();

    public void add(Location location) {
        locations.add(location);
    }


    @Override
    public Optional<Location> findByName(String name) {
        return locations.stream()
                .filter(n -> n.getName().equals(name))
                .findFirst();


    }
}
