package com.salesianostriana.dam.g9realstate.dto.vivienda;

import com.salesianostriana.dam.g9realstate.model.TipoVivienda;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetViviendaInteresaDto {

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
    private boolean interesa;

}
