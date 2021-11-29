package com.salesianostriana.dam.g9realstate.controller;

import com.salesianostriana.dam.g9realstate.dto.propietario.GetPropietarioDto;
import com.salesianostriana.dam.g9realstate.users.dto.UserDtoConverter;
import com.salesianostriana.dam.g9realstate.users.model.UserEntity;
import com.salesianostriana.dam.g9realstate.users.model.UserRole;
import com.salesianostriana.dam.g9realstate.users.services.UserEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/propietario")
@RestController
public class PropietarioController {

    private final UserEntityService userEntityService;
    private final UserDtoConverter userDtoConverter;

    @Operation(summary = "Obtiene todos los propietarios creados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado los propietarios",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado los propietarios",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<UserEntity>> findAll(){ // TODO El modelo de respuesta de esta petición no es nada adecuado. Aparecen los roles, la contraseña cifrado, ....
        List<UserEntity> data = userEntityService.loadUserByRole(UserRole.PROPIETARIO);

        if (data.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            List<UserEntity> lista = data.stream().collect(Collectors.toList());

            return ResponseEntity.ok().body(lista);
        }
    }
    @Operation(summary = "Obtiene el propietario que le indicamos por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el propietario especificado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))}),
            @ApiResponse(responseCode = "403",
                    description = "Authentication failed",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<List<GetPropietarioDto>> findOnePropietario(@PathVariable UUID id, @AuthenticationPrincipal UserEntity userEntity) {
        Optional<UserEntity> propietario = userEntityService.loadUserById(id);


        if(!userEntity.getRoles().equals(UserRole.ADMIN) && !id.equals(userEntity.getId())){
            return ResponseEntity.status(403).build();
        }else {
            List<GetPropietarioDto> propietarioDto = propietario.stream()
                    .map(userDtoConverter::propietarioToGetPropietarioConViviendas)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(propietarioDto);
        }
    }



    @Operation(summary = "Se elimina el propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado el propietario",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha borrado el propietario",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePropietario(@PathVariable UUID id, @AuthenticationPrincipal UserEntity userEntity){




        if (id.equals(userEntity.getId()) || userEntity.getRoles().equals(UserRole.ADMIN)) {
            userEntityService.deleteById(id); // TODO Produce una excepción si el ID no existe.
            return ResponseEntity.noContent().build();

        }

        return ResponseEntity.status(403).build();

    }




}
