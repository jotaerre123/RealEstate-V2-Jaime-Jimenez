package com.salesianostriana.dam.g9realstate.dto.vivienda;

import com.salesianostriana.dam.g9realstate.model.Vivienda;
import org.springframework.stereotype.Component;

@Component
public class ViviendaDtoConverter {

    public GetViviendaDto viviendaToGetViviendaDTO(Vivienda v) {
        return GetViviendaDto
                .builder()
                .id(v.getId())
                .titulo(v.getTitulo())
                .descripcion(v.getDescripcion())
                .precio(v.getPrecio())
                .metrosCuadrados(v.getMetrosCuadrados())
                .numBanios(v.getNumBanios())
                .numHabitaciones(v.getNumHabitaciones())
                .avatar(v.getAvatar())
                .tipo(v.getTipoVivienda())
                .build();
    }

    public Vivienda createViviendaDtoToVivienda(CreateViviendaDto v){

        return Vivienda.builder()
                .titulo(v.getTitulo())
                .provincia(v.getProvincia())
                .tipoVivienda(v.getTipo())
                .numBanios(v.getNumBanios())
                .numHabitaciones(v.getNumHabitaciones())
                .metrosCuadrados(v.getMetrosCuadrados())
                .precio(v.getPrecio())
                .descripcion(v.getDescripcion())
                .codigoPostal(v.getCodigoPostal())
                .latlng(v.getLatlng())
                .poblacion(v.getPoblacion())
                .tienePiscina(v.isTienePiscina())
                .tieneAscensor(v.isTieneAscensor())
                .tieneGaraje(v.isTieneGaraje())
                .build();
    }

}
