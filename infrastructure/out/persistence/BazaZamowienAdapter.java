package infrastructure.out.persistence;

import java.util.HashMap;
import application.port.out.*;
import application.domain.*;

class BazaZamowienAdapter implements BazaZamowienPort {

	private java.util.Map<Integer, Zamowienie> zamowienia = new HashMap<>();

	/**
	 * 
	 * @param zamowienie
	 */
	public void zapisz(Zamowienie zamowienie) {
		zamowienia.put(zamowienie.getIdZamowienia(), zamowienie);
	}

	/**
	 * 
	 * @param idZamowienia
	 */
	public Zamowienie pobierz(int idZamowienia) {
		Zamowienie zamowienie = zamowienia.get(idZamowienia);
		if (zamowienie == null) {
			throw new IllegalArgumentException("Nie znaleziono zamowienia o id: " + idZamowienia);
		}
		return zamowienie;
	}

	/**
	 * 
	 * @param zamowienie
	 */
	public void aktualizuj(Zamowienie zamowienie) {
		if (!zamowienia.containsKey(zamowienie.getIdZamowienia())) {
			throw new IllegalArgumentException("Nie znaleziono zamowienia o id: " + zamowienie.getIdZamowienia());
		}
		zamowienia.put(zamowienie.getIdZamowienia(), zamowienie);
	}

}
