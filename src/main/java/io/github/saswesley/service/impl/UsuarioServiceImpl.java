package io.github.saswesley.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.saswesley.domain.entity.Usuario;
import io.github.saswesley.domain.repository.UsuarioRepo;

@Service
public class UsuarioServiceImpl implements UserDetailsService{

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UsuarioRepo usuariorepo;
	
	
	@Transactional
	public Usuario salvar (Usuario usuario) {
		return usuariorepo.save(usuario);
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			Usuario usuario = usuariorepo.findByLogin(username)
					.orElseThrow(() -> new UsernameNotFoundException(username + " não encontrado na base de dados, tente outro usuário"));
			
			String [] roles = usuario.isAdmin() ?
					new String [] {"ADMIN", "USER"} : new String [] {"USER"};
			
			return User
					.builder()
					.username(usuario.getLogin())
					.password(usuario.getSenha())
					.roles(roles)
					.build();
	}

}
