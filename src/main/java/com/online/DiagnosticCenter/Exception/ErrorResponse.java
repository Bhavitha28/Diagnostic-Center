package com.online.DiagnosticCenter.Exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timeStamp;

    private String message;
    private int status;
    private List<String> errorList;

    private ErrorResponse() {
        timeStamp = LocalDateTime.now();
    }

    public ErrorResponse(String message, int status) {
        this();
        this.message = message;
        this.status = status;
    }

    public ErrorResponse(String message, List<String> errorList) {
        this();
        this.message = message;
        this.errorList = errorList;
    }
}
