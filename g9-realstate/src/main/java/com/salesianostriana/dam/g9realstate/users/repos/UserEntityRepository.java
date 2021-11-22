package com.salesianostriana.dam.g9realstate.users.repos;

import com.salesianostriana.dam.g9realstate.users.model.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);

}
