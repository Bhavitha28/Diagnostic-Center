package com.online.DiagnosticCenter.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="booking")
public class Booking {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="USER_ID")
    private Long userId;

    @Column(name="TYPE")
    private String type;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name="QUANTITY")
    private Integer quantity;

    @Column(name="Cost")
    private Double cost;



    @Column(name="ORDER_ID")
    private String orderId;

    @Column(name="PAYMENT_ID")
    private String paymentId;

    @Column(name="created_on")
    public LocalDateTime createdOn;

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    @PrePersist
    public void setCreatedOn( ) {
        this.createdOn = LocalDateTime.now();
    }


}
