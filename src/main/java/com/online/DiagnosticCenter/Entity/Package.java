package com.online.DiagnosticCenter.Entity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="package")
public class Package {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="PACKAGE_NAME")
    private String packageName;

    @Column(name="PACKAGE_COST")
    private Double  packageCost;

    @Column(name="LOCATION")
    private String packageLocation;

    @Column(name="OFFER_FROM")
    private Date offerFrom;

    @Column(name="OFFER_TO")
    private Date offerTo;

    @Column(name="IMAGE_PATH")
    private String imagePath;

    @Column(name="Status")
    private Boolean status=true;

    @Column(name="SLOT_COUNT")
    private  Integer slotCount;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="NO_OF_PEOPLE")
    private  Integer noOfPeople;

    @Column(name="created_on")
    public LocalDateTime createdOn;

    @Column(name="last_modified_on")
    private LocalDateTime lastModifiedOn;

    public Package(String packageName, Double packageCost, String packageLocation, String imagePath, Date date1, Date date2, String description, Integer slotCount,Integer noOfPeople) {
        this.packageName=packageName;
        this.packageCost=packageCost;
        this.packageLocation=packageLocation;
        this.imagePath=imagePath;
        this.offerFrom=date1;
        this.offerTo=date2;
        this.description=description;
        this.slotCount=slotCount;
        this.noOfPeople=noOfPeople;


    }

    public Package(Long id, String packageName, Double packageCost, String packageLocation, String imagePath, Date date1, Date date2, String description, Integer slotCount, Integer noOfPeople) {
        this.id=id;
        this.packageName=packageName;
        this.packageCost=packageCost;
        this.packageLocation=packageLocation;
        this.imagePath=imagePath;
        this.offerFrom=date1;
        this.offerTo=date2;
        this.description=description;
        this.slotCount=slotCount;
        this.noOfPeople=noOfPeople;
    }


    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    @PrePersist
    public void setCreatedOn( ) {
        this.createdOn = LocalDateTime.now();
    }

    public LocalDateTime getLastModifiedOn() {
        return lastModifiedOn;
    }

    @PreUpdate
    public void setLastModifiedOn() {
        this.lastModifiedOn = LocalDateTime.now();
    }


}
