package com.salesianostriana.dam.g9realstate.controller;

import com.salesianostriana.dam.g9realstate.dto.inmobiliaria.GetInmobiliariaDto;
import com.salesianostriana.dam.g9realstate.dto.inmobiliaria.InmobiliariaDtoConverter;
import com.salesianostriana.dam.g9realstate.model.Inmobiliaria;
import com.salesianostriana.dam.g9realstate.service.InmobiliariaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inmobiliaria/")
public class InmobiliariaController {

    private final InmobiliariaService inmobiliariaService;
    private final InmobiliariaDtoConverter inmobiliariaDtoConverter;

    @Operation(summary = "Crea una inmobiliaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha creado la inmobiliaria",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha guardado la inmobiliaria",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<Inmobiliaria> create (@RequestBody Inmobiliaria inmobiliaria){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(inmobiliariaService.save(inmobiliaria));

    }

    @Operation(summary = "Crea una inmobiliaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha creado la inmobiliaria",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha guardado la inmobiliaria",
                    content = @Content),
    })
    @GetMapping("")
    public ResponseEntity<List<GetInmobiliariaDto>>findAll(){
        List<Inmobiliaria> data = inmobiliariaService.findAll();
        if (data.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            List<GetInmobiliariaDto> lista = data.stream()
                    .map(inmobiliariaDtoConverter::getInmobiliariaToInmobiliariaDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(lista);
        }

    }



}
