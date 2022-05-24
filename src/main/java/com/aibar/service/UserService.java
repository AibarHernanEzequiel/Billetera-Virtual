package com.aibar.service;

import com.aibar.controllers.RegisterData;
import com.aibar.model.Usuario;

public interface UserService {
    Usuario registrarUsuario(RegisterData data);
}
