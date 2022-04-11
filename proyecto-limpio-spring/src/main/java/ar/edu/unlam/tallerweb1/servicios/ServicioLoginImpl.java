package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cliente;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioLoginImpl implements ServicioLogin {
    @Override
    public Cliente buscarClientePorCorreo(String correo) {
        return null;
    }
}
