package com.salesianostriana.dam.g9realstate.dto;

import com.salesianostriana.dam.g9realstate.model.Inmobiliaria;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class InmobiliariaDtoConverter {

    public Inmobiliaria createInmpbiliariaDtoToInmobiliaria (CreateInmobiliariaDto i){
        return new Inmobiliaria(
                i.getNombre(),
                i.getEmail(),
                i.getTelefono()
        );



    }


    public GetInmobiliariaDto inmobiliariaToGetInmobiliariaDto (Inmobiliaria i) {

        return GetInmobiliariaDto
                .builder()
                .id(i.getId())
                .nombre(i.getNombre())
                .email(i.getEmail())
                .telefono(i.getTelefono())
                .build();

    }

    public GetInmobiliariaViviendaDto inmobiliariaToGetInmobiliariaViviendaDto (Inmobiliaria i) {

        List<String> titulos = new ArrayList<>();
        for (int pepitoPerez = 0; pepitoPerez<i.getViviendas().size(); pepitoPerez++){
            titulos.add(i.getViviendas().get(pepitoPerez).getTitulo());
        }
        return GetInmobiliariaViviendaDto
                .builder()
                .id(i.getId())
                .nombre(i.getNombre())
                .email(i.getEmail())
                .telefono(i.getTelefono())
                .viviendas(titulos)
                .build();

    }

}
