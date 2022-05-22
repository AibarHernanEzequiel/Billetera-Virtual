package com.aibar.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class HomeControllerTest {

    private HomeController homeController;
    private ModelAndView modelAndView;

    @BeforeEach
    void init() {
        this.homeController = new HomeController();
        this.modelAndView = new ModelAndView();
    }

    @Test
    void dadoQueUnUsuarioDeseaIngresarARegistrarseDeberiaMostrarElFormularioDeRegistro() {
        givenQueUnUsuarioDeseaIngresarARegistrarse();
        whenIngresaARegistrarse(modelAndView.getModelMap());
        thenDeberiaMostrarElFormularioDeRegistro(modelAndView);
    }

    private void givenQueUnUsuarioDeseaIngresarARegistrarse() {
    }

    private void whenIngresaARegistrarse(ModelMap modelMap) {
        this.modelAndView = this.homeController.irARegistrarme(modelMap);
    }

    private void thenDeberiaMostrarElFormularioDeRegistro(ModelAndView modelAndView2) {
        assertThat(modelAndView2.getViewName()).isEqualTo("register.html");
    }

    @Test
    void dadoQueUnUsuarioDeseaLoguearseDeberiaMostrarElFormularioDeLogin() {
        givenQueUnUsuarioDeseaIngresarALoguearse();
        whenIngresaALoguearse(modelAndView.getModelMap());
        thenDeberiaMostrarElFormularioDeLogin(modelAndView);
    }

    private void givenQueUnUsuarioDeseaIngresarALoguearse() {
    }

    private void whenIngresaALoguearse(ModelMap modelMap) {
        this.modelAndView = homeController.irALoguearme(modelMap);
    }

    private void thenDeberiaMostrarElFormularioDeLogin(ModelAndView modelAndView2) {
        assertThat(modelAndView2.getViewName()).isEqualTo("login.html");
    }
}
