package com.salesianostriana.dam.g9realstate.users.controller;


import com.salesianostriana.dam.g9realstate.users.dto.CreateGestorDto;
import com.salesianostriana.dam.g9realstate.users.dto.CreateUserDto;
import com.salesianostriana.dam.g9realstate.users.dto.GetUserDto;
import com.salesianostriana.dam.g9realstate.users.dto.UserDtoConverter;
import com.salesianostriana.dam.g9realstate.users.model.UserEntity;
import com.salesianostriana.dam.g9realstate.users.services.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/register/")
public class UserController {

    private final UserEntityService userEntityService;
    private final UserDtoConverter userDtoConverter;

    @PostMapping("user")
    public ResponseEntity<GetUserDto> nuevoUsuario(@RequestBody CreateUserDto newUser){

        UserEntity saved = userEntityService.savePropietario(newUser);

        if (saved == null){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(userDtoConverter.convertUserEntityToGetUserDto(saved));
        }

    }

    @PostMapping("gestor")
    public ResponseEntity<GetUserDto> nuevoGestor(@RequestBody CreateGestorDto newUser){
        UserEntity saved = userEntityService.saveGestor(newUser);

        if(saved == null || saved.getInmobiliaria()== null){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(userDtoConverter.convertUserEntityToGetUserDto(saved));
        }
    }

    @PostMapping("admin")
    public ResponseEntity<GetUserDto> nuevoAdmin(@RequestBody CreateUserDto newUser){
        UserEntity saved = userEntityService.saveAdmin(newUser);

        if (saved == null){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(userDtoConverter.convertUserEntityToGetUserDto(saved));
        }
    }





}
