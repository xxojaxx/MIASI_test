package application.port.out;

import application.domain.*;

public interface KatalogProduktowPort {

	/**
	 * 
	 * @param idProduktu
	 */
	abstract ProduktInfo pobierzProdukt(int idProduktu);

	/**
	 * 
	 * @param idProduktu
	 */
	abstract Promocja pobierzPromocje(int idProduktu);

}