package com.online.DiagnosticCenter.Controller;

import com.online.DiagnosticCenter.Entity.Booking;
import com.online.DiagnosticCenter.Entity.Package;
import com.online.DiagnosticCenter.Entity.User;

import com.online.DiagnosticCenter.Service.BookingService;
import com.online.DiagnosticCenter.Service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Booking")
@CrossOrigin(origins = "*")
public class BookingController {
   @Autowired
   private BookingService bookingService;

   @Autowired
   private PackageService packageService;




   @GetMapping("/calculateBill/{noOfSlots}/{packageId}")
   public  Double  calculateBill(@PathVariable("noOfSlots") int noOfSlots, @PathVariable("packageId") Long packageId)  {
      return bookingService.calculateTotalBill(noOfSlots,packageId);
   }

   @PostMapping("/AddBooking")
   public Booking addBooking(@RequestBody Booking bookingEntity)  {
      return  bookingService.storeBooking(bookingEntity);
   }




   @GetMapping("/getAllBookings")
   public List<Booking> findAllBookings() {
      return bookingService.findAllBookings();
   }

   @GetMapping("/getOneUserBookings/{userId}")
   public List<Booking> findOneUserBookings(@PathVariable("userId") Long userId){


         return bookingService.findBooking(userId);
      }

   @GetMapping("/getOneBooking/{paymentId}")
   public List<Booking> findOneBooking(@PathVariable("paymentId") String paymentId){


      return bookingService.findOneBooking(paymentId);
   }
}
