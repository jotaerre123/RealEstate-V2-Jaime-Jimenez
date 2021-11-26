package com.salesianostriana.dam.g9realstate.dto.vivienda;

import com.salesianostriana.dam.g9realstate.model.TipoVivienda;
import com.salesianostriana.dam.g9realstate.users.dto.GetUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetViviendaInmobiliariaDto2 {

    private Long id;
    private String titulo;
    private String provincia;
    private String direccion;
    private int numBanios;
    private int numHabitaciones;
    private double metrosCuadrados;
    private double precio;
    private String descripcion;
    private String avatar;
    private TipoVivienda tipo;
    private String codigoPostal;
    private String latlng;
    private String poblacion;
    private boolean tienePiscina;
    private boolean tieneAscensor;
    private boolean tieneGaraje;
    private String nombrePropietario;

}
