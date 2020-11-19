package com.empresa.controller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.empresa.entity.Cliente;
import com.empresa.service.ClienteService;



@Controller
public class LoginController {

	@Autowired
	private ClienteService servicio;

	@RequestMapping("/")
	public String verIndex() {
		return "Index";
	}
	
	@RequestMapping("/verlogin")
	public String verlogin() {
		return "login";
	}
	
	
	
	

	@RequestMapping("/login")
	public String login(Cliente cliente, HttpSession session, HttpServletRequest request) {

		Cliente objUsu = servicio.login(cliente);
		if (objUsu == null) {
			request.setAttribute("mensaje", "El usuario no existe");
			return "login";
		} else {
			session.setAttribute("objCliente", objUsu);
			return "intranetHome";
		}
	}
	

	
	
	@RequestMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		
		//Destruir todos los objetos de la sesion mediante programación
		session.invalidate();

		response.setHeader("Cache-control", "no-cache");
		response.setHeader("Expires", "0");
		response.setHeader("Pragma", "no-cache");

		request.setAttribute("mensaje", "El usuario salió de sesión");
		return "login";

	}
	
	@RequestMapping("/verIntranetHome")
	public String verIntranet() {
		return "intranetHome";
	}

}
