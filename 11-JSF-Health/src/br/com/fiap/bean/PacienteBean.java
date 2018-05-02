package br.com.fiap.bean;

import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.fiap.entity.Paciente;
import br.com.fiap.service.PacienteService;

@ManagedBean
public class PacienteBean {

	private Paciente paciente;
	private PacienteService service;

	@PostConstruct
	private void init() {
		paciente = new Paciente();
		//Inicializar a data
		paciente.setDataNascimento(Calendar.getInstance());
		service = new PacienteService();
	}
	
	public void excluir(int codigo) {
		FacesMessage msg;
		try {
			service.remover(codigo);
			msg = new FacesMessage("Removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro...");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		FacesMessage msg;
		try {
			if (paciente.getCodigo() == 0) {
				service.cadastrar(paciente);
				msg = new FacesMessage("Cadastrado!");				
			}else {
				service.atualizar(paciente);
				msg = new FacesMessage("Atualizado!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro...");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "lista-paciente"; //nome da página
	}
	
	public List<Paciente> getPacientes(){
		try {
			return service.listar();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
}