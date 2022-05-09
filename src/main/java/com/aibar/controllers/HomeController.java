package com.aibar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@GetMapping(path = "/ir-a-registrarme")
	public ModelAndView irARegistrarme(ModelMap modelMap) {
		return new ModelAndView("register.html");
	}

	@GetMapping(path = "/ir-a-login")
	public ModelAndView irALoguearme(ModelMap modelMap) {
		return new ModelAndView("login.html");
	}

}
