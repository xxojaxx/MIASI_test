package infrastructure.out.catalog;

import java.util.HashMap;
import application.port.out.*;
import application.domain.*;

class KatalogProduktowAdapter implements KatalogProduktowPort {

	private java.util.Map<Integer, ProduktInfo> produkty = new HashMap<>();
	private java.util.Map<Integer, Promocja> promocje = new HashMap<>();

	public KatalogProduktowAdapter() {
		produkty.put(1, new ProduktInfo(1, "Ksiazka", 50));
		produkty.put(2, new ProduktInfo(2, "Kurs online", 120));
		produkty.put(3, new ProduktInfo(3, "Notatnik", 20));

		promocje.put(1, new Promocja(2, 40));
		promocje.put(3, new Promocja(5, 15));
	}

	/**
	 * 
	 * @param idProduktu
	 */
	public ProduktInfo pobierzProdukt(int idProduktu) {
		ProduktInfo produkt = produkty.get(idProduktu);
		if (produkt == null) {
			throw new IllegalArgumentException("Nie znaleziono produktu o id: " + idProduktu);
		}
		return produkt;
	}

	/**
	 * 
	 * @param idProduktu
	 */
	public Promocja pobierzPromocje(int idProduktu) {
		return promocje.get(idProduktu);
	}

}
