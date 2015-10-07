package demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import demo.model.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

}
