package com.salesianostriana.dam.g9realstate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Date;

@Data@NoArgsConstructor@AllArgsConstructor@SuperBuilder
public class GetInteresadoInteresaDto extends GetInteresadoDto{

    private LocalDate createdDate;
    private String mensaje;

    public GetInteresadoInteresaDto(Long id, String nombre, String apellidos, String direccion, String email, String telefono, String avatar, LocalDate createdDate, String mensaje) {
        super(id, nombre, apellidos, direccion, email, telefono, avatar);
        this.createdDate = createdDate;
        this.mensaje = mensaje;
    }
}
