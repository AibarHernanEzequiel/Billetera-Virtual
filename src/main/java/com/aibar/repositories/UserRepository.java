package com.aibar.repositories;

import com.aibar.model.Usuario;

public interface UserRepository {
    Usuario buscarUsuarioPorEmail(String email);

    Usuario registrarUsuario(Usuario registrado);
}
