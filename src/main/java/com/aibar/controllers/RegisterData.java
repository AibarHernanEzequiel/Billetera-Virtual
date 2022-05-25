package com.aibar.controllers;

public class RegisterData {

    public RegisterData() {

    }

    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String repeatPassword;
    private String nickname;

    public RegisterData(String nombre, String apellido, String email, String password, String repeatPassword, String nickname) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.nickname = nickname;
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

    public void setNickName(String nickName) {
        this.nickname = nickName;
    }
}
