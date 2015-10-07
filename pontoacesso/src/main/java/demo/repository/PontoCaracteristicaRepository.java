package demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import demo.model.PontoCaracteristica;

@Repository
public interface PontoCaracteristicaRepository extends
		CrudRepository<PontoCaracteristica, Long> {

}
