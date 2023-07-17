package com.nicode.gestionenfermeria.persistance.repository;

import com.nicode.gestionenfermeria.persistance.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}
