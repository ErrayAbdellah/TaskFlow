package com.taskflow.taskflow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO {
    private Long tokenID;
    private String tokenType;
    private Date expiryDate;
    private Long userID;
}
