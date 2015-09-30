package demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import demo.model.Caracteristica;

@Repository
public interface CaracteristicaRepository extends
		CrudRepository<Caracteristica, Long> {

}
