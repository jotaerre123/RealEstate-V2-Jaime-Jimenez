package com.salesianostriana.dam.g9realstate.users.services;

import com.salesianostriana.dam.g9realstate.model.Inmobiliaria;
import com.salesianostriana.dam.g9realstate.service.InmobiliariaService;
import com.salesianostriana.dam.g9realstate.service.base.BaseService;
import com.salesianostriana.dam.g9realstate.users.dto.CreateGestorDto;
import com.salesianostriana.dam.g9realstate.users.dto.CreateUserDto;
import com.salesianostriana.dam.g9realstate.users.model.UserEntity;
import com.salesianostriana.dam.g9realstate.users.model.UserRole;
import com.salesianostriana.dam.g9realstate.users.repos.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserEntityService extends BaseService<UserEntity, UUID, UserEntityRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final InmobiliariaService inmobiliariaService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repositorio.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email + "no encontrado"));
    }

    public List<UserEntity> loadUserByRole(UserRole roles) throws UsernameNotFoundException{
        return this.repositorio.findByRoles(roles);
    }

    public Optional<UserEntity> loadUserById(Long id, UserRole rol) throws UsernameNotFoundException{
        return this.repositorio.findByIdByRol(id, rol);
    }


    public UserEntity savePropietario(CreateUserDto newUser){
        if (newUser.getPassword().contentEquals(newUser.getPassword2())){
            UserEntity userEntity = UserEntity.builder()
                    .nombre(newUser.getNombre())
                    .apellidos(newUser.getApellidos())
                    .direccion(newUser.getDireccion())
                    .telefono(newUser.getTelefono())
                    .email(newUser.getEmail())
                    .avatar(newUser.getAvatar())
                    .roles(UserRole.PROPIETARIO)
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    //Set.of(UserRole.PROPIETARIO)
                    .build();

            return save(userEntity);
        }else{
            return null;
        }
    }

    public UserEntity saveGestor(CreateGestorDto newUser){
        if (newUser.getPassword().contentEquals(newUser.getPassword2())){
            UserEntity userEntity = UserEntity.builder()
                    .nombre(newUser.getNombre())
                    .apellidos(newUser.getApellidos())
                    .direccion(newUser.getDireccion())
                    .telefono(newUser.getTelefono())
                    .email(newUser.getEmail())
                    .avatar(newUser.getAvatar())
                    .roles(UserRole.GESTOR)
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .inmobiliaria(null)
                    .build();
            Optional<Inmobiliaria> inmobiliaria = inmobiliariaService.findById(newUser.getInmobiliaria());
            userEntity.addInmobiliaria(inmobiliaria.get());
            return save(userEntity);
        }else{
            return null;
        }
    }

    public UserEntity saveAdmin(CreateUserDto newUser){
        if (newUser.getPassword().contentEquals(newUser.getPassword2())){
            UserEntity userEntity = UserEntity.builder()
                    .nombre(newUser.getNombre())
                    .apellidos(newUser.getApellidos())
                    .direccion(newUser.getDireccion())
                    .telefono(newUser.getTelefono())
                    .email(newUser.getEmail())
                    .avatar(newUser.getAvatar())
                    .roles(UserRole.ADMIN)
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .build();

            return save(userEntity);
        }else{
            return null;
        }
    }

}
