package com.online.DiagnosticCenter.Entity;


import lombok.*;


import javax.persistence.*;


@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private Long id;



    @Column(name="PASSWORD")
    private String password;

    @Column(name="USERNAME")
    private String userName;

    @Column(name="USER_TYPE")
    private String userType;

    @Column(name="FULL_NAME")
    private  String name;

    @Column(name="MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name="EMAIL_ID")
    private String emailId;

    @Column(name="isActive")
    private Boolean isActive=true;

}


