package com.online.DiagnosticCenter.Repository;

import com.online.DiagnosticCenter.Entity.Booking;
import com.online.DiagnosticCenter.Entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    List<Booking> findByUserId(Long userId);

    List<Booking> findByPaymentId(String paymentId);

}
