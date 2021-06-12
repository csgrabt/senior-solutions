package location;

import java.util.Optional;

public class DistanceService {
    LocationRepository locationRepository;

    public DistanceService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Optional<Double> calculateDistance(String name1, String name2) {
        if (locationRepository.findByName(name1).equals(Optional.empty())
                || locationRepository.findByName(name2).equals(Optional.empty())) {
            return Optional.empty();
        }
        Double result = locationRepository.findByName(name1).get().distance(locationRepository.findByName(name2).get());

        return Optional.of(result);
    }
}



