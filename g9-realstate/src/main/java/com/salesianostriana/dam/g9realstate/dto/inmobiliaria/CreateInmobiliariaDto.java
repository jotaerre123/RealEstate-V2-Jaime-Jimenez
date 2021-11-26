package com.salesianostriana.dam.g9realstate.dto.inmobiliaria;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateInmobiliariaDto {
    private Long id;

    private String nombre,telefono,email;
    private List<String> viviendas;

}
