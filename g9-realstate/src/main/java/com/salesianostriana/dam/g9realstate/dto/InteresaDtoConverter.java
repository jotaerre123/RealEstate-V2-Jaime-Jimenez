package com.salesianostriana.dam.g9realstate.dto;

import com.salesianostriana.dam.g9realstate.model.Interesa;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class InteresaDtoConverter {

    public Interesa createInteresaDto (GetViviendaInteresaDto getViviendaInteresaDto) {
        return Interesa.builder()
                .createdDate(LocalDate.now())
                .mensaje(getViviendaInteresaDto.getMensaje())
                .build();
    }

    public GetViviendaInteresaDto getInteresaDto (Interesa interesa) {
        return GetViviendaInteresaDto.builder()
                .mensaje(interesa.getMensaje())
                .createDate(LocalDate.now())
                .build();
    }


}
