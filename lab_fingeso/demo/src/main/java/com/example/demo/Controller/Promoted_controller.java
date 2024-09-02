package com.example.demo.Controller;

import com.example.demo.Entity.Promoted;
import com.example.demo.Entity.Property;
import com.example.demo.Service.Promoted_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/promoted")
public class Promoted_controller {
    @Autowired
    private Promoted_service promotedservice;

    @PostMapping("/top_10")
    public int Promoted_Property(
            @RequestParam long PropertyId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        int result = promotedservice.Top_10(date, PropertyId);
        return result;
    }
    @GetMapping("/getTop10")
    public List<Property> Promoted_Property_date(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return promotedservice.getTop10(date);
    }
@GetMapping("/no-availability-dates")
    public ResponseEntity<List<LocalDate>> getNoAvailabilityDates() {
        try {
            List<LocalDate> noAvailabilityDates = promotedservice.getDatesWithNoAvailability();
            return ResponseEntity.ok(noAvailabilityDates);
        } catch (Exception e) {
            // Manejar la excepción y/o registrar el error
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
