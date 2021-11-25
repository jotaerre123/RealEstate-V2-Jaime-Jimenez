package com.salesianostriana.dam.g9realstate.controller;


import com.salesianostriana.dam.g9realstate.dto.vivienda.ViviendaDtoConverter;
import com.salesianostriana.dam.g9realstate.model.Vivienda;

import com.salesianostriana.dam.g9realstate.service.ViviendaService;

import com.salesianostriana.dam.g9realstate.users.dto.UserDtoConverter;
import com.salesianostriana.dam.g9realstate.users.model.UserEntity;
import com.salesianostriana.dam.g9realstate.users.model.UserRole;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/vivienda")
public class ViviendaController {


    private final ViviendaService viviendaService;

    private final ViviendaDtoConverter viviendaDtoConverter;
    private final UserDtoConverter userDtoConverter;

    @Operation(summary = "Crea una nueva vivienda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado la vivienda",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado la vivienda",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<Vivienda> createVivienda(@RequestBody Vivienda vivienda, @AuthenticationPrincipal UserEntity userEntity) {

        if (vivienda.getTitulo().isEmpty() ||  userEntity.getId() == null) {
            return ResponseEntity.badRequest().build();
        } else {

            vivienda.addPropietario(userEntity);

                viviendaService.save(vivienda);

                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(vivienda);


        }

    }

    @Operation(summary = "Obtiene lista de viviendas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado lista de viviendas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se han encontrado las viviendas",
                    content = @Content),
    })
    @GetMapping("")
    public ResponseEntity<List<Vivienda>> findAll() {

        List<Vivienda> data = viviendaService.listarViviendasDto();

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {


            return ResponseEntity.ok(viviendaService.listarViviendasDto());
        }
    }

    @Operation(summary = "Obtiene una vivienda en base a su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la vivienda",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado la vivienda",
                    content = @Content),
    })
    @GetMapping("{id}")
    public ResponseEntity<Optional<Vivienda>> findOne (@PathVariable Long id) {

        Optional<Vivienda> data = viviendaService.findOne(id);

        if (data == null) {

            return ResponseEntity.notFound().build();

        } else {

            return ResponseEntity.ok().body(data);

        }

    }

    @Operation(summary = "Edita una vivienda anteriormente creada, buscando por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado la vivienda",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha editado la vivienda",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Vivienda> edit(@RequestBody Vivienda v, @PathVariable Long id, @AuthenticationPrincipal UserEntity userEntity) {




        if (!userEntity.getRoles().equals(UserRole.ADMIN) && !viviendaService.findById(id).get().getPropietario().getId().equals(userEntity.getId())) {
            return ResponseEntity.notFound().build();

        } else {
            return ResponseEntity.of(

                    viviendaService.findById(id).map(m -> {
                        m.setTitulo(v.getTitulo());
                        m.setDescripcion(v.getDescripcion());
                        m.setAvatar(v.getAvatar());
                        m.setCodigoPostal(v.getCodigoPostal());
                        m.setLatlng(v.getLatlng());
                        m.setMetrosCuadrados(v.getMetrosCuadrados());
                        m.setNumBanios(v.getNumBanios());
                        m.setNumHabitaciones(v.getNumHabitaciones());
                        m.setPoblacion(v.getPoblacion());
                        m.setPrecio(v.getPrecio());
                        m.setProvincia(v.getProvincia());
                        m.setDireccion(v.getDireccion());
                        m.setTipoVivienda(v.getTipoVivienda());
                        m.setTienePiscina(v.isTienePiscina());
                        m.setTieneAscensor(v.isTieneAscensor());
                        m.setTieneGaraje(v.isTieneGaraje());
                        viviendaService.save(m);

                        return m;
                    })
            );
        }
    }

    @Operation(summary = "Borra una vivienda por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado la vivienda",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @AuthenticationPrincipal UserEntity userEntity) {

        if (userEntity.getRoles().equals(UserRole.ADMIN) || viviendaService.findById(id).get().getPropietario().getId().equals(userEntity.getId())) {
            viviendaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
            return ResponseEntity.notFound().build();
    }


    /*public GetViviendaDto saveGetViviendaDto(CreateViviendaDto createViviendaDto, UserEntity user){
        GetViviendaDto getViviendaDto = GetViviendaDto.builder()
                .titulo(createViviendaDto.getTitulo())
                .descripcion(createViviendaDto.getDescripcion())
                .latlng(createViviendaDto.getLatlng())
                .codigoPostal(createViviendaDto.getCodigoPostal())
                .tienePiscina(createViviendaDto.isTienePiscina())
                .tieneAscensor(createViviendaDto.isTieneAscensor())
                .tieneGaraje(createViviendaDto.isTieneGaraje())
                .precio(createViviendaDto.getPrecio())
                .poblacion(createViviendaDto.getPoblacion())
                .provincia(createViviendaDto.getProvincia())
                .avatar(createViviendaDto.getAvatar())
                .tipo(createViviendaDto.getTipo())
                .direccion(createViviendaDto.getDireccion())
                .numHabitaciones(createViviendaDto.getNumHabitaciones())
                .metrosCuadrados(createViviendaDto.getMetrosCuadrados())
                .numBanios(createViviendaDto.getNumBanios())
                .getUserDto(userDtoConverter.convertUserEntityToGetUserDto(user))
                .build();

        return getViviendaDto;
    }*/

}
