package com.salesianostriana.dam.g9realstate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data@NoArgsConstructor@AllArgsConstructor
public class CreateInteresadoInteresaDto {

    private String nombre, apellidos, direccion, email, telefono, avatar;
    private LocalDate createdDate;
    private String mensaje;
    private Long viviendaId;
}
