package com.musicians.d2ovj9haladojavalev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PublisherDTO {
    Long id;
    String name;
    String location;
    List<String> artists;

}
