package com.salesianostriana.dam.g9realstate.controller;

import com.salesianostriana.dam.g9realstate.dto.GetInteresadoDto;
import com.salesianostriana.dam.g9realstate.dto.GetInteresadoViviendaDto;
import com.salesianostriana.dam.g9realstate.dto.InteresadoDtoConverter;
import com.salesianostriana.dam.g9realstate.model.Interesado;
import com.salesianostriana.dam.g9realstate.service.InteresadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/interesado/")
@RestController
public class InteresadoController {

    private final InteresadoService interesadoService;
    private final InteresadoDtoConverter interesadoDtoConverter;

    @Operation(summary = "Obtiene todos los interesados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han listado todos los interesados",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Interesado.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se han listado los interesados",
                    content = @Content),
    })
    @GetMapping("")
    public ResponseEntity<List<GetInteresadoDto>> findAll(){

        List<Interesado> data = interesadoService.findAll();

        if (data.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            List<GetInteresadoDto> result =
                    data.stream()
                            .map(interesadoDtoConverter::interesadoToGetInteresadoDto)
                            .collect(Collectors.toList());
            return ResponseEntity.ok().body(result);
        }

    }

    @Operation(summary = "Obtiene el interesado que le indicamos por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el interesado especificado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Interesado.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado el interesado",
                    content = @Content),
    })
    @GetMapping("{id}")
    public ResponseEntity<List<GetInteresadoViviendaDto>> findOne(@PathVariable Long id){
        Optional<Interesado> data = interesadoService.findById(id);

        if (data.isEmpty()) {

            return ResponseEntity.notFound().build();

        } else {
            List<GetInteresadoViviendaDto> interesadoDto = data
                    .stream().map(interesadoDtoConverter :: interesadoToGetInteresadoViviendaDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(interesadoDto);

        }
    }

}
