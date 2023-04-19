package com.musicians.d2ovj9haladojavalev.dto;

import com.musicians.d2ovj9haladojavalev.entity.Album;
import com.musicians.d2ovj9haladojavalev.entity.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArtistDTO {
    Long id;
    String name;
    int formed;
    String genre;
    List<String> albums;
    String publisher;
    List<String> members;

    // TODO: add members

}