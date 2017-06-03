package domain;

import java.util.ArrayList;
import java.util.List;

public class ProduktSerwis{
	
	static private List <Produkt> produkt = new ArrayList <Produkt> ();

	public List<Produkt> getProdukt() {
		return produkt;
	}
	
	public static void setProdukt(List<Produkt> produkt) {
		ProduktSerwis.produkt = produkt;
	}
	
	public Produkt getProduktById(int id){
		
		for(Produkt produkt : produkt){
			
			if(produkt.getId() == id){
				
				return produkt;
			}
		}
		
		return null;
	}
}