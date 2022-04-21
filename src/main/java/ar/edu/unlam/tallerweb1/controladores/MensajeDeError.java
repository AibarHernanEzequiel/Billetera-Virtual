package ar.edu.unlam.tallerweb1.controladores;

public class MensajeDeError
{
    public static String correoInvalido()
    {
        return "Se introdujo un correo invalido, verifique el correo ingresado";
    }

    public static String claveInvalida()
    {
        return "Ingresaste un clave invalida, para que sea valida debe contener 10 caracteres, entre ellos al menos una mayuscula, y debe contener al menos dos numeros";
    }
}
