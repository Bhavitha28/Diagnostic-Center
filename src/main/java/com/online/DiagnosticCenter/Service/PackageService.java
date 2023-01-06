package com.online.DiagnosticCenter.Service;

import com.online.DiagnosticCenter.Entity.Package;
import com.online.DiagnosticCenter.Entity.User;

import com.online.DiagnosticCenter.Repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;

    public Package addPackage(Package package1, MultipartFile file) {
        return packageRepository.save(package1);
    }


    public List<Package> findAllPackages() {
        return packageRepository.findAll();
    }

    public List<Package> findActivePackages() {
        Date date=new Date();
        return packageRepository.findByStatus(true).stream().
                filter((packageEntity) -> {
                    return (packageEntity.getOfferFrom().after(date)
                            || packageEntity.getOfferFrom() == date);
                }).collect(Collectors.toList());
    }

    public List<Package> findInActivePackages() {
        return packageRepository.findByStatus(false);
    }

    public Package updatePackage(Package package1,MultipartFile file) {
        return packageRepository.save(package1);
    }

    public Package updatePackageSlot(Package package1) {
        return packageRepository.save(package1);
    }

    public Package findPackage(Long id) {
        return packageRepository.findByIdAndStatus(id,true);

    }
    public Package deletePackageById(Long id) {
        Package package1 = this.findPackage(id);
        package1.setStatus(false);
        this.packageRepository.save(package1);
        return package1;
    }

    public Package findInactivePackage(Long id) {


        return packageRepository.findByIdAndStatus(id,false);
    }
    public boolean undoDeleteById(Long id) {
        Package package1 = this.findInactivePackage(id);
        package1.setStatus(true);
        Package activatedPackage = packageRepository.save(package1);

        if(activatedPackage.getStatus())
            return  true;
        return false;
    }
}
