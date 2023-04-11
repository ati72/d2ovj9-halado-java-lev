package com.musicians.d2ovj9haladojavalev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    @Min(1920)
    @Max(2023)
    private int yearOfBirth;
    @NotBlank
    private String instrument;
    @ManyToMany
    private Set<Artist> associatedActs;
}
