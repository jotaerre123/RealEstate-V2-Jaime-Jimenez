package com.salesianostriana.dam.g9realstate.dto;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.salesianostriana.dam.g9realstate.model.Propietario;
import com.salesianostriana.dam.g9realstate.model.Vivienda;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PropietarioDtoConverter {

    public Propietario createPropietarioDtoToPropietario(CreatePropietarioDto p){
        return new Propietario(
                p.getNombre(),
                p.getApellidos(),
                p.getDireccion(),
                p.getEmail(),
                p.getTelefono(),
                p.getAvatar()
        );
    }
    public GetPropietarioDto propietarioToGetPropietarioDto(Propietario p){
        return GetPropietarioDto
                .builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .apellidos(p.getApellidos())
                .direccion(p.getDireccion())
                .email(p.getEmail())
                .telefono(p.getTelefono())
                .avatar(p.getAvatar())
                .build();
    }

    public GetPropietarioViviendaDto propietarioToGetPropietarioViviendaDto (Propietario p) {

        return GetPropietarioViviendaDto
                .builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .apellidos(p.getApellidos())
                .direccion(p.getDireccion())
                .email(p.getEmail())
                .telefono(p.getTelefono())
                .viviendas(p.getListaViviendas().stream().map(v -> new GetViviendaDto(v.getId(),v.getTitulo()
                        ,v.getProvincia(),
                        v.getNumBanios(), v.getNumHabitaciones(),v.getMetrosCuadrados(),v.getPrecio()
                        ,v.getDescripcion(),v.getAvatar())).toList())
                .build();
    }

}
