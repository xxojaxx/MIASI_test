package infrastructure.out.payment;

import application.port.out.*;

class SystemPlatnosciAdapter implements PlatnoscPort {

	private ZewnetrznySystemPlatnosci zewnetrznySystemPlatnosci;

	/**
	 * 
	 * @param zewnetrznySystemPlatnosci
	 */
	SystemPlatnosciAdapter(ZewnetrznySystemPlatnosci zewnetrznySystemPlatnosci) {
		this.zewnetrznySystemPlatnosci = zewnetrznySystemPlatnosci;
	}

	/**
	 * 
	 * @param idKupujacego
	 * @param kwota
	 */
	public boolean zaplac(int idKupujacego, float kwota) {
		return zewnetrznySystemPlatnosci.zaplac(idKupujacego, kwota);
	}

}
