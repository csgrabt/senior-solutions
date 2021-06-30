package com.example.movie2_0;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private ModelMapper modelMapper;
    private AtomicLong idGenerator = new AtomicLong();
    private List<Movie> movies = Collections.synchronizedList(new ArrayList<>(
            List.of(
                    new Movie(
                            idGenerator.incrementAndGet(), "J", 5, new ArrayList<>(Arrays.asList(1, 2)), 0
                    )
            )
    ));


    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<MovieDto> getMovies(Optional<String> title) {
        Type targetListType = new TypeToken<List<MovieDto>>() {
        }.getType();
        List<Movie> filtered = movies
                .stream()
                .filter(n -> title.isEmpty() || n.getTitle().toLowerCase().startsWith(title.get().toLowerCase()))
                .collect(Collectors.toList());


        return modelMapper.map(filtered, targetListType);
    }

    public static double calculateAverage(List<Integer> list) {
        return list.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics()
                .getAverage();
    }

    public MovieDto findMovieById(long id) {
        return modelMapper.map(movies
                        .stream()
                        .filter(n -> n.getID() == id)
                        .findAny()
                        .orElseThrow(() -> new IllegalArgumentException("Movie not found: " + id)),
                MovieDto.class

        );
    }

    public MovieDto createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(idGenerator.incrementAndGet(), command.getTitle(), command.getLength());
        movies.add(movie);
        return modelMapper.map(movie, MovieDto.class);
    }

    public void deleteMovie(long id) {
        Movie movie = movies
                .stream()
                .filter(n -> n.getID() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
        movies.remove(movie);
    }

    public MovieDto addRating(long id, RatingMovieCommand command) {
        Movie movie = movies
                .stream()
                .filter(n -> n.getID() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Movie not found: " + id));

        movie.addRating(command.getRating());

        return modelMapper.map(movie, MovieDto.class);
    }
}
