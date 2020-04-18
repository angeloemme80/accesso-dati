package it.angelomassaro;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.JoinColumn;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.Scarpe;

import model.Utenti;

public class First {

	private static final EntityManagerFactory emFactoryObj;
    private static final String PERSISTENCE_UNIT_NAME = "PU_POSTGRES";  
 
    static {
        emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
 
    // This Method Is Used To Retrieve The 'EntityManager' Object
    public static EntityManager getEntityManager() {
        return emFactoryObj.createEntityManager();
    }
    
	public static void main(String[] args) {
		First first = new First();
		//first.inserisci();
        //first.queryTipoNamed();
		//first.queryConPaginazione();
		//first.queryTipoCriteria();
		//first.queryTipoCriteriaJoin();
		first.queryTipoQuery();
	}
	
	public void queryTipoQuery() {
		EntityManager em = getEntityManager();
		List<Object[]> l = em.createQuery("SELECT u, s FROM Utenti u LEFT JOIN u.scarpe s") //join dove la condizione di incrocia grazie al @JoinColumn nell'entity
        //List<Object[]> l = em.createQuery("SELECT u, s FROM Utenti u, Scarpe s WHERE u.id = s.idUtente") //join SENZA @JoinColumn nell'entity 
        		//.setFirstResult(4)
        		//.setMaxResults(2)
        		.getResultList();
        System.out.println("Totali: " + l.size());
        l.stream().forEach( e -> {
        	Utenti u = (Utenti) e[0];
        	Scarpe s = (Scarpe) e[1];
        	if(s!=null)
        		System.out.println( u.getNome() + " - " + s.getMarca() + " - " + s.getTipo() + " - " + s.getNumero() + " - " + s.getId() );
        	else
        		System.out.println( u.getNome() + " - NON HA SCARPE" );
        });
        em.clear();
	}
	
	public void queryTipoCriteriaJoin() {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Utenti> criteriaQuery = criteriaBuilder.createQuery(Utenti.class);
		Root<Utenti> utentiRoot = criteriaQuery.from(Utenti.class);
		//Join<Utenti, Scarpe> join = utentiRoot.join("scarpe",JoinType.LEFT);
		Join<Utenti, Scarpe> join = utentiRoot.join("scarpe",JoinType.INNER);
		join.on(criteriaBuilder.equal(utentiRoot.get("id"), join.get("idUtente")));
		//criteriaQuery = criteriaQuery.select(utentiRoot);
		
        List<Utenti> l = em.createQuery(criteriaQuery).getResultList();
        System.out.println("Totali: " + l.size());
        l.stream().forEach( e -> System.out.println(e.getNome() + " - " ) );
        em.clear();
		
		//Predicate predicateForCognomeMassaro = criteriaBuilder.equal(utentiRoot.get("cognome"), "Massaro");
		//criteriaQuery.where(predicateForCognomeMassaro);
		
		
	}
	
	public void queryTipoCriteria() {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Utenti> criteriaQuery = criteriaBuilder.createQuery(Utenti.class);
		Root<Utenti> utentiRoot = criteriaQuery.from(Utenti.class);
		Predicate predicateForCognomeMassaro = criteriaBuilder.equal(utentiRoot.get("cognome"), "Massaro");
		criteriaQuery.where(predicateForCognomeMassaro);
		List<Utenti> l = em.createQuery(criteriaQuery).getResultList();
		System.out.println("Totali: " + l.size());
        l.stream().forEach( e -> System.out.println(e.getNome()) );
        em.clear();
	}
	
	public void queryConPaginazione() {
		EntityManager em = getEntityManager();
		List<Scarpe> l = em.createNamedQuery("Scarpe.findAll")
				.setFirstResult(6)
				.setMaxResults(2)
        		.getResultList();
		System.out.println("Totali: " + l.size());
        l.stream().forEach( e -> System.out.println(e.getMarca() + " - " + e.getId()) );
        em.clear();
	}
	
	public void queryTipoNamed() {
		EntityManager em = getEntityManager();
        List<Utenti> l = em.createNamedQuery("Utenti.findAll")
        		.getResultList();
        System.out.println("Totali: " + l.size());
        l.stream().forEach( e -> System.out.println(e.getNome()) );
        em.clear();
	}
	
	public void inserisci() {
		EntityManager em = getEntityManager();
        em.getTransaction().begin();
		Utenti utenti = new Utenti();
        utenti.setNome("Javier");
        utenti.setCognome("Zanetti");
        em.persist(utenti);
        em.getTransaction().commit();
        em.clear();
        System.out.println("Record Successfully Inserted In The Database");
	}
	

}
