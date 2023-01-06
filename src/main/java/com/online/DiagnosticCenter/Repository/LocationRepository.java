package com.online.DiagnosticCenter.Repository;

import com.online.DiagnosticCenter.Entity.Location;
import com.online.DiagnosticCenter.Entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {

    Location findAllByIdAndIsActive(Long id, boolean b);

    Location findByIdAndIsActive(Long id, boolean b);


    @Query(value = "SELECT * FROM location WHERE location=?", nativeQuery = true)
    public Location findByLocationEquals(String location);
}
