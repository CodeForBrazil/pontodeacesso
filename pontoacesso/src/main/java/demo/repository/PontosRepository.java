package demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import demo.model.Pontos;

@Repository
public interface PontosRepository extends CrudRepository<Pontos, Long> {

}
