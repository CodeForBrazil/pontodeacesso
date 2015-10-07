package demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.model.Caracteristica;
import demo.repository.CaracteristicaRepository;

@Service
public class CaracteristicaService {

	@Autowired
	private CaracteristicaRepository repository;

	public Caracteristica findById(Long id) {
		return repository.findOne(id);
	}
}
