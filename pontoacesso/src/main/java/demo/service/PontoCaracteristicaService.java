package demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.model.PontoCaracteristica;
import demo.repository.PontoCaracteristicaRepository;

@Service
public class PontoCaracteristicaService {

	@Autowired
	private PontoCaracteristicaRepository repository;

	public void salvar(PontoCaracteristica pc) {
		repository.save(pc);
	}
}
