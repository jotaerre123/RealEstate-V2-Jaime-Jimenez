package com.salesianostriana.dam.g9realstate.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("propietario")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Propietario extends Persona implements Serializable {


    @Builder.Default
    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vivienda> listaViviendas = new ArrayList<>();

    public Propietario(String nombre, String apellidos, String direccion, String email, String telefono, String avatar) {
        super(nombre, apellidos, direccion, email, telefono, avatar);
    }
}
