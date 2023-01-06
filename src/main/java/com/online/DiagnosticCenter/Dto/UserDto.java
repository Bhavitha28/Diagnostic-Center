package com.online.DiagnosticCenter.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Data
@Getter
@Setter
public class UserDto {


    private String password;


    private String userName;


    private String userType;


    private  String name;


    private String mobileNumber;


    private String emailId;



}
