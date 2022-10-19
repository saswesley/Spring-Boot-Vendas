package io.github.saswesley.rest.controller;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
}
