package infrastructure.out.catalog;

import java.util.HashMap;
import application.port.out.*;
import application.domain.*;

class KatalogProduktowAdapter implements KatalogProduktowPort {

	private java.util.Map<Integer, ProduktInfo> produkty = new HashMap<>();
	private java.util.Map<Integer, Promocja> promocje = new HashMap<>();

	public KatalogProduktowAdapter() {
		// TODO - implement KatalogProduktowAdapter.KatalogProduktowAdapter
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idProduktu
	 */
	public ProduktInfo pobierzProdukt(int idProduktu) {
		// TODO - implement KatalogProduktowAdapter.pobierzProdukt
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idProduktu
	 */
	public Promocja pobierzPromocje(int idProduktu) {
		// TODO - implement KatalogProduktowAdapter.pobierzPromocje
		throw new UnsupportedOperationException();
	}

}
