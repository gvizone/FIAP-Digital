package br.com.fiap.jsf.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.fiap.ws.service.SelecaoService;
import br.com.fiap.ws.to.Selecao;

@ManagedBean
public class SelecaoBean {

	private Selecao selecao;
	private SelecaoService service;
	
	//Método de inicialização do ManagedBean
	@PostConstruct
	private void init() {
		selecao = new Selecao();
		service = new SelecaoService();
	}
	
	public void cadastrar() {		
		try {
			service.cadastrar(selecao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Selecao getSelecao() {
		return selecao;
	}

	public void setSelecao(Selecao selecao) {
		this.selecao = selecao;
	}
	
	
}
