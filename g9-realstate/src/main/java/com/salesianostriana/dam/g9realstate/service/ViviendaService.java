package com.salesianostriana.dam.g9realstate.service;

import com.salesianostriana.dam.g9realstate.dto.GetViviendaDto;
import com.salesianostriana.dam.g9realstate.model.Vivienda;
import com.salesianostriana.dam.g9realstate.repos.ViviendaRepository;
import com.salesianostriana.dam.g9realstate.service.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViviendaService extends BaseService<Vivienda, Long, ViviendaRepository> {

    public List<GetViviendaDto> listarViviendasDto() {
        return repositorio.todosLasViviendasDto();
    }




}
