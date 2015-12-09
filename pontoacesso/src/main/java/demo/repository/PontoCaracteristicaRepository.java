package demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import demo.model.PontoCaracteristica;
import demo.model.Pontos;

@Repository
public interface PontoCaracteristicaRepository extends
		CrudRepository<PontoCaracteristica, Long> {
	
	List<PontoCaracteristica> findByPonto(Pontos p);

}
