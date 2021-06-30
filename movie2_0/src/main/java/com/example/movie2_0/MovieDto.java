package com.example.movie2_0;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
   // private long ID;
    private String title;
    private int length;
    //private List<Integer> ratings;
    private double averageOfRatings;

}
