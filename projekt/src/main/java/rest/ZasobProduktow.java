package rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Produkt;
import domain.TypProduktu;

@Path("/produkty")
@Stateless
public class ZasobProduktow{
	
	@PersistenceContext
	EntityManager em;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List <Produkt> wszystkieProdukty(){
		
		return em.createNamedQuery("produkt.all", Produkt.class)
				.getResultList();
	}
	
	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response produktPoId(@PathParam("id") int id){
		
		Produkt wynik = em.createNamedQuery("produkt.id", Produkt.class)
				.setParameter("produktId", id)
				.getSingleResult();
		
		if(wynik == null){
			
			return Response.status(404).build();
		}
		
		return Response.ok(wynik).build();
	}
	
	@PUT
	@Path("/id/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response aktualizujProdukt(@PathParam("id") int id, Produkt produkt){
		
		Produkt wynik = em.createNamedQuery("produkt.id", Produkt.class)
				.setParameter("produktId", id)
				.getSingleResult();
		
		if(wynik == null){
			
			return Response.status(404).build();
		}
		else{
			
			wynik.setNazwa(produkt.getNazwa());
			wynik.setCena(produkt.getCena());
			wynik.setTypProduktu(produkt.getTypProduktu());
			
			em.persist(wynik);
		}
		
		return Response.ok().build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response dodajProdukt(Produkt produkt){
		
		em.persist(produkt);
		return Response.ok(produkt.getId()).build();
	}
	
	@GET
	@Path("/cena/{cenaOd}/{cenaDo}")
	@Produces(MediaType.APPLICATION_JSON)
	public List <Produkt> wyswietlProduktyPoZakresieCenowym(@PathParam("cenaOd") int cenaOd, @PathParam("cenaDo") int cenaDo){
		
		return em.createNamedQuery("produkt.cena", Produkt.class)
				.setParameter("cenaOd", cenaOd)
				.setParameter("cenaDo", cenaDo)
				.getResultList();
	}
	
	@GET
	@Path("/kategoria/{typ}")
	@Produces(MediaType.APPLICATION_JSON)
	public List <Produkt> wyswietlProduktyPoKategorii(@PathParam("typ") TypProduktu typ){
		
		return em.createNamedQuery("produkt.kategoria", Produkt.class)
				.setParameter("typ", typ)
				.getResultList();
	}
	
	@GET
	@Path("/nazwa/{nazwa}")
	@Produces(MediaType.APPLICATION_JSON)
	public List <Produkt> wyswietlProduktyPoNazwie(@PathParam("nazwa") String nazwa){
		
		return em.createNamedQuery("produkt.nazwa", Produkt.class)
				.setParameter("produktNazwa", nazwa)
				.getResultList();
	}
}