package com.salesianostriana.dam.g9realstate.users.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor
@Builder
public class CreateUserDto {

    private String nombre;
    private String apellidos;
    private String direccion;
    private String email;
    private String telefono;
    private String avatar;
    private String password;
    private String password2;

}
