package com.salesianostriana.dam.g9realstate.dto.vivienda;

import com.salesianostriana.dam.g9realstate.model.Inmobiliaria;
import com.salesianostriana.dam.g9realstate.model.Vivienda;
import com.salesianostriana.dam.g9realstate.users.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class ViviendaDtoConverter {

    public GetViviendaDto viviendaToGetViviendaDTO(Vivienda v) {
        return GetViviendaDto
                .builder()
                .titulo(v.getTitulo())
                .descripcion(v.getDescripcion())
                .precio(v.getPrecio())
                .metrosCuadrados(v.getMetrosCuadrados())
                .numBanios(v.getNumBanios())
                .numHabitaciones(v.getNumHabitaciones())
                .avatar(v.getAvatar())
                .tipo(v.getTipoVivienda())
                .build();
    }

    public Vivienda createViviendaDtoToVivienda  (CreateViviendaDto v, UserEntity user) {

        Vivienda result = new Vivienda();

        result.setId(v.getId());
        result.setTitulo(v.getTitulo());
        result.setDescripcion(v.getDescripcion());
        result.setAvatar(v.getAvatar());
        result.setLatlng(v.getLatlng());
        result.setDireccion(v.getDireccion());
        result.setCodigoPostal(v.getCodigoPostal());
        result.setPoblacion(v.getPoblacion());
        result.setProvincia(v.getProvincia());
        result.setTipoVivienda(v.getTipo());
        result.setPrecio(v.getPrecio());
        result.setNumHabitaciones(v.getNumHabitaciones());
        result.setMetrosCuadrados(v.getMetrosCuadrados());
        result.setNumBanios(v.getNumBanios());
        result.setTienePiscina(v.isTienePiscina());
        result.setTieneAscensor(v.isTieneAscensor());
        result.setTieneGaraje(v.isTieneGaraje());
        //result.setInmobiliaria(inmobiliariaService.findById(v.getInmobiliaria().getId()).get());
        result.setPropietario(user);

        return result;
    }

    public GetViviendaInmobiliariaDto viviendaToGetViviendaInmobiliariaDto(Vivienda v, Inmobiliaria i){

        GetViviendaInmobiliariaDto result = new GetViviendaInmobiliariaDto();
        result.setPoblacion(v.getPoblacion());
        result.setDireccion(v.getDireccion());
        result.setProvincia(v.getProvincia());
        result.setPrecio(v.getPrecio());
        result.setAvatar(v.getAvatar());
        result.setMetrosCuadrados(v.getMetrosCuadrados());
        result.setNumHabitaciones(v.getNumHabitaciones());
        result.setTipo(v.getTipoVivienda());
        result.setTitulo(v.getTitulo());
        result.setIdInmo(i.getId());
        result.setNombreInmo(i.getNombre());

        return result;
    }

}
