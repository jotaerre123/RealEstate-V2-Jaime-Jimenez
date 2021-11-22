package com.salesianostriana.dam.realestate.users.services;

import com.salesianostriana.dam.realestate.services.base.BaseService;
import com.salesianostriana.dam.realestate.users.dto.CreateUserDto;
import com.salesianostriana.dam.realestate.users.model.UserEntity;
import com.salesianostriana.dam.realestate.users.model.UserRole;
import com.salesianostriana.dam.realestate.users.repos.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserEntityService extends BaseService<UserEntity, Long, UserEntityRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repositorio.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email + "no encontrado"));
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
                    .roles(Stream.of(UserRole.PROPIETARIO).collect(Collectors.toSet()))
                    //Set.of(UserRole.PROPIETARIO)
                    .build();

            return save(userEntity);
        }else{
            return null;
        }
    }

    public UserEntity saveGestor(CreateUserDto newUser){
        if (newUser.getPassword().contentEquals(newUser.getPassword2())){
            UserEntity userEntity = UserEntity.builder()
                    .nombre(newUser.getNombre())
                    .apellidos(newUser.getApellidos())
                    .direccion(newUser.getDireccion())
                    .telefono(newUser.getTelefono())
                    .email(newUser.getEmail())
                    .avatar(newUser.getAvatar())
                    .roles(Stream.of(UserRole.PROPIETARIO).collect(Collectors.toSet()))
                    .build();

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
                    .roles(Stream.of(UserRole.PROPIETARIO).collect(Collectors.toSet()))
                    .build();

            return save(userEntity);
        }else{
            return null;
        }
    }

}
