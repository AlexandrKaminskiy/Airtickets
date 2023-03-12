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
public class AirportFindingDto {

    private String town;
    private String country;
    private String name;

    @NotNull
    private PageRequest pageRequest;

}
