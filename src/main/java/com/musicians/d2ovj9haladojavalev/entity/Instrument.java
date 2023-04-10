package com.musicians.d2ovj9haladojavalev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "instrument")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Instrument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String manufacturer;
    @NotBlank
    private String type;

    //Todo lehetne enum
    @NotBlank
    private String instrumentClass;


}
