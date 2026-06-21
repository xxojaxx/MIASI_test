package application.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class ZamowienieFactory {

	private java.util.concurrent.atomic.AtomicInteger sekwencjaId = new AtomicInteger(1);

	/**
	 * 
	 * @param idKupujacego
	 */
	public Zamowienie utworz(int idKupujacego) {
		// TODO - implement ZamowienieFactory.utworz
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idZamowienia
	 * @param idKupujacego
	 * @param status
	 * @param pozycje
	 */
	public Zamowienie odtworz(int idZamowienia, int idKupujacego, StatusZamowienia status, java.util.List<PozycjaZamowienia> pozycje) {
		// TODO - implement ZamowienieFactory.odtworz
		throw new UnsupportedOperationException();
	}

}
