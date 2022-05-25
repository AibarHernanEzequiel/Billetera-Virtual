package com.aibar.repositories;

import com.aibar.model.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public Usuario buscarUsuarioPorEmail(String email) {
        return null;
    }

    @Override
    public Usuario registrarUsuario(Usuario registrado) {
        return null;
    }
}
