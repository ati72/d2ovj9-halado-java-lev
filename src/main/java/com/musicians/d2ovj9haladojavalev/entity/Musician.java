package com.musicians.d2ovj9haladojavalev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "musician")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Musician {

    // todo: manytomany -> egy zenész szerepelhet több zenekarban, egy zenekarban több zenész játszik
    // todo: regex meg ilyenek
    // todo: táblák összekapcsolása, DTO implementálás
    // todo: exceptions

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "First name must not be blank!")
    private String firstName;
    @NotBlank(message = "Last name must not be blank!")
    private String lastName;
    @NotNull(message = "Year of birt must not be null!")
    @Min(value = 1920, message = "Year of birth must be greater than 1920!")
    @Max(value = 2023, message = "Year of birth must be less than 2023")
    private int yearOfBirth;
    @NotBlank(message = "Instrument must not be blank!")
    private String instrument;
    @ManyToMany
    @EqualsAndHashCode.Exclude
    private Set<Artist> associatedActs;

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    public void deleteArtist(Artist artist) {
        this.associatedActs.remove(artist);
    }
}
