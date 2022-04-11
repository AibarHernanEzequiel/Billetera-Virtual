package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioClienteImpl implements RepositorioCliente {
    @Override
    public Cliente buscarClientePorCorreo(String correo) {
        return null;
    }
}
