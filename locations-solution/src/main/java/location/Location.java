package location;

import java.util.Objects;

public class Location {
    private String name;
    private double lat;
    private double lon;
    private double height;

    public Location(String name, double lat, double lon) {
        validatorLon(lon);
        validatorLat(lat);
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public Location(String name, double lat, double lon, double height) {
        validatorLon(lon);
        validatorLat(lat);
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        validatorLat(lat);
        this.lat = lat;
    }

    private void validatorLat(double lat) {
        if (lat < -90 || lat > 90) {
            throw new IllegalArgumentException("Latitude " + lat + " is not valid!");
        }
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        validatorLon(lon);
        this.lon = lon;
    }

    public double distance(Location anotherLocation) {

        final double R = 6378.137;

        double latDistance = Math.toRadians(anotherLocation.getLat() - lat);
        double lonDistance = Math.toRadians(anotherLocation.getLon() - lon);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(anotherLocation.getLat()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // convert to meters

        double height = this.height - anotherLocation.getHeight();

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    private void validatorLon(double lon) {
        if (lon > 180 || lon < -180) {
            throw new IllegalArgumentException("Longitude " + lon + " is not valid!");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.lat, lat) == 0 && Double.compare(location.lon, lon) == 0 && Double.compare(location.height, height) == 0 && Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lat, lon, height);
    }
}
