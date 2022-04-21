package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.excepciones.CorreoInvalidoException;

public class ValidadorDeCorreo
{
    public static boolean validarCorreo(String correo) throws CorreoInvalidoException {
        if (correo.contains("@") && correo.endsWith(".com"))
        {
            return true;
        }
        throw new CorreoInvalidoException();
    }
}
