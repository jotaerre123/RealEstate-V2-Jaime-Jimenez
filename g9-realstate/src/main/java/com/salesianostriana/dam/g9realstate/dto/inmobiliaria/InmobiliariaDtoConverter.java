package com.salesianostriana.dam.g9realstate.dto.inmobiliaria;

import com.salesianostriana.dam.g9realstate.model.Inmobiliaria;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InmobiliariaDtoConverter {

    public Inmobiliaria createInmobiliariaDtoToInmobiliaria(CreateInmobiliariaDto c){
        return new Inmobiliaria(
                c.getNombre(),
                c.getEmail(),
                c.getTelefono()
        );
    }
    public GetInmobiliariaDto getInmobiliariaToInmobiliariaDto(Inmobiliaria in){

        List<String> nombreVivienda = new ArrayList<>();
        for (int i=0; i<in.getViviendas().size();i++){
            nombreVivienda.add(in.getViviendas().get(i).getTitulo());
        }

        return GetInmobiliariaDto
                .builder()
                .id(in.getId())
                .nombre(in.getNombre())
                .email(in.getEmail())
                .telefono(in.getTelefono())
                .vivienda(nombreVivienda)
                .build();

    }






}