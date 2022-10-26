package io.github.saswesley.rest.controller;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.saswesley.domain.entity.Produto;
import io.github.saswesley.domain.repository.ProdutosRepo;

@RestController
@RequestMapping("/api/produto/")
public class ProdutoController {
	
	private ProdutosRepo produtosrepo;
	
	public ProdutoController (ProdutosRepo produtosrepo) {
		this.produtosrepo = produtosrepo;
	}
	
	
	@GetMapping("{id}")
	public Produto getProdutoById(@PathVariable Integer id) {
		return produtosrepo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto save (@RequestBody Produto produto) {
		return produtosrepo.save(produto);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@PathVariable Integer id) {
		produtosrepo.findById(id).map(produto -> {
					produtosrepo.delete(produto);
					return Void.TYPE;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));		
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update (@PathVariable Integer id, @RequestBody Produto produto) {
		produtosrepo.findById(id)
		.map(produtoExistente -> {
			produto.setId(produtoExistente.getId());
			produtosrepo.save(produto);
			return produtoExistente;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));	
	}
	
	
	@GetMapping
	public List <Produto> find (Produto filtro){
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example<Produto> example = Example.of(filtro, matcher);
		return produtosrepo.findAll(example);
		
	}
	

}
