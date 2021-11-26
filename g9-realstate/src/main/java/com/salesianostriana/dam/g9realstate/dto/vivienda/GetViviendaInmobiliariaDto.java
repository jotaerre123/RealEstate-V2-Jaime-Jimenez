package com.salesianostriana.dam.g9realstate.dto.vivienda;

import com.salesianostriana.dam.g9realstate.model.TipoVivienda;
import com.salesianostriana.dam.g9realstate.users.dto.GetUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetViviendaInmobiliariaDto extends GetViviendaDto {

    private Long idInmo;
    private String nombreInmo;

    public GetViviendaInmobiliariaDto(String titulo, String provincia, String direccion, int numBanios, int numHabitaciones, double metrosCuadrados, double precio, String descripcion, String avatar, TipoVivienda tipo, String codigoPostal, String latlng, String poblacion, boolean tienePiscina, boolean tieneAscensor, boolean tieneGaraje, GetUserDto propietario, String nombreInmo) {
        super(titulo, provincia, direccion, numBanios, numHabitaciones, metrosCuadrados, precio, descripcion, avatar, tipo, codigoPostal, latlng, poblacion, tienePiscina, tieneAscensor, tieneGaraje, propietario);
        this.nombreInmo = nombreInmo;
    }
}
