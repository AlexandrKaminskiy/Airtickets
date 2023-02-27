package com.company.innowise.airticketsapp.businessservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewFlightDto {

    @Min(1)
    @NotNull
    private Integer seatsCount;

    @NotNull
    @DateTimeFormat
    private LocalDateTime timeDeparture;

    @NotNull
    @DateTimeFormat
    private LocalDateTime timeArrive;

    @Min(0)
    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer fromId;

    @NotNull
    private Integer toId;

}
