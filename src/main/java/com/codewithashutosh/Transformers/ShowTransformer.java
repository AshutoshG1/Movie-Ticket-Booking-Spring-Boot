package com.codewithashutosh.Transformers;


import com.codewithashutosh.Dtos.RequestDtos.ShowEntryDto;
import com.codewithashutosh.Models.Show;

public class ShowTransformer {

    public static Show showDtoToShow(ShowEntryDto showEntryDto) {
        Show show = Show.builder()
                .time(showEntryDto.getShowStartTime())
                .date(showEntryDto.getShowDate())
                .build();

        return show;
    }
}
