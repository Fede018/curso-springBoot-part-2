package com.portafolio.my_portafolio_backend.repository;

import com.portafolio.my_portafolio_backend.model.User;

import java.util.Optional;

public interface IUserRepository  {
    Optional<User> findByUsername(String username);


}
