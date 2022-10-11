package io.github.saswesley.domain.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.saswesley.domain.entity.Cliente;


public interface Clientes extends JpaRepository<Cliente, Integer> {

	List<Cliente> findByNomeLike(String string);
	
			
}
