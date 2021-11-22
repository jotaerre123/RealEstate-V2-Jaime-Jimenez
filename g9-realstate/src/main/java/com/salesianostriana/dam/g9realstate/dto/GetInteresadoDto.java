package com.salesianostriana.dam.g9realstate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GetInteresadoDto {

    private Long id;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String email;
    private String telefono;
    private String avatar;

}
