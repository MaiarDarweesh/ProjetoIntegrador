package com.generation.projetovida.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.projetovida.model.Categoria;
import com.generation.projetovida.repository.CategoriaRepository;


@RestController // está informando que CategoriaController é o nosso controller
@RequestMapping("/categoria") // definindo o caminho da rota, ou pagina
@CrossOrigin("*") // juntar front e back quando hospedado em sites diferentes
public class CategoriaController {

	@Autowired // troca a responsabilidade de CategoriaRepository para repository
	private CategoriaRepository repository;
	
	@GetMapping // metodo para pegar tudo, vai usar a mesma rota principal
	public ResponseEntity<List<Categoria>> GetAll (){ // metodo que busca tudo de categoria
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}") //metodo para pegar pelo id, vai usar a rota principal porem vai incluir uma / para puxar parametro
	public ResponseEntity<Categoria> GetById (@PathVariable Long id){ //Path pega esse parametro do body
		return repository.findById(id)    // pega todos os id
				.map(resp -> ResponseEntity.ok(resp)) // se tiver o id da ok
				.orElse(ResponseEntity.notFound().build());	// se n tiver, da erro
	}
	
	@GetMapping("/cursos/{cursos}") // metodo para pegar por uma descrica, vai usar uma rota /descricao e / para puxar o parametro
	public ResponseEntity<List<Categoria>> GetByCategoria (@PathVariable String cursos){ 
		return ResponseEntity.ok(repository.findAllByCursosContainingIgnoreCase(cursos)); // usa o metodo do CategoriaRepository
	}
	
	@GetMapping("/produtos/{produtos}") // metodo para pegar por uma descrica, vai usar uma rota /descricao e / para puxar o parametro
	public ResponseEntity<List<Categoria>> GetByProduto (@PathVariable String produtos){ 
		return ResponseEntity.ok(repository.findAllByProdutosContainingIgnoreCase(produtos)); // usa o metodo do CategoriaRepository
	}
	
	@PostMapping // metodo para postar alguma coisa, publicacao, postagem, vai usar a mesma rota principal
	public ResponseEntity<Categoria> Post (@Valid @RequestBody Categoria categoria){ // faz requisição da body
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria)); // salva a requisiçao
	}
	
	@PutMapping // metodo para atualizar dados ,vai usar a mesma rota principal
	public ResponseEntity<Categoria> Put (@Valid @RequestBody Categoria categoria){ // faz requisição da body
		return ResponseEntity.ok(repository.save(categoria));	 // atualiza a requisição
	}
	
	@DeleteMapping("/{id}") // metodo para apagar um id ,vai usar a mesma rota principal
	public void deleteId (@PathVariable Long id) { // pega a requisição
		repository.deleteById(id); // deleta o id
	
	}
}
