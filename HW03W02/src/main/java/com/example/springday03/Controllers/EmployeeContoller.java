package com.example.springday03.Controllers;

import com.example.springday03.models.Employee;
import com.example.springday03.models.ResponseAPI;
import com.example.springday03.models.Ride;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("employee/")
public class EmployeeContoller {
    List<Employee> emps = new ArrayList<>();

    @GetMapping
    public ResponseEntity getEmployees(){
        return ResponseEntity.status(200).body(emps);
    }
    @PostMapping
    public ResponseEntity addEmployee(@RequestBody @Valid Employee e, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new ResponseAPI(
                    "Invalid data entered", 400,
                    error.getFieldError().getDefaultMessage()));
        }
        emps.add(e);
        return ResponseEntity.status(201).body(new ResponseAPI(
                e.getEmpName() + " has been added", 201)
        );
    }
    @PutMapping("{index}")
    public ResponseEntity updateRide(@RequestBody @Valid Employee e, @PathVariable Integer index, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new ResponseAPI(
                    "Invalid data entered", 400,
                    error.getFieldError().getDefaultMessage()));
        }
        emps.set(index, e);
        return ResponseEntity.status(201).body(new ResponseAPI(
                e.getEmpName() + "'s data have been Updated", 201)
        );
    }
    @DeleteMapping("{index}")
    public ResponseEntity deleteRide(@RequestBody @Valid Employee e, @PathVariable Integer index, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new ResponseAPI(
                    "Invalid data entered", 400,
                    error.getFieldError().getDefaultMessage()));
        }
        emps.set(index, e);
        return ResponseEntity.status(201).body(new ResponseAPI(
                " Employee has been deleted", 201)
        );
    }

    @PutMapping("leave/{days}")
    public ResponseEntity requestLeave(@RequestBody String id,@PathVariable Integer days){
        Integer daysLeft = 0;
        String name = "";
        for(Employee em : emps){
            if(em.getEmpID().equals(id)){
                if (em.getOnLeave() == true) {
                    return ResponseEntity.status(400).body(new ResponseAPI(
                            "Employee is already on leave", 400));
                }
                em.setOnLeave(true);
                em.setAnnualLeave(em.getAnnualLeave()-days);
                daysLeft = em.getAnnualLeave();
                name = em.getEmpName();
                break;
            }
        }

        return ResponseEntity.status(201).body(new ResponseAPI(
                name + " is now on annual leave and has " + daysLeft + " left", 201)
        );
    }





}
