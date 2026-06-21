package application.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class ZamowienieFactory {

	private java.util.concurrent.atomic.AtomicInteger sekwencjaId = new AtomicInteger(1);

	/**
	 * 
	 * @param idKupujacego
	 */
	public Zamowienie utworz(int idKupujacego) {
		return new Zamowienie(sekwencjaId.getAndIncrement(), idKupujacego, StatusZamowienia.WSTEPNE);
	}

	/**
	 * 
	 * @param idZamowienia
	 * @param idKupujacego
	 * @param status
	 * @param pozycje
	 */
	public Zamowienie odtworz(int idZamowienia, int idKupujacego, StatusZamowienia status, java.util.List<PozycjaZamowienia> pozycje) {
		return new Zamowienie(idZamowienia, idKupujacego, status, pozycje);
	}

}
