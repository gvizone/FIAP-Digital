package br.com.fiap.jsf.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Login {

	private String user;
	private String pass;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public void logar() {
		if ("gabriel".equals(getUser()) && "1234".equals(getPass())) {
			System.out.println("Usuario logado: " + getUser());
		} else {
			System.out.println("Usuario com senha incorreta");
		}
	}

	
	
	
}
