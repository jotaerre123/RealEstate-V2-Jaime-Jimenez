package com.salesianostriana.dam.g9realstate.dto;

import com.salesianostriana.dam.g9realstate.model.TipoVivienda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetViviendaIdDto {

    private Long id;
    private String titulo;
    private String provincia;
    private TipoVivienda tipoVivienda;
    private int numBanios;
    private int numHabitaciones;
    private double metrosCuadrados;
    private double precio;
    private String descripcion;
    private String avatar;
    private String direccion;
    private String codigoPostal;
    private String latlng;
    private String poblacion;
    private boolean tienePiscina;
    private boolean tieneAscensor;
    private boolean tieneGaraje;
    private Long propietarioId;
}
