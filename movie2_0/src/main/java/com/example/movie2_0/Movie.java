package com.example.movie2_0;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private long ID;
    private String title;
    private int length;
    private List<Integer> ratings = new ArrayList<>();
    private double averageOfRatings;

    public Movie(long ID, String title, int length) {
        this.ID = ID;
        this.title = title;
        this.length = length;
    }


    public void addRating(int rating) {
        ratings.add(rating);
        this.averageOfRatings = averageOfRatings();
    }


    private double averageOfRatings() {
        return this.ratings.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics()
                .getAverage();
    }


}
