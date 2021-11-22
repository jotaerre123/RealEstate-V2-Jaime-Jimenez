package com.salesianostriana.dam.g9realstate.controller;

import com.salesianostriana.dam.g9realstate.dto.*;
import com.salesianostriana.dam.g9realstate.model.Inmobiliaria;
import com.salesianostriana.dam.g9realstate.model.Vivienda;
import com.salesianostriana.dam.g9realstate.repos.InmobiliariaRepository;
import com.salesianostriana.dam.g9realstate.service.InmobiliariaService;
import com.salesianostriana.dam.g9realstate.service.ViviendaService;
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
@RestController
@RequestMapping("/inmobiliaria/")
@RequiredArgsConstructor
public class InmobiliariaController {

    private final InmobiliariaRepository inmobiliariaRepository;
    private final InmobiliariaService inmobiliariaService;
    private final InmobiliariaDtoConverter inmobiliariaDtoConverter;
    private final ViviendaService viviendaService;
    private final PaginationLinksUtils paginationLinksUtils;

    @Operation(summary = "Muestra una lista de todas las inmobiliarias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las inmobiliarias",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "No se han encontrado las inmobiliarias",
                    content = @Content),
    })
    @GetMapping("")
    public ResponseEntity<Page<GetInmobiliariaDto>> findAll(@PageableDefault(size = 8, page = 0) Pageable pageable, HttpServletRequest request) {

        Page<Inmobiliaria> data = inmobiliariaRepository.findAll(pageable);

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Page<GetInmobiliariaDto> result = data.map(inmobiliariaDtoConverter::inmobiliariaToGetInmobiliariaDto);

            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(result, uriBuilder)).body(result);
        }

    }

    @Operation(summary = "Muestra una inmobiliaria y sus viviendas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado la inmobiliaria",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado la inmobiliaria",
                    content = @Content),
    })
    @GetMapping("{id}")
    public ResponseEntity<List<GetInmobiliariaViviendaDto>> findOne(@PathVariable Long id){

        Optional<Inmobiliaria> inmobiliaria = inmobiliariaService.findById(id);

        if (inmobiliaria.isEmpty()){

            return ResponseEntity.notFound().build();

        }else{

            List<GetInmobiliariaViviendaDto> result =
                    inmobiliaria.stream()
                            .map(inmobiliariaDtoConverter::inmobiliariaToGetInmobiliariaViviendaDto)
                            .collect(Collectors.toList());

            return ResponseEntity.ok().body(result);

        }


    }
    @Operation(summary = "Crea una nueva inmobiliaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han creado la inmobiliaria",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado la inmobiliaria",
                    content = @Content),
    })
    @PostMapping("")
    public ResponseEntity<Inmobiliaria> create(@RequestBody CreateInmobiliariaDto dto){
        if (dto.getNombre().isEmpty()){
            return ResponseEntity.notFound().build();
        }else {

            Inmobiliaria nueva = inmobiliariaDtoConverter.createInmpbiliariaDtoToInmobiliaria(dto);

            return ResponseEntity.status(HttpStatus.CREATED).body(inmobiliariaService.save(nueva));

        }

    }

    @Operation(summary = "Borra una inmobiliaria en base a su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado la inmobiliaria",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha borrado la inmobiliaria",
                    content = @Content),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteInmobiliaria(@PathVariable Long id) {
        Optional<Inmobiliaria> inmobiliaria = inmobiliariaService.findById(id);
        Inmobiliaria inmobiliaria1 = inmobiliaria.get();
        List <Vivienda> listVivienda = viviendaService.findAll();

        if (inmobiliaria.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            inmobiliaria1.preRemove();
            inmobiliariaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }

    }
}
