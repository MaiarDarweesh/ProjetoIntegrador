package com.generation.projetovida.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.projetovida.model.Categoria;

@Repository // informando para o spring que o CategoriaRepository é um repositorio
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	public List<Categoria> findAllByCursosContainingIgnoreCase(String cursos);
	// select * tb_categoria where cursos like "%cursos%"

	public List<Categoria> findAllByProdutosContainingIgnoreCase(String produtos);
	// select * tb_categoria where produtos like "%produtos%"
}
