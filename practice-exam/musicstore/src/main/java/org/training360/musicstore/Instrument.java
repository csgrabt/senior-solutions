package org.training360.musicstore;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instrument {
    private long id;
    private String brand;
    private Type type;
    private double price;
    private LocalDate dateOfPublication;

}
