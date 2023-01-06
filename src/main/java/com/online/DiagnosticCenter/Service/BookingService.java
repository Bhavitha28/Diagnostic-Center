package com.online.DiagnosticCenter.Service;

import com.online.DiagnosticCenter.Entity.Booking;
import com.online.DiagnosticCenter.Entity.Cart;
import com.online.DiagnosticCenter.Entity.Location;
import com.online.DiagnosticCenter.Entity.Package;
import com.online.DiagnosticCenter.Exception.InvalidDataException;
import com.online.DiagnosticCenter.Exception.ResourceNotFoundException;

import com.online.DiagnosticCenter.Repository.BookingRepository;
import com.online.DiagnosticCenter.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private PackageService packageService;


    public Double calculateTotalBill(int noOfSlots,Long packageId) {

        Package packageEntity = packageService.findPackage(packageId);

        if (packageEntity == null) {
            throw new ResourceNotFoundException("package doest not exist");
        }
        Double totalBill= noOfSlots * packageEntity.getPackageCost();
        return totalBill;
    }


    public Booking storeBooking(Booking bookingEntity) {


        Package packageEntity = packageService.findPackage(bookingEntity.getProductId());
        int availableSlots = packageEntity.getSlotCount();
        int noOfSlots = bookingEntity.getQuantity();

        // check slots available or not & noOfSlots are more than available slots
        if (availableSlots <= 0 || availableSlots < noOfSlots) {
            throw new InvalidDataException("selected quantity of slots not available");
        } else {


            // update -slots in package by subtracting -noOfSlots
            packageEntity.setSlotCount(packageEntity.getSlotCount() - noOfSlots);
            packageService.updatePackageSlot(packageEntity);

            bookingEntity.setCost(calculateTotalBill(noOfSlots, packageEntity.getId()));

            bookingEntity.setType("Buy");

            Booking booking= bookingRepository.save(bookingEntity);

                this.addCount(packageEntity.getPackageLocation());

return booking;
        }
    }

    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();

    }

    public void addCount(String location){

        Location locationEntity =locationRepository.findByLocationEquals(location);
        locationEntity.setCount(locationEntity.getCount() + 1);
        locationRepository.save(locationEntity);
    }

    public List<Booking> findBooking(Long userId) {
        return bookingRepository.findByUserId(userId);

    }

    public List<Booking> findOneBooking(String paymentId) {
        return bookingRepository.findByPaymentId(paymentId);
    }


}
