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
public class TicketFindingDto {

    private Double priceFrom;
    private Double priceTo;

    @NotNull
    private PageRequest pageRequest;

}
