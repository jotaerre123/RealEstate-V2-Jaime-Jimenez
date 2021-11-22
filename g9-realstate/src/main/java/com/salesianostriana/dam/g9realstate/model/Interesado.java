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
@DiscriminatorValue("I")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Interesado extends Persona implements Serializable {

    @Builder.Default
    @OneToMany(mappedBy = "interesado")
    private List<Interesa> interesas = new ArrayList<>();

    public Interesado( String nombre, String apellidos, String direccion, String email, String telefono, String avatar) {
        super( nombre, apellidos, direccion, email, telefono, avatar);
    }
}
