package ar.edu.unlam.tallerweb1.controladores;

public class ValidadorDeCorreo
{
    public static boolean validarCorreo(String correo)
    {
        return correo.contains("@") && correo.endsWith(".com");
    }
}
