package com.online.DiagnosticCenter.Service;

import com.online.DiagnosticCenter.Entity.Location;
import com.online.DiagnosticCenter.Entity.Package;
import com.online.DiagnosticCenter.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;
    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }


    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location findLocation(Long id) {
        return locationRepository.findAllByIdAndIsActive(id,true);
    }

    public Location updateLocation(Location location) {
        return locationRepository.save(location);
    }


    public Location deleteLocationById(Long id) {
        Location location = this.findLocation(id);
       location.setIsActive(false);
        this.locationRepository.save(location);
        return location;
    }

    public Location findInactiveLocation(Long id) {


        return locationRepository.findByIdAndIsActive(id,false);
    }
    public boolean undoDeleteById(Long id) {
        Location location = this.findInactiveLocation(id);
        location.setIsActive(true);
        Location activatedLocation = locationRepository.save(location);

        if(activatedLocation.getIsActive())
            return  true;
        return false;
    }

}
