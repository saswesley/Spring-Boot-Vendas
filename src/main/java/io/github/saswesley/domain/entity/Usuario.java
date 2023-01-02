package io.github.saswesley.domain.entity;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "login")
	@NotEmpty(message = "{campo.login.obrigatorio}")
	private String login;
	
	@NotEmpty(message = "{campo.senha.obrigatorio}")
	@Column(name = "senha")
	private String senha;
	
	@Column(name = "admin")
	private boolean admin;
	
	
}
