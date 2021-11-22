package com.salesianostriana.dam.g9realstate.controller;

import com.salesianostriana.dam.g9realstate.dto.*;
import com.salesianostriana.dam.g9realstate.model.*;
import com.salesianostriana.dam.g9realstate.repos.InmobiliariaRepository;
import com.salesianostriana.dam.g9realstate.repos.InteresadoRepository;
import com.salesianostriana.dam.g9realstate.repos.ViviendaRepository;
import com.salesianostriana.dam.g9realstate.service.InteresaService;
import com.salesianostriana.dam.g9realstate.service.InteresadoService;
import com.salesianostriana.dam.g9realstate.service.PropietarioService;
import com.salesianostriana.dam.g9realstate.service.ViviendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/vivienda/")
public class ViviendaController {

    private final ViviendaService viviendaService;
    private final ViviendaRepository viviendaRepository;
    private final InteresadoRepository interesadoRepository;
    private final InmobiliariaRepository inmobiliariaRepository;
    private final ViviendaDtoConverter dtoConverter;
    private final PropietarioService propietarioService;
    private final InteresaDtoConverter interesaDtoConverter;
    private final InteresadoService interesadoService;
    private final InteresaService interesaService;
    private final InteresadoDtoConverter interesadoDtoConverter;



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
    public ResponseEntity<List<GetViviendaDto>> findAll() {

        List<GetViviendaDto> data = viviendaService.listarViviendasDto();

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
                            schema = @Schema(implementation = Inmobiliaria.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado la vivienda",
                    content = @Content),
    })
    @GetMapping("{id}")
    public ResponseEntity<GetViviendaIdDto> findOne (@PathVariable Long id) {

        GetViviendaIdDto data = dtoConverter.viviendaToGetViviendaDto(viviendaRepository.getById(id));

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
    @PutMapping("{id}")
    public ResponseEntity<PutViviendaDto> edit (@RequestBody PutViviendaDto v, @PathVariable Long id) {


        if (viviendaService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {

            return ResponseEntity.of(
                    viviendaService.findById(id).map(m -> {
                        m.setTitulo(v.getTitulo());
                        m.setProvincia(v.getProvincia());
                        m.setTipoVivienda(v.getTipoVivienda());
                        m.setNumBanios(v.getNumBanios());
                        m.setNumHabitaciones(v.getNumHabitaciones());
                        m.setMetrosCuadrados(v.getMetrosCuadrados());
                        m.setPrecio(v.getPrecio());
                        m.setDescripcion(v.getDescripcion());
                        m.setAvatar(v.getAvatar());
                        m.setDireccion(v.getDireccion());
                        m.setCodigoPostal(v.getCodigoPostal());
                        m.setLatlng(v.getLatlng());
                        m.setPoblacion(v.getPoblacion());
                        m.setTienePiscina(v.isTienePiscina());
                        m.setTieneAscensor(v.isTieneAscensor());
                        m.setTieneGaraje(v.isTieneGaraje());
                        m.setPropietario(m.getPropietario());
                        viviendaService.save(m);
                        return dtoConverter.editViviendaDtoToVivienda(m);
                    })
            );

        }
    }

    @Operation(summary = "Crea una nueva vivienda a la que se asocia un  propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado la nueva vivienda",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado el propietario",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado la nueva vivienda",
                    content = @Content),
    })
    @PostMapping("")
    public ResponseEntity<GetViviendaPropietarioDto> create(@RequestBody CreateViviendaDto dto) {

        if (dto.getPropietarioId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Vivienda nueva = dtoConverter.createViviendaDtoToVivienda(dto);
        Propietario propietario = propietarioService.findById(dto.getPropietarioId()).orElse(null);
        nueva.setPropietario(propietario);
        viviendaService.save(nueva);
        GetViviendaPropietarioDto converter = dtoConverter.viviendaToGetViviendaPropietarioDto(nueva);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(converter);

    }

    @Operation(summary = "Crea una nueva vivienda y añade una inmobiliaria ya existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado la nueva vivienda",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado la nueva vivienda",
                    content = @Content),
    })
    @PostMapping("{id}/inmobiliaria/{id2}")
    public ResponseEntity<Vivienda> createViviendaWithRealEstate (@PathVariable Long id, @PathVariable Long id2){

        if (viviendaRepository.findById(id).isEmpty()) {

            return  ResponseEntity.notFound().build();

        } else {
            Vivienda vivienda = viviendaRepository.getById(id);

            vivienda.setInmobiliaria(inmobiliariaRepository.getById(id2));
            vivienda.setPropietario(null);

            viviendaRepository.save(vivienda);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(vivienda);
        }

    }



    @Operation(summary = "Se elimina la vivienda y el interés, pero no el interesado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha borrado la vivienda",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha borrado la vivienda",
                    content = @Content),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteVivienda (@PathVariable Long id){

        if (viviendaService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }else

        viviendaService.deleteById(id);

        return ResponseEntity.noContent().build();

    }

    @Operation(summary = "Crea un interesado, y a la vez añade un interesado por una vivienda ya creada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado el nuevo interesado con interés",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Interesado.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se encuentra la vivienda",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "No se ha podido crear el interesado o hay datos erróneos",
                    content = @Content)
    })
    @PostMapping("{id}/meinteresa")
    public ResponseEntity<GetInteresadoInteresaDto> create(@PathVariable("id") Long id, @RequestBody CreateInteresadoInteresaDto dto){

        if (viviendaService.findById(id).isEmpty()){
            return  ResponseEntity.notFound().build();
        }else {
            Optional<Vivienda> v = viviendaService.findById(id);
            Interesado interesado = interesadoDtoConverter.createInteresadoDtoToInteresado(dto);
            Interesa interesa = Interesa.builder()
                    .mensaje(dto.getMensaje())
                    .build();
            interesa.addToInteresado(interesado);
            interesa.addToVivienda(v.get());
            interesadoService.save(interesado);
            interesaService.save(interesa);
            GetInteresadoInteresaDto interesadoInteresaDto = interesadoDtoConverter.
                    interesadoToGetInteresadoInteresaDto(interesado, interesa);


            return ResponseEntity.status(HttpStatus.CREATED).body(interesadoInteresaDto);

        }

    }

    @Operation(summary = "Crea un nuevo interés entre una vivienda y un interesado ya existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado el interés",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado el interés",
                    content = @Content),
    })
    @PostMapping("{id}/meinteresa/{id2}")
        public ResponseEntity<GetViviendaInteresaDto> createInteresaViviendaWithExistingInterested (@PathVariable Long id,
                                                                                                    @PathVariable Long id2,
                                                                                                    @RequestBody GetViviendaInteresaDto nuevoInteresa) {

        if (viviendaRepository.findById(id).isEmpty()) {

            return ResponseEntity.notFound().build();

        } else {

            Interesa interesa = interesaDtoConverter.createInteresaDto(nuevoInteresa);

            Interesado interesado = interesadoRepository.getById(id2);
            Vivienda vivienda = viviendaRepository.getById(id);

            interesa.addToVivienda(vivienda);
            interesa.addToInteresado(interesado);
            Interesa interesa1 = interesaService.save(interesa);
            GetViviendaInteresaDto result = interesaDtoConverter.getInteresaDto(interesa1);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(result);

        }
    }

    @Operation(summary = "Elimina el interés entre una vivienda y un interesado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha eliminado el interés",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha eliminado el interés",
                    content = @Content),
    })
    @DeleteMapping("{id}/meinteresa/{id2}")
    public ResponseEntity<?> deleteInteresaVivienda (@PathVariable Long id, @PathVariable Long id2) {

        if (viviendaRepository.findById(id).isEmpty() && interesadoRepository.findById(id2).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<Interesa> listInteresa = viviendaRepository.getById(id).getInteresas();
            Vivienda vivienda = viviendaRepository.getById(id);
            Interesado interesado = interesadoRepository.getById(id2);

            for (Interesa i: listInteresa) {
                interesaService.delete(i);
                return ResponseEntity.noContent().build();
            }

        }

        return ResponseEntity.badRequest().build();

    }


    @Operation(summary = "Se elimina la inmobiliaria sobre la vivienda especificada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado la inmobiliaria",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha borrado la inmobiliaria",
                    content = @Content),
    })
    @DeleteMapping("{id}/inmobiliaria")
    public ResponseEntity<?> deleteInmobiliaria (@PathVariable Long id){
        Optional <Vivienda> vivienda = viviendaService.findById(id);

        if (vivienda.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            vivienda.map(v -> {
                v.setInmobiliaria(null);
                viviendaService.save(v);
                return ResponseEntity.noContent().build();

            });  return ResponseEntity.noContent().build();

        }
    }

}
