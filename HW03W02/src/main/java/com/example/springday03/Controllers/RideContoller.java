package com.example.springday03.Controllers;

import com.example.springday03.models.ResponseAPI;
import com.example.springday03.models.Ride;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/ride/")

public class RideContoller {
    List<Ride> rides = new ArrayList<>();
    @GetMapping
    public ResponseEntity getRides(){
        return ResponseEntity.status(200).body(rides);
    }

    @PostMapping
    public ResponseEntity addRide(@RequestBody @Valid Ride ride, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new ResponseAPI(
                    "Invalid data entered", 400,
                    error.getFieldError().getDefaultMessage()));
    }
        rides.add(ride);
        return ResponseEntity.status(201).body(new ResponseAPI(
                        ride.getRideName() + " has been added", 201)
        );
    }

    @PutMapping("{index}")
    public ResponseEntity updateRide(@RequestBody @Valid Ride ride, @PathVariable Integer index, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new ResponseAPI(
                    "Invalid data entered", 400,
                    error.getFieldError().getDefaultMessage()));
        }
        rides.set(index, ride);
        return ResponseEntity.status(201).body(new ResponseAPI(
                ride.getRideName() + " has been Updated", 201)
        );
    }
    @DeleteMapping("{index}")
    public ResponseEntity deleteRide(@RequestBody @Valid Ride ride, @PathVariable Integer index, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new ResponseAPI(
                    "Invalid data entered", 400,
                    error.getFieldError().getDefaultMessage()));
        }
        rides.set(index, ride);
        return ResponseEntity.status(201).body(new ResponseAPI(
                " ride has been deleted", 201)
        );
    }

    @PutMapping("sell/{amount}")
    public ResponseEntity sellTicket(@RequestBody String id, @PathVariable Integer amount){
        Ride ri = new Ride();
        for(Ride r: rides){
            if(r.getRideID().equals(id)){
                if(r.getTickets() ==0 || amount <0 || amount < r.getPrice()){
                    return ResponseEntity.status(400).body(new ResponseAPI(
                            "No more tickets to sell or you entered a negative number", 400));
                }
                r.setTickets(r.getTickets()-amount);
                return ResponseEntity.status(201).body(new ResponseAPI(
                        r.getTickets() + "tickets left", 201
                ));
            }
        }
        return ResponseEntity.status(400).body(new ResponseAPI(
                "Ride type not found", 400));
    }
}




