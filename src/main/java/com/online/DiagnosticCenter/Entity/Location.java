package com.online.DiagnosticCenter.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="location")
public class Location {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="location")
    private String location;

@Column(name="COUNT")
private Integer count;

    @Column(name="isActive")
    private Boolean isActive=true;
}
