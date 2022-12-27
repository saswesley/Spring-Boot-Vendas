package io.github.saswesley.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.saswesley.domain.entity.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {
	
	Optional<Usuario> findByLogin (String login);
	
}
