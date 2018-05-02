package br.com.fiap.service;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.fiap.entity.Paciente;

public class PacienteService {

	private final static String URL = "http://localhost:8080/09-WS-Restful-Health/rest/paciente";
	
	private Client client = Client.create();
	
	public void cadastrar(Paciente paciente) throws Exception {
		WebResource resource = client.resource(URL);
		ClientResponse resp = resource.type(MediaType.APPLICATION_JSON)
									.post(ClientResponse.class,paciente);
		if (resp.getStatus() != 201) {
			throw new Exception("Erro: " + resp.getStatus());
		} 
	}
	
	public void atualizar(Paciente paciente) throws Exception {
		WebResource resource = client.resource(URL)
				.path(String.valueOf(paciente.getCodigo()));
		ClientResponse resp = resource.type(MediaType.APPLICATION_JSON)
				.put(ClientResponse.class,paciente);
		if (resp.getStatus() != 200) {
			throw new Exception("Erro: " + resp.getStatus());
		}
	}
	
	public Paciente buscar(int codigo) throws Exception {
		WebResource resource = client.resource(URL)
				.path(String.valueOf(codigo));
		ClientResponse resp = resource.accept(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		if (resp.getStatus() != 200) {
			throw new Exception("Erro: " + resp.getStatus());
		}
		return resp.getEntity(Paciente.class);
	}
	
	public List<Paciente> listar() throws Exception{
		WebResource resource = client.resource(URL);
		ClientResponse resp = resource.accept(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		if (resp.getStatus() != 200) {
			throw new Exception("Erro: " + resp.getStatus());
		}
		return Arrays.asList(resp.getEntity(Paciente[].class));
	}
	
	public void remover(int codigo) throws Exception {
		WebResource resource = client.resource(URL).path(String.valueOf(codigo));
		ClientResponse resp = resource.delete(ClientResponse.class);
		if (resp.getStatus() != 204) {
			throw new Exception("Erro: " + resp.getStatus());
		}
	}
	
}
