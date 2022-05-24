package com.aibar.model;

import com.aibar.controllers.RegisterData;

public class Usuario {

    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String repeatPassword;
    private String nickname;

    public Usuario(RegisterData registerData) {
        this.nombre = registerData.getNombre();
        this.apellido = registerData.getApellido();
        this.email = registerData.getEmail();
        this.password = registerData.getPassword();
        this.repeatPassword = registerData.getRepeatPassword();
        this.nickname = registerData.getNickName();
    }

    public Usuario() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getNickName() {
        return this.nickname;
    }
}
