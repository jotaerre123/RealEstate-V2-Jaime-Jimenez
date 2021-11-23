package com.salesianostriana.dam.g9realstate.users.dto;

import com.salesianostriana.dam.g9realstate.users.model.UserEntity;

import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public GetUserDto convertUserEntityToGetUserDto(UserEntity user){
        return GetUserDto.builder()
                .nombre(user.getNombre())
                .apellidos(user.getApellidos())
                .direccion(user.getDireccion())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .telefono(user.getTelefono())
                .role(user.getRoles().name())
                .build();
    }

}
