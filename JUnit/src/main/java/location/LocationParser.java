package location;

public class LocationParser {

    private static final String REGEX = ",";

    public Location parse(String text) {
        validator(text);
        String[] array = text.split(REGEX);
        return new Location(
                array[0],
                Double.parseDouble(array[1]),
                Double.parseDouble(array[2]));
    }


    private void validator(String text) {
        if (text == null || !text.contains(REGEX)) {
            throw new IllegalArgumentException("Invalid text!");
        }


    }
}
