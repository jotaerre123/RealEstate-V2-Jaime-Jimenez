package com.salesianostriana.dam.g9realstate.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@SuperBuilder

public abstract class Persona implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String apellidos;

    private String direccion;

    private String email;

    private String telefono;

    private String avatar;

    public Persona(String nombre, String apellidos, String direccion, String email, String telefono, String avatar) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.avatar = avatar;
    }
}
