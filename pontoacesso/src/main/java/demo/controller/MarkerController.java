package demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import demo.model.GeoLocation;
import demo.model.Location;
import demo.model.Pontos;
import demo.model.ResultadoGeolocation;
import demo.repository.PontosRepository;
import demo.service.GeoLocationService;

@Controller
@RequestMapping("/markers")
public class MarkerController {

	@Autowired
	private PontosRepository repository;

	@Autowired
	private GeoLocationService service;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<?> pontos() {
		List<Pontos> pontos = (List<Pontos>) repository.findAll();
		return new ResponseEntity<List<Pontos>>(pontos, HttpStatus.OK);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> adicionarMarcador(@RequestBody String endereco) {
		List<Pontos> lista = new ArrayList<Pontos>();
		try {
			ResultadoGeolocation coordenadas = service
					.converterEnderecoParaCoordenadas(endereco);
			Pontos p = null;
			for (GeoLocation geo : coordenadas.getResults()) {
				Location location = geo.getGeometry().getLocation();
				p = new Pontos(location.getLat(), location.getLng());
			}
			repository.save(p);
			lista = (List<Pontos>) repository.findAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Pontos>>(lista, HttpStatus.OK);
	}

}
