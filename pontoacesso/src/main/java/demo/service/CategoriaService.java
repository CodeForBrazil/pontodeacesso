package demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.model.Categoria;
import demo.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria findById(Long id) {
		return repository.findOne(id);
	}

	public List<Categoria> findAll() {
		return (List<Categoria>) repository.findAll();
	}

}
