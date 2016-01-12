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
import org.springframework.web.bind.annotation.RequestParam;

import demo.model.CadastroView;
import demo.model.Caracteristica;
import demo.model.Categoria;
import demo.model.GeoLocation;
import demo.model.Location;
import demo.model.PontoCaracteristica;
import demo.model.Pontos;
import demo.model.ResultadoGeolocation;
import demo.repository.PontosRepository;
import demo.service.CaracteristicaService;
import demo.service.CategoriaService;
import demo.service.GeoLocationService;
import demo.service.PontoCaracteristicaService;

@Controller
@RequestMapping("/markers")
public class MarkerController {

	@Autowired
	private PontosRepository repository;

	@Autowired
	private GeoLocationService service;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private CaracteristicaService caracteristicaService;

	@Autowired
	private PontoCaracteristicaService pontoCaracteristicaService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<?> pontos() {
		List<Pontos> pontos = (List<Pontos>) repository.findAll();
		return new ResponseEntity<List<Pontos>>(pontos, HttpStatus.OK);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ResponseEntity<?> adicionarMarcador(@RequestParam String nome,
			@RequestParam String endereco, @RequestParam Long categoria,
			@RequestParam String caracteristicas) {
		List<Pontos> lista = new ArrayList<Pontos>();
		try {
			ResultadoGeolocation coordenadas = service
					.converterEnderecoParaCoordenadas(endereco);
			Pontos p = null;
			for (GeoLocation geo : coordenadas.getResults()) {
				Location location = geo.getGeometry().getLocation();
				Categoria c = categoriaService.findById(categoria);
				p = new Pontos(location.getLat(), location.getLng(), c, nome,
						geo.getFormatted_address());
			}
			repository.save(p);

			String[] caracs = caracteristicas.split(",");
			for (int i = 0; i < caracs.length; i++) {
				Caracteristica caracteristica = caracteristicaService
						.findById(new Long(caracs[i]));
				PontoCaracteristica pc = new PontoCaracteristica(p,
						caracteristica);
				pontoCaracteristicaService.salvar(pc);
			}
			lista = (List<Pontos>) repository.findAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Pontos>>(lista, HttpStatus.OK);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> adicionar(@RequestBody CadastroView cadastroView) {
		Pontos p = new Pontos();
		p.setCategoria(categoriaService.findById(Long.valueOf(cadastroView
				.getTipo())));
		p.setNome(cadastroView.getNome());
		p.setLatitude(cadastroView.getLatitude());
		p.setLongitude(cadastroView.getLongitude());
		p.setFoto(cadastroView.getFoto());
		repository.save(p);
		for (Integer c : cadastroView.getCaracteristicas()) {
			Caracteristica caracteristica = caracteristicaService.findById(c
					.longValue());
			PontoCaracteristica pc = new PontoCaracteristica(p, caracteristica);
			pontoCaracteristicaService.salvar(pc);

		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public ResponseEntity<?> find(@RequestParam Long categoria) {
		Categoria cat = categoriaService.findById(categoria);
		List<Pontos> pontos = repository.findAllByCategoria(cat);
		return new ResponseEntity<List<Pontos>>(pontos, HttpStatus.OK);
	}

}
