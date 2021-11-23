package com.salesianostriana.dam.g9realstate.controller;

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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/propietario/")
@RestController
public class PropietarioController {

    private final UserEntityService userEntityService;

    @Operation(summary = "Obtiene todos los propietarios creados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado los propietarios",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se han encontrado los propietarios",
                    content = @Content),
    })
    @GetMapping("")
    public ResponseEntity<List<UserEntity>> findAll(){
        List<UserEntity> data = userEntityService.loadUserByRole(UserRole.PROPIETARIO);

        if (data.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            List<UserEntity> lista = data.stream().collect(Collectors.toList());

            return ResponseEntity.ok().body(lista);
        }
    }
    /*@Operation(summary = "Obtiene el propietario que le indicamos por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el propietario especificado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Propietario.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado el propietario",
                    content = @Content),
    })
    @GetMapping("{id}")
    public ResponseEntity<List<GetPropietarioViviendaDto>> findOne(@PathVariable Long id){
        Optional<Propietario> data = propietarioService.findById(id);

        if (data.isEmpty()) {

            return ResponseEntity.notFound().build();

        } else {
            List<GetPropietarioViviendaDto> propietarioDto = data
                    .stream().map(propietarioDtoConverter :: propietarioToGetPropietarioViviendaDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(propietarioDto);

        }
    }

    @Operation(summary = "Se elimina el propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado el propietario",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Propietario.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha borrado el propietario",
                    content = @Content),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePropietario(@PathVariable Long id){
        Optional<Propietario> propietario = propietarioService.findById(id);
        if (propietario.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            propietarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        }

    }

    @Operation(summary = "Crea un nuevo propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado el propietario",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado el propietario",
                    content = @Content),
    })
    @PostMapping("")
    public ResponseEntity<Propietario> create(@RequestBody CreatePropietarioDto dto){
        if (dto.getNombre().isEmpty()){
            return ResponseEntity.notFound().build();
        }else {

            Propietario nuevo = propietarioDtoConverter.createPropietarioDtoToPropietario(dto);

            return ResponseEntity.status(HttpStatus.CREATED).body(propietarioService.save(nuevo));

        }

    }*/


}
