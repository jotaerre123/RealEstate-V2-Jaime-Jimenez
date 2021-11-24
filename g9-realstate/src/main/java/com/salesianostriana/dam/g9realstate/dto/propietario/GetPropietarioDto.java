package com.salesianostriana.dam.g9realstate.dto.propietario;

import com.salesianostriana.dam.g9realstate.dto.vivienda.GetViviendaDto;
import com.salesianostriana.dam.g9realstate.dto.vivienda.GetViviendaDtoPequenio;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPropietarioDto {

    private String nombre;
    private String apellidos;
    private String direccion;
    private String email;
    private String telefono;
    private String avatar;
    private String role;
    private List<GetViviendaDtoPequenio> viviendas;
}
