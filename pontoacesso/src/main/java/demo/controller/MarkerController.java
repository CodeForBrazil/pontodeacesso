package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import demo.model.Pontos;
import demo.repository.PontosRepository;

@Controller
@RequestMapping("/markers")
public class MarkerController {

	@Autowired
	private PontosRepository repository;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<?> pontos() {
		List<Pontos> pontos = (List<Pontos>) repository.findAll();
		return new ResponseEntity<List<Pontos>>(pontos, HttpStatus.OK);
	}

}
