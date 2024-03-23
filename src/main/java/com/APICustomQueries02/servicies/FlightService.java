package com.APICustomQueries02.servicies;

import com.APICustomQueries02.entities.Flight;
import com.APICustomQueries02.entities.StatusEnum;
import com.APICustomQueries02.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class FlightService {
    @Autowired
    private FlightRepository repo;

    public Flight createFlight(){

        Random random = new Random();
        Flight flight = new Flight();
        List<StatusEnum> statusList = Arrays.asList(StatusEnum.values());
        flight.setDescription("description");
        flight.setFromAirport("from airport: " + random.nextInt(100));
        flight.setToAirport("to airport: " + random.nextInt(100));
        flight.setStatus(statusList.get(random.nextInt(statusList.size())));
        return flight;
    }
    public List<Flight> saveFlights(Integer numberOfFLights){
        ArrayList<Flight> flightList = new ArrayList<>();
        IntStream.range(0, numberOfFLights).forEach(i -> flightList.add(createFlight()));
        return repo.saveAll(flightList);
    }
    public List<Flight> saveFlights(){
        ArrayList<Flight> flightList = new ArrayList<>();
        IntStream.range(0, 100).forEach(i -> flightList.add(createFlight()));
        return repo.saveAll(flightList);
    }
    public List<Flight> getAllFlight(){
        return repo.findAll();
    }

    public Page<Flight> getAllFlightAsc(Integer pageNumber , Integer pageSize){
        Pageable pageRequest = PageRequest.of(pageNumber,pageSize);
        return repo.findAllOrderedByFromAirportAsc(pageRequest);
    }
    public List<Flight> getAllFlightOnTime(){
        return repo.findByStatus(StatusEnum.ONTIME);
    }
    public List<Flight> getAllFlightByTwoStatus(StatusEnum statusEnum1,StatusEnum statusEnum2){
        List<Flight> flightList = repo.findByTwoStatus(statusEnum1,statusEnum2);
        return flightList;
    }
}
