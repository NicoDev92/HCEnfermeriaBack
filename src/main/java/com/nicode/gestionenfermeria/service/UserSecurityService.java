package com.nicode.gestionenfermeria.service;


import com.nicode.gestionenfermeria.persistance.entity.UserEntity;
import com.nicode.gestionenfermeria.persistance.entity.UserRoleEntity;
import com.nicode.gestionenfermeria.persistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSecurityService implements UserDetailsService {


    private final UserRepository userRepository;

    @Autowired
    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> userEntity = Optional.of(this.userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario " + username + " no encontrado")));

        String[] roles = userEntity.get().getRoles().stream().map(UserRoleEntity::getRole).toArray(String[]::new);

        return User.builder()
                .username(userEntity.get().getUsername())
                .password(userEntity.get().getPassword())
                .roles(roles)
                .accountLocked(userEntity.get().getLocked())
                .disabled(userEntity.get().getDisabled())
                .build();
    }

}
