package com.salesianostriana.dam.g9realstate.model;

import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@NamedEntityGraph(
        name = Inmobiliaria.INMOBILIARIA_CON_VIVIENDA,
        attributeNodes = {
                @NamedAttributeNode("viviendas")
        }
)
public class Inmobiliaria implements Serializable {

    public static final String INMOBILIARIA_CON_VIVIENDA= "grafo-inmobiliaria-con-vivienda";

    @Id
    @GeneratedValue
    private Long id;

    private String nombre, email;

    private String telefono;

    @OneToMany(mappedBy = "inmobiliaria")
    private List<Vivienda> viviendas =new ArrayList<>();

    public Inmobiliaria(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public Inmobiliaria(String nombre, String email, String telefono, List<Vivienda> viviendas) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.viviendas = viviendas;
    }
    @PreRemove
    public void preRemove() {
        viviendas.forEach( v -> v.setInmobiliaria(null));
    }
}