package demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import demo.model.Categoria;
import demo.model.Pontos;

@Repository
public interface PontosRepository extends CrudRepository<Pontos, Long> {
	
	List<Pontos> findAllByCategoria(Categoria categoria);

}
