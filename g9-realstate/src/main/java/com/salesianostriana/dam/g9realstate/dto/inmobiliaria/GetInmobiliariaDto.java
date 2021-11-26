package com.salesianostriana.dam.g9realstate.dto.inmobiliaria;

import com.salesianostriana.dam.g9realstate.model.Vivienda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetInmobiliariaDto {
    private Long id;
    private String nombre, email, telefono;
    private Vivienda vivienda;
}
