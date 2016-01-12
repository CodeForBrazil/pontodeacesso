package demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ponto_caracteristicas")
public class PontoCaracteristica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PontoCaracteristica() {
		// TODO Auto-generated constructor stub
	}

	public PontoCaracteristica(Pontos ponto, Caracteristica caracteristica) {
		this.ponto = ponto;
		this.caracteristica = caracteristica;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne
	@JoinColumn(name = "id_ponto")
	@JsonManagedReference
	@JsonIgnore
	private Pontos ponto;

	@OneToOne
	@JoinColumn(name = "id_caracteristica")
	private Caracteristica caracteristica;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pontos getPonto() {
		return ponto;
	}

	public void setPonto(Pontos ponto) {
		this.ponto = ponto;
	}

	public Caracteristica getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}

}
