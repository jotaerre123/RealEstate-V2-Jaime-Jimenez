package com.salesianostriana.dam.g9realstate.users.dto;

import com.salesianostriana.dam.g9realstate.dto.GetPropietario;
import com.salesianostriana.dam.g9realstate.dto.GetViviendaDto;
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

    public GetPropietario propietarioToGetPropietarioConViviendas(UserEntity user){

        return GetPropietario.builder()
                .nombre(user.getNombre())
                .apellidos(user.getApellidos())
                .direccion(user.getDireccion())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .telefono(user.getTelefono())
                .role(user.getRoles().name())
                .viviendas(user.getListaViviendas().stream().map(v -> new GetViviendaDto(v.getId(),v.getTitulo()
                        ,v.getProvincia(),
                        v.getNumBanios(), v.getNumHabitaciones(),v.getMetrosCuadrados(),v.getPrecio()
                        ,v.getDescripcion(),v.getAvatar())).toList())
                .build();
    }

}
