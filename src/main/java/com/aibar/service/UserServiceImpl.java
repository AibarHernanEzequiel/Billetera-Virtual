package com.aibar.service;

import com.aibar.controllers.RegisterData;
import com.aibar.exceptions.UsuarioYaExistenteException;
import com.aibar.model.Usuario;
import com.aibar.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario registrarUsuario(RegisterData data) throws UsuarioYaExistenteException {
        Usuario buscado = repository.buscarUsuarioPorEmail(data.getEmail());
        if (buscado != null) {
            throw new UsuarioYaExistenteException();
        }
        return repository.registrarUsuario(new Usuario(data));
    }
}
