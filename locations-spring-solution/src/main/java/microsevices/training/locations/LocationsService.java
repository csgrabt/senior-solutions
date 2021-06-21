package microsevices.training.locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Service
public class LocationsService {
    private ModelMapper modelMapper;
    private List<Location> favoriteLocations = Collections.synchronizedList(new ArrayList<>(
            List.of(new Location("Xuzhou", 56.12, 98.23),
                    new Location("Peru", 52.54, 154.26)))
    );


    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public List<LocationDto> getLocation() {
        Type targetListType = new TypeToken<List<LocationDto>>() {
        }.getType();
        return modelMapper.map(favoriteLocations, targetListType);
    }
}
