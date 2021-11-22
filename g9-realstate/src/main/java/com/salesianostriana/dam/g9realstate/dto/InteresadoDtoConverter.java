package com.salesianostriana.dam.g9realstate.dto;

import com.salesianostriana.dam.g9realstate.model.Interesa;
import com.salesianostriana.dam.g9realstate.model.Interesado;
import com.salesianostriana.dam.g9realstate.model.Propietario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InteresadoDtoConverter {

    public Interesado createInteresadoDtoToInteresado(CreateInteresadoInteresaDto i){
        return new Interesado(
                i.getNombre(),
                i.getApellidos(),
                i.getDireccion(),
                i.getEmail(),
                i.getTelefono(),
                i.getAvatar()
        );
    }

    public GetInteresadoDto interesadoToGetInteresadoDto(Interesado i){
        return GetInteresadoDto
                .builder()
                .id(i.getId())
                .nombre(i.getNombre())
                .apellidos(i.getApellidos())
                .direccion(i.getDireccion())
                .email(i.getEmail())
                .telefono(i.getTelefono())
                .avatar(i.getAvatar())
                .build();
    }

    public GetInteresadoInteresaDto interesadoToGetInteresadoInteresaDto(Interesado interesado, Interesa interesa){

        return GetInteresadoInteresaDto
                .builder()
                .id(interesado.getId())
                .nombre(interesado.getNombre())
                .apellidos(interesado.getApellidos())
                .direccion(interesado.getDireccion())
                .email(interesado.getEmail())
                .telefono(interesado.getTelefono())
                .avatar(interesado.getAvatar())
                .createdDate(interesa.getCreatedDate())
                .mensaje(interesa.getMensaje())
                .build();

    }

    public GetInteresadoViviendaDto interesadoToGetInteresadoViviendaDto (Interesado interesado){



        return GetInteresadoViviendaDto
                .builder()
                .id(interesado.getId())
                .nombre(interesado.getNombre())
                .apellidos(interesado.getApellidos())
                .email(interesado.getEmail())
                .telefono(interesado.getTelefono())
                .direccion(interesado.getDireccion())
                .vivienda(interesado.getInteresas().stream().map(v -> new GetInteresaDto(v.getMensaje(),v.getCreatedDate(),v.getVivienda().getTitulo(),
                        v.getVivienda().getProvincia(),v.getVivienda().getNumBanios(),
                        v.getVivienda().getNumHabitaciones(),v.getVivienda().getPrecio(),
                        v.getVivienda().getDescripcion(),v.getVivienda().getAvatar())).toList())
                .build();
    }

}
