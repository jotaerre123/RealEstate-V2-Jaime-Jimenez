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

    @PreRemove
    public void removeInteresasFromVivienda(){
        interesas.forEach(interesa -> interesa.setVivienda(null));
    }

    public void addInmobiliaria(Inmobiliaria i){
        this.inmobiliaria = i;
        if (i.getViviendas() == null){
            i.setViviendas(new ArrayList<>());
        }
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

    public Vivienda(String titulo, String descripcion, String avatar, String latlng, String direccion, String codigoPostal, String poblacion, String provincia, TipoVivienda tipoVivienda, double precio, int numHabitaciones, int numBanios, double metrosCuadrados, boolean tienePiscina, boolean tieneAscensor, boolean tieneGaraje) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.avatar = avatar;
        this.latlng = latlng;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.tipoVivienda = tipoVivienda;
        this.precio = precio;
        this.numHabitaciones = numHabitaciones;
        this.numBanios = numBanios;
        this.metrosCuadrados = metrosCuadrados;
        this.tienePiscina = tienePiscina;
        this.tieneAscensor = tieneAscensor;
        this.tieneGaraje = tieneGaraje;
    }

    public Vivienda(Long id, String titulo, String descripcion, String avatar, String latlng, String direccion, String codigoPostal, String poblacion, String provincia, TipoVivienda tipoVivienda, double precio, int numHabitaciones, int numBanios, double metrosCuadrados, boolean tienePiscina, boolean tieneAscensor, boolean tieneGaraje, Inmobiliaria inmobiliaria) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.avatar = avatar;
        this.latlng = latlng;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.tipoVivienda = tipoVivienda;
        this.precio = precio;
        this.numHabitaciones = numHabitaciones;
        this.numBanios = numBanios;
        this.metrosCuadrados = metrosCuadrados;
        this.tienePiscina = tienePiscina;
        this.tieneAscensor = tieneAscensor;
        this.tieneGaraje = tieneGaraje;
        this.inmobiliaria = inmobiliaria;
    }
}
