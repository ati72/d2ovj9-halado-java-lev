package com.musicians.d2ovj9haladojavalev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "album")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String year;
    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;


    // TODO: artist összekapcs


}
