package ar.edu.unlam.tallerweb1.servicios;


import ar.edu.unlam.tallerweb1.excepciones.ClienteInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Cliente;

public interface ServicioCliente {
    Cliente buscarClientePorCorreo(String correo) throws ClienteInexistenteException;
}
