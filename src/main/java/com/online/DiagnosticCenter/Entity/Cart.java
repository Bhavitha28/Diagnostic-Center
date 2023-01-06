package com.online.DiagnosticCenter.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="USERID")
    private  Long userId;

    @Column(name="PACKAGEID")
    private Long packageId;

    @Column(name="Quantity")
    private Integer quantity;

    @Column(name="COST")
    private Double cost;

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

}
