package com.salesianostriana.dam.g9realstate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetInteresaDto {

    private String mensaje;
    private LocalDate createDate;
    private String tituloVivienda;
    private String provinciaVivienda;
    private int numBaniosVivienda;
    private int numHabitacionesVivienda;
    private double precioVivienda;
    private String descripcionVivienda;
    private String avatarVivienda;
}
