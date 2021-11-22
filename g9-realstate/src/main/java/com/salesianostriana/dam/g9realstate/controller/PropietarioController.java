package com.salesianostriana.dam.g9realstate.controller;

import com.salesianostriana.dam.g9realstate.dto.*;
import com.salesianostriana.dam.g9realstate.model.Propietario;

import com.salesianostriana.dam.g9realstate.model.Vivienda;
import com.salesianostriana.dam.g9realstate.service.PropietarioService;
import com.salesianostriana.dam.g9realstate.util.pagination.PaginationLinksUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/propietario/")
@RestController
public class PropietarioController {

    private final PropietarioService propietarioService;
    private final PropietarioDtoConverter propietarioDtoConverter;
    private final PaginationLinksUtils paginationLinksUtils;

    @Operation(summary = "Obtiene todos los propietarios creados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado los propietarios",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Propietario.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se han encontrado los propietarios",
                    content = @Content),
    })
    @GetMapping("")
    public ResponseEntity<Page<GetPropietarioDto>> findAll(@PageableDefault(size = 8, page = 0) Pageable pageable, HttpServletRequest request){

        Page<Propietario> data = propietarioService.findAll(pageable);

        if (data.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            Page<GetPropietarioDto> result =
                    data.map(propietarioDtoConverter::propietarioToGetPropietarioDto);

            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(result, uriBuilder)).body(result);
        }


    }
    @Operation(summary = "Obtiene el propietario que le indicamos por ID")
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

    }


}
