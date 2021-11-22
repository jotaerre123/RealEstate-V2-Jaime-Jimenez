package com.salesianostriana.dam.g9realstate.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Interesa {

    @Builder.Default
    @EmbeddedId
    private InteresaPk id = new InteresaPk();

    @ManyToOne
    @MapsId("vivienda_id")
    @JoinColumn(name="vivienda_id")
    private Vivienda vivienda;

    @ManyToOne
    @MapsId("interesado_id")
    @JoinColumn(name = "interesado_id")
    private Interesado interesado;

    @CreatedDate
    private LocalDate createdDate;
    private String mensaje;



    public void addToVivienda(Vivienda v){
        vivienda = v;
        v.getInteresas().add(this);
    }

    public void removeFromVivienda(Vivienda v){
        v.getInteresas().remove(this);
        vivienda=null;
    }

    public void addToInteresado(Interesado i){
        interesado = i;
        if (i.getInteresas() == null){
            i.setInteresas(new ArrayList<>());
            i.getInteresas().add(this);
        }
        i.getInteresas().add(this);
    }

    public void removeFromInteresado(Interesado i){
        i.getInteresas().remove(this);
        interesado=null;
    }
}

