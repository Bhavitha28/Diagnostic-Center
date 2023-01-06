package com.online.DiagnosticCenter.Controller;

import com.online.DiagnosticCenter.Entity.Booking;
import com.online.DiagnosticCenter.Entity.Location;
import com.online.DiagnosticCenter.Entity.Package;
import com.online.DiagnosticCenter.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Admin")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("/addLocation")
    public Location addLocation(@RequestBody Location location){
        return locationService.addLocation(location);
    }

    @GetMapping("/getAllLocations")
    public List<Location> getAllLocations(){
        return locationService.getAllLocations();
    }

    @GetMapping("/getOneLocation/{id}")
    public Location findOneLocation(@PathVariable("id") Long id){


        return locationService.findLocation(id);
    }

    @PutMapping("/updateLocation")
    public Location updateLocation(@RequestBody Location location) {
        return locationService.updateLocation(location);
    }

    @DeleteMapping("/deleteLocation/{id}")
    public Location deleteUser(@PathVariable("id") Long id) {
        return locationService.deleteLocationById(id);
    }

    @GetMapping("/undoDelete/{id}")
    public boolean undoDelete(@PathVariable("id") Long id) {
        return locationService.undoDeleteById(id);
    }
}

