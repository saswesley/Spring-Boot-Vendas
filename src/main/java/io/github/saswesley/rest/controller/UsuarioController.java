package io.github.saswesley.rest.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.saswesley.domain.entity.Usuario;
import io.github.saswesley.exception.SenhaInvalidaException;
import io.github.saswesley.rest.dto.CredenciaisDTO;
import io.github.saswesley.rest.dto.TokenDTO;
import io.github.saswesley.security.jwt.JwtService;
import io.github.saswesley.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuario/")
@RequiredArgsConstructor
public class UsuarioController {
	
	private final UsuarioServiceImpl usuarioService;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtService jwtService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar (@RequestBody @Valid Usuario usuario) {
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		return usuarioService.salvar(usuario);
	}
	
	@PostMapping("/auth")
	public TokenDTO autenticar (@RequestBody CredenciaisDTO credenciais) {
		try {
				Usuario usuario = Usuario.builder()
																	.login(credenciais.getLogin())
																	.senha(credenciais.getSenha())
																	.build();
			UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
			String token = jwtService.gerarToken(usuario);
			
			return  new TokenDTO(usuario.getLogin(), token);
		} catch (UsernameNotFoundException | SenhaInvalidaException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}
	
}