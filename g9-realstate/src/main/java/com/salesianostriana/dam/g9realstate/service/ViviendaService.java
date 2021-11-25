package com.salesianostriana.dam.g9realstate.service;

import com.salesianostriana.dam.g9realstate.dto.vivienda.GetViviendaDto;
import com.salesianostriana.dam.g9realstate.model.Vivienda;
import com.salesianostriana.dam.g9realstate.repos.ViviendaRepository;
import com.salesianostriana.dam.g9realstate.service.base.BaseService;
import com.salesianostriana.dam.g9realstate.users.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViviendaService extends BaseService<Vivienda, Long, ViviendaRepository> {

    public List<Vivienda> listarViviendasDto() {
        return repositorio.findAll();
    }


    public Vivienda saveViviendaFromGetViviendaDto(GetViviendaDto getViviendaDto, UserEntity userEntity){
       Vivienda vivienda = Vivienda.builder()
               .titulo(getViviendaDto.getTitulo())
               .provincia(getViviendaDto.getProvincia())
               .direccion(getViviendaDto.getDireccion())
               .numBanios(getViviendaDto.getNumBanios())
               .numHabitaciones(getViviendaDto.getNumHabitaciones())
               .metrosCuadrados(getViviendaDto.getMetrosCuadrados())
               .precio(getViviendaDto.getPrecio())
               .descripcion(getViviendaDto.getDescripcion())
               .avatar(getViviendaDto.getAvatar())
               .tipoVivienda(getViviendaDto.getTipo())
               .codigoPostal(getViviendaDto.getCodigoPostal())
               .latlng(getViviendaDto.getLatlng())
               .poblacion(getViviendaDto.getPoblacion())
               .tienePiscina(getViviendaDto.isTienePiscina())
               .tieneAscensor(getViviendaDto.isTieneAscensor())
               .tieneGaraje(getViviendaDto.isTieneGaraje())
               .propietario(userEntity)
               .build();
       return save(vivienda);
    }
}
