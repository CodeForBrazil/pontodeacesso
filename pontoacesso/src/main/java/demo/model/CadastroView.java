package demo.model;

import java.util.List;

public class CadastroView {
	
	protected String nome;
	
	protected List<Integer> caracteristicas;
	
	protected String foto;
	
	protected Double latitude;
	
	protected Double longitude;
	
	protected Integer tipo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Integer> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<Integer> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}	
}
