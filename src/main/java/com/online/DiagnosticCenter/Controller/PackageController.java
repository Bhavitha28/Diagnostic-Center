package com.online.DiagnosticCenter.Controller;

import com.online.DiagnosticCenter.Entity.Package;
import com.online.DiagnosticCenter.Entity.User;
import com.online.DiagnosticCenter.Service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Package")
@CrossOrigin(origins = "*")
public class PackageController {
    @Autowired
    private PackageService packageService;

//    @PostMapping("/add")
//
//    public Package addPackage(@RequestBody Package package1)  {
//        return packageService.addPackage(package1);
//    }

    @PostMapping("/add/{packageName}/{packageCost}/{packageLocation}/{offerFrom}/{offerTo}/{description}/{slotCount}/{noOfPeople}")
    public Package storePackage(@RequestParam("file") MultipartFile file,
                                      @PathVariable("packageName") String packageName,
                                      @PathVariable("packageCost") Double packageCost ,
                                      @PathVariable("packageLocation") String packageLocation ,
                                      @PathVariable("offerFrom") String offerFrom ,
                                      @PathVariable("offerTo") String offerTo,
                                      @PathVariable("description") String description ,
                                      @PathVariable("slotCount") Integer slotCount,
                                @PathVariable("noOfPeople") Integer noOfPeople
                                      ) throws IOException, ParseException {


        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(offerFrom);
        Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(offerTo);

        Package packageEntity = new Package(packageName,packageCost,packageLocation,file.getOriginalFilename(),date1,date2,description,slotCount,noOfPeople);
        return  packageService.addPackage(packageEntity ,file);
    }



    @GetMapping("/getAllPackages")
    public List<Package> findAllPackages() {
        return packageService.findAllPackages();
    }

    @GetMapping("/getActivePackages")
    public List<Package> findActivePackages() {
        return packageService.findActivePackages();
    }

    @GetMapping("/getInActivePackages")
    public List<Package> findInActiveEmployees() {
        return packageService.findInActivePackages();
    }

    @GetMapping("/getOnePackage/{id}")
    public Package findPackage(@PathVariable("id") Long id) {

        return packageService.findPackage(id);
    }

    @PutMapping("/updatePackage/{id}/{packageName}/{packageCost}/{packageLocation}/{offerFrom}/{offerTo}/{description}/{slotCount}/{noOfPeople}")
    public Package updatePackage(@RequestParam("file") MultipartFile file,
                                 @PathVariable("id") Long id,
                                 @PathVariable("packageName") String packageName,
                                 @PathVariable("packageCost") Double packageCost ,
                                 @PathVariable("packageLocation") String packageLocation ,
                                 @PathVariable("offerFrom") String offerFrom ,
                                 @PathVariable("offerTo") String offerTo,
                                 @PathVariable("description") String description ,
                                 @PathVariable("slotCount") Integer slotCount,
                                 @PathVariable("noOfPeople") Integer noOfPeople
    ) throws IOException, ParseException {


        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(offerFrom);
        Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(offerTo);

        Package packageEntity = new Package(id,packageName,packageCost,packageLocation,file.getOriginalFilename(),date1,date2,description,slotCount,noOfPeople);
        return  packageService.updatePackage(packageEntity ,file);
    }
    @DeleteMapping("/deletePackage/{id}")
    public Package deleteUser(@PathVariable("id") Long id) {
        return packageService.deletePackageById(id);
    }


    @GetMapping("/undoDelete/{id}")
    public boolean undoDelete(@PathVariable("id") Long id) {
        return packageService.undoDeleteById(id);
    }
}
