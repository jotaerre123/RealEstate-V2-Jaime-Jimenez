package com.salesianostriana.dam.g9realstate.repos;

import com.salesianostriana.dam.g9realstate.model.Vivienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ViviendaRepository extends JpaRepository<Vivienda, Long> {

    /*@Query("""
            select new com.salesianostriana.dam.g9realstate.dto.GetViviendaDto(
                v.id, v.titulo, v.provincia, v.numBanios, v.numHabitaciones, v.metrosCuadrados, v.precio, v.descripcion, v.avatar
            )
            from Vivienda v 
            """)
    List<GetViviendaDto> todosLasViviendasDto();*/






}
