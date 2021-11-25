package com.salesianostriana.dam.g9realstate.users.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesianostriana.dam.g9realstate.model.Inmobiliaria;
import com.salesianostriana.dam.g9realstate.model.Interesa;
import com.salesianostriana.dam.g9realstate.model.Vivienda;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class UserEntity implements UserDetails{

    @Id @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String nombre;

    private String apellidos;

    private String direccion;

    @NaturalId
    @Column(unique = true, updatable = false)
    private String email;

    private String telefono;

    private String avatar;

    private String password;



    @Enumerated(EnumType.STRING)
    private UserRole roles;

    @ManyToOne
    @JoinColumn(name = "inmobiliaria_id", foreignKey = @ForeignKey(name = "PK_USER_INMOBILIARIA"), nullable = true)
    private Inmobiliaria inmobiliaria;

    @Builder.Default
    @OneToMany(mappedBy = "propietario", fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @JsonIgnore
    private List<Vivienda> listaViviendas = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "interesado")
    private List<Interesa> interesas = new ArrayList<>();

    @PreRemove
    public void preRemove(){
        interesas.forEach(v -> v.setInteresado(null));
    }

    public void addInmobiliaria(Inmobiliaria i) {
        this.inmobiliaria = i;
        i.getUserEntity().add(this);
    }

    public void removeInmobiliaria(Inmobiliaria i) {
        i.getUserEntity().remove(this);
        this.inmobiliaria = null;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + roles.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
