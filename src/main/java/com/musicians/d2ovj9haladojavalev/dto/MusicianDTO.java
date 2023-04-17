package com.musicians.d2ovj9haladojavalev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MusicianDTO {
    Long id;
    String firstName;
    String lastName;
    int yearOfBirth;
    String instrument;
    List<String> associatedActs;
}
