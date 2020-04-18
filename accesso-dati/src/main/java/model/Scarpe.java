package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the scarpe database table.
 * 
 */
@Entity
@NamedQuery(name="Scarpe.findAll", query="SELECT s FROM Scarpe s")
@Table(name="scarpe", schema = "schema01")
public class Scarpe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="id_utente")
	private Integer idUtente;

	private String marca;

	private Integer numero;

	private String tipo;

	public Scarpe() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdUtente() {
		return this.idUtente;
	}

	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}