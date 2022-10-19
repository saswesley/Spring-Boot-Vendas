package io.github.saswesley.rest.controller;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.saswesley.domain.entity.Cliente;
import io.github.saswesley.domain.repository.ClientesRepo;


@Controller
public class ClienteController {
	
	private ClientesRepo clientesrepo;
	
	public ClienteController (ClientesRepo clientesrepo) {
		this.clientesrepo = clientesrepo;
	}
	
	@GetMapping("/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id ) {
		Optional<Cliente> cliente = clientesrepo.findById(id);
		
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/api/clientes/")
	@ResponseBody
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		Cliente clienteSalvo = clientesrepo.save(cliente);
		return ResponseEntity.ok(clienteSalvo);
		
	}
	
	@DeleteMapping("/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity<Cliente> delete(@PathVariable Integer id) {
		Optional<Cliente> cliente = clientesrepo.findById(id);
		
		if(cliente.isPresent()) {
			clientesrepo.delete(cliente.get());
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Cliente cliente){
		return clientesrepo.findById(id).map(clienteExistente -> { 
			cliente.setId(clienteExistente.getId());
			clientesrepo.save(cliente);
			return ResponseEntity.noContent().build();
		}).orElseGet( () -> ResponseEntity.notFound().build() );
				
				
	}
	
}
