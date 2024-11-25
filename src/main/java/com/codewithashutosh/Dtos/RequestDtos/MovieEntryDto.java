package com.codewithashutosh.Dtos.RequestDtos;


import com.codewithashutosh.Enums.Genre;
import com.codewithashutosh.Enums.Language;
import lombok.Data;

import java.sql.Date;

@Data
public class MovieEntryDto {
    private String movieName;
    private Integer duration;
    private Double rating;
    private Date releaseDate;
    private Genre genre;
    private Language language;
}
