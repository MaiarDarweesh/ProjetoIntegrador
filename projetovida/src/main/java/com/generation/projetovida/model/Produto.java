package com.generation.projetovida.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // Faz o objeto virar uma table no BD
@Table(name = "tb_produto") // Dá um nome para a tabela no meu banco de dados
public class Produto {
	
	@Id// Definir a coluna de id como chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)// Equivalente ao auto_increment no mysql
	private Long id;

	@NotBlank// Define que o campo não pode ter espaços vazios e em branco
	@Size(min=3, max=255, message = "O campo deve ter no minimo 3 caracteres e no maximo 255 caracteres")
	private String nome;

	@NotBlank // Define que o campo não pode ter espaços vazios
	@Size(min=3, max=255, message = "O campo deve ter no minimo 3 caracteres e no maximo 255 caracteres")
	private String descricao;
	
	@NotNull // Define que o campo não pode ter espaços vazios
	private BigDecimal preco;
	
	@NotBlank // Define que o campo não pode ter espaços vazios
	@Size(min=3, max=4000, message = "O campo deve ter no minimo 3 caracteres e no maximo 4000 caracteres")
	private String foto;
	
	
	@ManyToOne // Varios produtos com um usuario
	@JsonIgnoreProperties("produto") // recursividade - Ignora o termo usuário na busca
	private Categoria categoria;
	
	@ManyToOne // Varios produtos com um usuario
	@JsonIgnoreProperties("produto") // recursividade - Ignora o termo usuário na busca
	private Usuario usuario;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
		
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
}
