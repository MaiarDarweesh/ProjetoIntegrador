package com.generation.projetovida.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.projetovida.model.Produto;
import com.generation.projetovida.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repositoryProd;
	
	public Optional<Produto>cadastroProduto(Produto produto){
		// primeiro valida se o produto já existe no banco
		if(repositoryProd.findByNome(produto.getNome()).isPresent())
			return Optional.empty();
		
			//e por ultimo, salvo o produto  no banco de dados
			return Optional.of(repositoryProd.save(produto));
	}
	
	/*	public Optional<Produto>atualizarProduto(Produto produto){
		
		if(repositoryProd.findById(produto.getId()).isPresent())
			return Optional.empty();
		
			return Optional.ofNullable(repositoryProd.save(produto));
	}*/

}
