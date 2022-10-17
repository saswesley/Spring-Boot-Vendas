package io.github.saswesley.domain.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.saswesley.domain.entity.Cliente;


public interface Clientes extends JpaRepository<Cliente, Integer> {

	@Query(value = " SELECT c FROM Cliente c WHERE c.nome LIKE :nome")
	List<Cliente> encontrarnome(@Param ("nome") String nome);
	
	
	List<Cliente> findByNomeLike(String nome);
	
	List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);
	
	boolean existsByNome (String nome);
	
	@Query(" DELETE FROM Cliente c WHERE c.nome = :nome")
	@Modifying
	void deleteByNome(String nome);
	
			
}
