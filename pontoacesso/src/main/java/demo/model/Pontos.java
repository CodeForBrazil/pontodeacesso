package demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pontos")
public class Pontos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;

	@Column(name = "nome")
	protected String nome;

	@Column(name = "descricao")
	protected String descricao;

	@Column(name = "latitude")
	protected Double latitude;

	@Column(name = "longitude")
	protected Double longitude;
	
	@Column(name = "foto")
	protected String foto;

	@OneToOne
	@JoinColumn(name = "id_categoria")
	protected Categoria categoria;

	public Pontos() {
	}

	public Pontos(Double latitude, Double longitude, Categoria categoria,
			String nome, String endereco) {
		this.nome = nome;
		this.latitude = latitude;
		this.longitude = longitude;
		this.categoria = categoria;
		this.descricao = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

}
