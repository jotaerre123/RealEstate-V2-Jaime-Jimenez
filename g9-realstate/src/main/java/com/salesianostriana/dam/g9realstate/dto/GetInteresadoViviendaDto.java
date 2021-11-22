package com.salesianostriana.dam.g9realstate.dto;

import com.salesianostriana.dam.g9realstate.model.Interesa;
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
public class GetInteresadoViviendaDto {

    private Long id;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String email;
    private String telefono;
    private List <GetInteresaDto> vivienda;

}
