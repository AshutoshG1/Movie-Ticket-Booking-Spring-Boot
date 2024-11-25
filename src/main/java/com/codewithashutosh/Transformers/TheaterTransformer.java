package com.codewithashutosh.Transformers;


import com.codewithashutosh.Dtos.RequestDtos.TheaterEntryDto;
import com.codewithashutosh.Models.Theater;

public class TheaterTransformer {

    public static Theater theaterDtoToTheater(TheaterEntryDto entryDto) {
        Theater theater = Theater.builder()
                .name(entryDto.getName())
                .address(entryDto.getAddress())
                .build();
        return theater;
    }
}
