package com.salesianostriana.dam.g9realstate.model;

import com.salesianostriana.dam.g9realstate.users.model.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Vivienda implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String titulo;
    @Lob
    private String descripcion;
    @Lob
    private String avatar;
    private String latlng;
    private String direccion;
    private String codigoPostal;
    private String poblacion;
    private String provincia;

    @Enumerated(EnumType.STRING)
    private TipoVivienda tipoVivienda;
    private double precio;
    private int numHabitaciones;
    private int numBanios;
    private double metrosCuadrados;
    private boolean tienePiscina;
    private boolean tieneAscensor;
    private boolean tieneGaraje;

    @ManyToOne
    @JoinColumn(name = "inmobiliaria", foreignKey = @ForeignKey(name = "FK_VIVIENDA_INMOBILIARIA"))
    private Inmobiliaria inmobiliaria;

    @ManyToOne
    @JoinColumn(name = "userEntity_id", foreignKey = @ForeignKey(name = "FK_VIVIENDA_USERENTITY"))
    private UserEntity propietario;

    @Builder.Default
    @OneToMany(mappedBy = "vivienda")
    private List<Interesa> interesas = new ArrayList<>();



    public void addInmobiliaria(Inmobiliaria i){
        this.inmobiliaria = i;
        i.getViviendas().add(this);
    }

    public void removeInmobiliaria(Inmobiliaria i){
        i.getViviendas().remove(this);
        this.inmobiliaria=null;
    }

    public void addPropietario(UserEntity p){
        this.propietario = p;
        p.getListaViviendas().add(this);
    }

    public void removePropietario(UserEntity p){
        p.getListaViviendas().remove(this);
        this.propietario=null;
    }

}
