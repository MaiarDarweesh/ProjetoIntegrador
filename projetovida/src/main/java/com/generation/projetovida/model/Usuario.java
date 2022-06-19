package com.generation.projetovida.model;


import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity // Faz o objeto virar uma table no BD
@Table(name = "tb_usuario") // Dá um nome para a tabela no meu banco de dados
public class Usuario {
		
		@Id // Definir a coluna de id como chave primaria
		@GeneratedValue(strategy = GenerationType.IDENTITY) // Equivalente ao auto_increment no mysql
		private Long id;

		@NotBlank // Define que o campo não pode ter espaços vazios e em branco
		private String nome;

		@Schema(example = "email@email.com.br")
		@NotNull(message = "O atributo Usuário é Obrigatório!")
		@Email (message = "O atributo Usuário deve ser um email válido!")
		private String usuario;

	
		@NotBlank // Define que o campo não pode ter espaços vazios e em branco
		private String senha;
		
		private String voluntario;
		
		@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL) // Um usuário para vários produtos
		@JsonIgnoreProperties("usuario") // recursividade - Ignora o termo usuário na busca
		private List<Produto> produto;

		//Getters and Setters - Permite que outras classes podem intereragir com os atributos privados dessa classe
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getUsuario() {
			return usuario;
		}

		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}
	
		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public String getVoluntario() {
			return voluntario;
		}

		public void setVoluntario(String voluntario) {
			this.voluntario = voluntario;
		}

		public List<Produto> getProduto() {
			return produto;
		}

		public void setProduto(List<Produto> produto) {
			this.produto = produto;
		}
}