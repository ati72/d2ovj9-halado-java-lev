package com.musicians.d2ovj9haladojavalev.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "artist")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name must not be blank!")
    private String name;

    // inthez nincs notblank
    @NotNull(message = "Formed must not be null!")
    @Min(value = 1900, message = "Formed must be greater than 1900")
    private int formed;

    @NotBlank(message = "Genre must not be blank!")
    private String genre;

    @OneToMany(mappedBy = "artist")
    @EqualsAndHashCode.Exclude
    private Set<Album> albums = new HashSet<>();

    @ManyToOne
    // @JsonBackReference
    @JoinColumn(name = "publisher_id",  nullable = true)
    private Publisher publisher;

    @ManyToMany
    @JoinTable(
            name = "member_artist_join",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "musician_id")
    )
    private Set<Musician> members = new HashSet<>();

    public void setArtistMusicianRelationship(Musician musician) {
        members.add(musician);
    }

    public void addAlbum(Album album) {
        this.albums.add(album);
    }


}
