package com.salesianostriana.dam.g9realstate.dto.propietario;

import com.salesianostriana.dam.g9realstate.dto.vivienda.GetViviendaDtoPequenio;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPropietarioViviendaDto {

    private List<GetViviendaDtoPequenio> vivienda;

}
