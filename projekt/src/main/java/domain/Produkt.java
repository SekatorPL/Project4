package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries({
	
	@NamedQuery(name="produkt.all", query="SELECT p FROM Produkt p"),
	@NamedQuery(name="produkt.id", query="FROM Produkt p WHERE p.id=:produktId"),
	@NamedQuery(name="produkt.cena", query="FROM Produkt p WHERE p.cena>=:cenaOd AND p.cena <=:cenaDo"),
	@NamedQuery(name="produkt.kategoria", query="FROM Produkt p WHERE p.typProduktu=:typ"),
	@NamedQuery(name="produkt.nazwa", query="FROM Produkt p WHERE p.nazwa=:produktNazwa"),
})
public class Produkt{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nazwa;
	private int cena;
	private TypProduktu typProduktu;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public TypProduktu getTypProduktu() {
		return typProduktu;
	}
	public void setTypProduktu(TypProduktu typProduktu) {
		this.typProduktu = typProduktu;
	}
}