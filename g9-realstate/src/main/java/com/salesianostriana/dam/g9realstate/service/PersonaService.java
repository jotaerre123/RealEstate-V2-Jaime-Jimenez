package com.salesianostriana.dam.g9realstate.service;

import com.salesianostriana.dam.g9realstate.model.Persona;
import com.salesianostriana.dam.g9realstate.repos.PersonaRepository;
import com.salesianostriana.dam.g9realstate.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class PersonaService extends  BaseService<Persona, Long, PersonaRepository>{
}
