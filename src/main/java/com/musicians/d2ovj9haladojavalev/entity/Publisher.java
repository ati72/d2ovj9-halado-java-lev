package com.musicians.d2ovj9haladojavalev.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publisher")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Name must not be blank!")
    private String name;
    @NotBlank(message = "Location must not be blank!")
    private String location;
    @NotBlank(message = "Email address must not be blank")
    private String emailAddress;
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "publisher")
    // @JsonManagedReference
    private Set<Artist> artists = new HashSet<>();

    public void addArtists(Artist artist) {
        this.artists.add(artist);
        artist.setPublisher(this);
    }

    public void deleteArtist(Artist artist) {
        this.artists.remove(artist);
    }

}
