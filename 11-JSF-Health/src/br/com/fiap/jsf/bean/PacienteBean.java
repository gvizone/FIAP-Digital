package br.com.fiap.jsf.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.fiap.ws.service.PacienteService;
import br.com.fiap.ws.to.Paciente;

@ManagedBean
public class PacienteBean {

	private Paciente paciente;
	private PacienteService service; //Não tem get e set
	
	//Método de inicialização do ManagedBean
	@PostConstruct
	private void init() {
		paciente = new Paciente();
		service = new PacienteService();
		
	}
	
	//Método para excluir seleção
	public void deletar(int codigo) {
		FacesMessage msg;
		try {
			service.remover(codigo);
			msg = new FacesMessage("Excluido");
		} catch(Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro...");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	//Retorna a lista de seleções para ser exibida em uma tabela HTML
	public List<Paciente> getSelecoes() {
		try {
			return service.listar();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void salvar() {
		FacesMessage msg; //Objeto para exibir msg na tela
		try {
			//Verifica se precisa cadastrar ou atualizar
			if (paciente.getCodigo() == 0) {
				service.cadastrar(paciente);
				msg = new FacesMessage("Cadatrado!");
			}else {
				service.atualizar(paciente);
				msg = new FacesMessage("Atualizado!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro...");
		}
		//Adiciona a mensagem para a tela exibir
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}