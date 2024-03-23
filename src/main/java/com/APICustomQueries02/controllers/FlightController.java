package com.APICustomQueries02.controllers;

import com.APICustomQueries02.entities.Flight;
import com.APICustomQueries02.entities.StatusEnum;
import com.APICustomQueries02.servicies.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService service;
    @PostMapping("/generaterandom/{flightToGenerate}")
    public ResponseEntity<List<Flight>> generateRandomFlights(@PathVariable Integer flightToGenerate){
        return ResponseEntity.ok().body(service.saveFlights(flightToGenerate));
    }
    @PostMapping("/generaterandom")
    public ResponseEntity<List<Flight>> generateRandomFlights(){
        return ResponseEntity.ok().body(service.saveFlights());
    }
    @GetMapping("/getall")
    public ResponseEntity<List<Flight>> getAllFlights(){
        return ResponseEntity.ok().body(service.getAllFlight());
    }
    @GetMapping("/getallasc")
    public ResponseEntity<Page<Flight>> getAllFlightsAsc(@RequestParam Integer pageNumber , @RequestParam Integer pageSize){
        return ResponseEntity.ok().body(service.getAllFlightAsc(pageNumber,pageSize));
    }
    @GetMapping("/getall/ontime")
    public ResponseEntity<List<Flight>> getAllFlightOnTime(){
        return ResponseEntity.ok().body(service.getAllFlightOnTime());
    }
    @GetMapping("/getallbytwostatus/")
    public ResponseEntity<List<Flight>> getAllFlightsAsc(@RequestParam StatusEnum firstStatus, @RequestParam StatusEnum secondStatus){
        List<Flight> flightList = service.getAllFlightByTwoStatus(firstStatus,secondStatus);
        return ResponseEntity.ok().body(flightList);
    }

}
