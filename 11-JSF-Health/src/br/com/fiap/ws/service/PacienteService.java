package br.com.fiap.ws.service;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.fiap.ws.to.Paciente;

//Realiza o acesso ao webservice
public class PacienteService {

	private Client client = Client.create();
	
	private static final String URL = "http://localhost:8080/07-WS-Restful-Server/rest/paciente";
	
	public void remover(int codigo) throws Exception {
		WebResource resource = client.resource(URL)
				.path(String.valueOf(codigo));
		
		ClientResponse response = resource
				.delete(ClientResponse.class);
		
		if (response.getStatus() != 204) {
			throw new Exception("Erro: " + response.getStatus());
		}
	}
	
	public void atualizar(Paciente paciente) throws Exception {
		WebResource resource = client.resource(URL)
				.path(String.valueOf(paciente.getCodigo()));
	
		ClientResponse response = resource
				.type(MediaType.APPLICATION_JSON)
				.put(ClientResponse.class,paciente);
		
		if (response.getStatus() != 200) {
			throw new Exception("Erro: " + response.getStatus());
		}
	}
	
	public Paciente buscar(int codigo) throws Exception {
		WebResource resource = client.resource(URL)
					//Adiciona o código na URL
					.path(String.valueOf(codigo));
		
		ClientResponse response = resource
				.accept(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		
		//Valida o status 200 OK
		if (response.getStatus() != 200) {
			throw new Exception("Erro: " + response.getStatus());
		}
		//Recupera o paciente 
		return response.getEntity(Paciente.class);
	}
	
	public void cadastrar(Paciente paciente) throws Exception {
		WebResource resource = client.resource(URL);
		
		ClientResponse response = resource
				//Formato de dados que será enviado ao ws
				.type(MediaType.APPLICATION_JSON)
				//Faz um POST enviando os dados (seleção)
				.post(ClientResponse.class,paciente);
		
		//Valida se cadastrou HTTP Status 201 Created
		if (response.getStatus() != 201) {
			throw new Exception("Erro: " + response.getStatus());
		}
	}
	
	public List<Paciente> listar() throws Exception {
		//Cria o acesso ao web service com a URL
		WebResource resource = client.resource(URL);
		//Chamar o webservice
		ClientResponse response = resource
				//Configura o formato de resposta - JSON
				.accept(MediaType.APPLICATION_JSON)
				//Faz a requisição do tipo GET
				.get(ClientResponse.class);
		
		//Valida a resposta do servidor - Status HTTP 200 ok
		if (response.getStatus() != 200) {
			throw new Exception("ERRO:" + response.getStatus());
		}
		//Recupera os pacientes e retorna 
		return Arrays.asList(
						response.getEntity(Paciente[].class));
	}
	
}





