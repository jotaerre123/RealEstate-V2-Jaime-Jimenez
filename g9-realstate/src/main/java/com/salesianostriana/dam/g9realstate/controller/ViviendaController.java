package com.salesianostriana.dam.g9realstate.controller;


import com.salesianostriana.dam.g9realstate.dto.vivienda.*;
import com.salesianostriana.dam.g9realstate.model.Inmobiliaria;
import com.salesianostriana.dam.g9realstate.model.Vivienda;

import com.salesianostriana.dam.g9realstate.service.InmobiliariaService;
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
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/vivienda")
public class ViviendaController {


    private final ViviendaService viviendaService;
    private final InmobiliariaService inmobiliariaService;
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
    public ResponseEntity<GetViviendaDto> createVivienda(@RequestBody CreateViviendaDto vivienda, @AuthenticationPrincipal UserEntity userEntity) {

        GetViviendaDto getDto = guardarGetViviendaDto(vivienda, userEntity);

        Vivienda v = viviendaDtoConverter.createViviendaDtoToVivienda(vivienda, userEntity);

        viviendaService.saveViviendaFromGetViviendaDto(getDto, userEntity);


                return ResponseEntity.status(HttpStatus.CREATED).body(getDto);




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
    public ResponseEntity<List<GetViviendaInmobiliariaDto2>> findAll() {

        List<Vivienda> data = viviendaService.listarViviendasDto();

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {

                List<GetViviendaInmobiliariaDto2> lista = data.stream()
                        .map(viviendaDtoConverter::getVivienda).collect(Collectors.toList());
            return ResponseEntity.ok(lista);
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
            viviendaService.findById(id).get().removeInteresasFromVivienda();
            viviendaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
            return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Top de las 5 viviendas que tienen más interesados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado la vivienda",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))})
    })
    @GetMapping("/top")
    public ResponseEntity<List<GetViviendaDto>> top5Viviendas (@RequestParam("n") int n){
        List<Vivienda> data = viviendaService.findTop5ViviendaOrderByInteresas();

        List<GetViviendaDto> list = data.stream().map(viviendaDtoConverter::viviendaToGetViviendaDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Crea una nueva Inmobiliaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado la inmobiliaria",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado la inmobiliaria",
                    content = @Content),
    })
    @PostMapping("/{id}/inmobiliaria/{id2}")
    public ResponseEntity<?> asociarInmobiliariaAVivienda(@PathVariable Long id,@PathVariable Long id2,@AuthenticationPrincipal UserEntity userEntity) {
        if (viviendaService.findById(id).isEmpty() || inmobiliariaService.findById(id2).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Vivienda vivienda = viviendaService.findById(id).get();
            Inmobiliaria inmobiliaria = inmobiliariaService.findById(id2).get();
            vivienda.setInmobiliaria(inmobiliaria);
            vivienda.addInmobiliaria(inmobiliaria);
            viviendaService.save(vivienda);
            GetViviendaInmobiliariaDto get = viviendaDtoConverter.viviendaToGetViviendaInmobiliariaDto(vivienda, inmobiliaria);
            return ResponseEntity.status(HttpStatus.CREATED).body(get);


        }

    }
    @Operation(summary = "Borra la asociación entre una vivienda y una inmobiliaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado la vivienda",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la vivienda a la que le queremos quitar la inmobiliaria",
                    content = @Content),
    })

    @DeleteMapping("/{id}/inmobiliaria/")
    public ResponseEntity deleteInmobiliariaDeVivienda(@PathVariable Long id) {

        if(viviendaService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        else {

            Inmobiliaria inmobiliaria = viviendaService.findById(id).get().getInmobiliaria();
            viviendaService.findById(id).get().removeInmobiliaria(inmobiliaria);
            viviendaService.save(viviendaService.findById(id).get());

            return ResponseEntity.noContent().build();
        }

    }


    public GetViviendaDto guardarGetViviendaDto(CreateViviendaDto createViviendaDto, UserEntity user){
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
                .propietario(userDtoConverter.convertUserEntityToGetUserDto(user))
                .build();

        return getViviendaDto;
    }

}
