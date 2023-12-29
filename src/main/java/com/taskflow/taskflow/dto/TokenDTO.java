package com.taskflow.taskflow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO {
    private UUID id;
    @NotBlank(message = "Token type is required")
    private String tokenType;

    @NotNull(message = "Expiry date is required")
    @Future(message = "Expiry date must be in the future")
    private Date expiryDate;

    @NotNull(message = "User ID is required")
    private UUID userID;
}
