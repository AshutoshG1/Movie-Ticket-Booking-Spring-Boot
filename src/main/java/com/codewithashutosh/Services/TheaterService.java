package com.codewithashutosh.Services;


import com.codewithashutosh.Dtos.RequestDtos.TheaterEntryDto;
import com.codewithashutosh.Dtos.RequestDtos.TheaterSeatEntryDto;
import com.codewithashutosh.Enums.SeatType;
import com.codewithashutosh.Exceptions.TheaterIsNotPresentOnThisAddress;
import com.codewithashutosh.Exceptions.TheaterIsPresentOnThatAddress;
import com.codewithashutosh.Models.Theater;
import com.codewithashutosh.Models.TheaterSeat;
import com.codewithashutosh.Repositories.TheaterRepository;
import com.codewithashutosh.Transformers.TheaterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;
    public String addTheater(TheaterEntryDto theaterEntryDto) throws TheaterIsPresentOnThatAddress{
        if(theaterRepository.findByAddress(theaterEntryDto.getAddress()) != null) {
            throw new TheaterIsPresentOnThatAddress();
        }
        Theater theater = TheaterTransformer.theaterDtoToTheater(theaterEntryDto);

        theaterRepository.save(theater);
        return "Theater has been saved Successfully";
    }

    public String addTheaterSeat(TheaterSeatEntryDto entryDto) throws TheaterIsNotPresentOnThisAddress{
        if(theaterRepository.findByAddress(entryDto.getAddress()) == null) {
            throw new TheaterIsNotPresentOnThisAddress();
        }
        Integer noOfSeatsInRow = entryDto.getNoOfSeatInRow();
        Integer noOfPremiumSeats = entryDto.getNoOfPremiumSeat();
        Integer noOfClassicSeat = entryDto.getNoOfClassicSeat();
        String address = entryDto.getAddress();

        Theater theater = theaterRepository.findByAddress(address);

        List<TheaterSeat> seatList = theater.getTheaterSeatList();

        int counter = 1;
        int fill = 0;
        char ch = 'A';

        for(int i = 1; i <= noOfClassicSeat; i++) {
            String seatNo = Integer.toString(counter)+ch;

            ch++;
            fill++;
            if(fill == noOfSeatsInRow) {
                fill = 0;
                counter++;
                ch = 'A';
            }

            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.CLASSIC);
            theaterSeat.setTheater(theater);
            seatList.add(theaterSeat);
        }

        for(int i = 1; i <= noOfPremiumSeats; i++) {
            String seatNo = Integer.toString(counter)+ch;

            ch++;
            fill++;
            if(fill == noOfSeatsInRow) {
                fill = 0;
                counter++;
                ch = 'A';
            }

            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.PREMIUM);
            theaterSeat.setTheater(theater);
            seatList.add(theaterSeat);
        }

        theaterRepository.save(theater);

        return "Theater Seats have been added successfully";
    }
}
