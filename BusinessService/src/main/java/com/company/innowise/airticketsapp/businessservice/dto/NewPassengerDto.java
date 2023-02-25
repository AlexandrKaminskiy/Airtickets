package com.company.innowise.airticketsapp.businessservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewPassengerDto {

    @Email
    @NotNull
    private String email;

    @NotNull
    @Length(min = 8, max = 72)
    private String password;

    @NotNull
    @Length(max = 30)
    private String username;

    @NotNull
    @Length(min = 9)
    private String passport;

    @NotNull
    @Length(min = 1)
    private String firstname;

    @NotNull
    @Length(min = 1)
    private String lastname;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

}
