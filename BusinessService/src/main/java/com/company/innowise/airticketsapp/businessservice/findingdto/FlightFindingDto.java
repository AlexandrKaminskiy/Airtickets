package com.company.innowise.airticketsapp.businessservice.findingdto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightFindingDto {

    @NotNull
    private String to;

    @NotNull
    private String from;

    @NotNull
    private PageRequest pageRequest;

}
