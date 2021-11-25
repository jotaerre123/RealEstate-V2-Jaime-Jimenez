package com.salesianostriana.dam.g9realstate.users.dto;

import com.salesianostriana.dam.g9realstate.dto.propietario.GetPropietarioDto;
import com.salesianostriana.dam.g9realstate.dto.vivienda.GetViviendaDto;
import com.salesianostriana.dam.g9realstate.dto.vivienda.GetViviendaDtoPequenio;
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

    public GetPropietarioDto propietarioToGetPropietarioConViviendas(UserEntity user){

        return GetPropietarioDto.builder()
                .nombre(user.getNombre())
                .apellidos(user.getApellidos())
                .direccion(user.getDireccion())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .telefono(user.getTelefono())
                .role(user.getRoles().name())
                .viviendas(user.getListaViviendas().stream().map(v -> new GetViviendaDtoPequenio(v.getId(),v.getTitulo()
                        ,v.getProvincia(),
                        v.getDireccion(),
                        v.getNumBanios(), v.getNumHabitaciones(),v.getMetrosCuadrados(),v.getPrecio()
                        ,v.getDescripcion(),v.getAvatar(), v.getTipoVivienda())).toList())
                .build();
    }

}
