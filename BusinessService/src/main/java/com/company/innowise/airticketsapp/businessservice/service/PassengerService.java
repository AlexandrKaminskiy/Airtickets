package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.model.Passenger;
import com.company.innowise.airticketsapp.businessservice.model.Ticket;
import com.company.innowise.airticketsapp.businessservice.repository.PassengerRepository;
import com.company.innowise.airticketsapp.businessservice.repository.quetyUtils.builderimpl.PassengerSpecificationBuilder;
import com.company.innowise.airticketsapp.businessservice.repository.quetyUtils.builderimpl.TicketSpecificationBuilder;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;

    private final PassengerSpecificationBuilder passengerSpecificationBuilder;
    private final TicketSpecificationBuilder ticketSpecificationBuilder;

    public List<Passenger> getAll() {
        return passengerRepository.findAll();
    }

    public List<Passenger> getAll(Map<String, Object> parameters) {
        Specification<Passenger> specification = getSpecification(parameters);
        return passengerRepository.findAll(specification);
    }

    public Passenger getPassenger(Long id) {
        return passengerRepository.getReferenceById(id);
    }

    public Passenger addPassenger(Passenger passenger) {
        passengerRepository.save(passenger);
        return passenger;
    }

    public void deletePassenger(long id) {
        passengerRepository.deleteById(id);
    }

    private Specification<Passenger> getSpecification(Map<String, Object> parameters) {
        return (root, query, criteriaBuilder) -> {

            Specification<Passenger> airportSpecification = passengerSpecificationBuilder.getSpecification(Optional.empty(), parameters);
            Join<Passenger, Ticket> passengerTicketJoin = root.join("ticket");
            Specification<Passenger> ticketSpecification = ticketSpecificationBuilder.getSpecification(Optional.of(passengerTicketJoin), parameters);
            return airportSpecification.and(airportSpecification)
                    .and(ticketSpecification)
                    .toPredicate(root, query, criteriaBuilder);

        };
    }

}
