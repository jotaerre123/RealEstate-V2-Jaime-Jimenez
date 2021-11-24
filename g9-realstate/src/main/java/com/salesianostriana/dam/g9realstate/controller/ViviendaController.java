package com.salesianostriana.dam.g9realstate.controller;

import com.salesianostriana.dam.g9realstate.dto.vivienda.CreateViviendaDto;
import com.salesianostriana.dam.g9realstate.dto.vivienda.GetViviendaDto;
import com.salesianostriana.dam.g9realstate.dto.vivienda.ViviendaDtoConverter;
import com.salesianostriana.dam.g9realstate.model.Vivienda;
import com.salesianostriana.dam.g9realstate.service.InmobiliariaService;
import com.salesianostriana.dam.g9realstate.service.ViviendaService;
import com.salesianostriana.dam.g9realstate.users.dto.GetUserDto;
import com.salesianostriana.dam.g9realstate.users.dto.UserDtoConverter;
import com.salesianostriana.dam.g9realstate.users.model.UserEntity;
import com.salesianostriana.dam.g9realstate.users.services.UserEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

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
    public ResponseEntity<GetViviendaDto> createVivienda(@RequestBody CreateViviendaDto vivienda, @AuthenticationPrincipal UserEntity userEntity) {

        if (vivienda.getTitulo().isEmpty() ||  (userEntity.getId() != null)) {
            return ResponseEntity.badRequest().build();
        } else {


                Vivienda v = viviendaDtoConverter.createViviendaDtoToVivienda(vivienda);
                GetViviendaDto getViviendaDto = saveGetViviendaDto(vivienda, userEntity);
                v.addPropietario(userEntity);
                viviendaService.save(v);

                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(getViviendaDto);


        }

    }
    public GetViviendaDto saveGetViviendaDto(CreateViviendaDto createViviendaDto, UserEntity user){
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
    }

}
