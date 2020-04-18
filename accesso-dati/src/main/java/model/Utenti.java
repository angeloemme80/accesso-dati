package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.persistence.metamodel.StaticMetamodel;


/**
 * The persistent class for the utenti database table.
 * 
 */
@Entity
@NamedQuery(name="Utenti.findAll", query="SELECT u FROM Utenti u")
@Table(name="utenti", schema = "schema01")
public class Utenti implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cognome;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_utente")
	private List<Scarpe> scarpe = new ArrayList<>();
	
	
	
	public List<Scarpe> getScarpe() {
		return scarpe;
	}

	public void setScarpe(List<Scarpe> scarpe) {
		this.scarpe = scarpe;
	}

	public Utenti() {
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}