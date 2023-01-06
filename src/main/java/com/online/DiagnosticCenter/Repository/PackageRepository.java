package com.online.DiagnosticCenter.Repository;

import com.online.DiagnosticCenter.Entity.Package;
import com.online.DiagnosticCenter.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends CrudRepository<Package,Long> {

    List<Package> findByStatus(boolean b);
    List<Package> findAll();
    Package findByIdAndStatus(Long id, boolean b);
}
