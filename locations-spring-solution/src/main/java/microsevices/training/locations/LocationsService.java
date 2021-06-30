package microsevices.training.locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


@Service
public class LocationsService {
    private ModelMapper modelMapper;
    private AtomicLong idGenerator = new AtomicLong();

    private List<Location> favoriteLocations = Collections.synchronizedList(new ArrayList<>(
            List.of(new Location(idGenerator.incrementAndGet(), "Xuzhou", 56.12, 98.23),
                    new Location(idGenerator.incrementAndGet(), "Peru", 52.54, 154.26)))
    );


    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public List<LocationDto> getLocation(Optional<String> prefix) {
        Type targetListType = new TypeToken<List<LocationDto>>() {
        }.getType();

        List<Location> filtered = favoriteLocations
                .stream()
                .filter(n -> prefix.isEmpty() || n.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .collect(Collectors.toList());

        return modelMapper.map(filtered, targetListType);
    }

    public List<LocationDto> findLocationByCoordinate(Optional<Long> latMax, Optional<Long> latMin, Optional<Long> lonMax, Optional<Long> lonMin) {
        Type targetListType = new TypeToken<List<LocationDto>>() {
        }.getType();

        List<Location> filtered = favoriteLocations
                .stream()
                .filter(n -> latMax.isEmpty() || n.getLat() <= latMax.get())
                .filter(n -> latMin.isEmpty() || n.getLat() >= latMin.get())
                .filter(n -> lonMax.isEmpty() || n.getLon() <= lonMax.get())
                .filter(n -> lonMin.isEmpty() || n.getLon() >= lonMin.get())
                .collect(Collectors.toList());
        return modelMapper.map(filtered, targetListType);
    }

    public LocationDto createLocation(CreateLocationCommand command) {

        Location location = new Location(
                idGenerator.incrementAndGet(),
                command.getName(),
                command.getLat(),
                command.getLon()
        );
        favoriteLocations.add(location);
        return modelMapper.map(location, LocationDto.class);
    }
}
