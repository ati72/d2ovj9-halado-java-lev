package com.musicians.d2ovj9haladojavalev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlbumDTO {
    Long id;
    String artist;
    String title;
    String releaseYear;
}
