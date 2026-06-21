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
		// TODO - implement BazaZamowienAdapter.zapisz
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idZamowienia
	 */
	public Zamowienie pobierz(int idZamowienia) {
		// TODO - implement BazaZamowienAdapter.pobierz
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param zamowienie
	 */
	public void aktualizuj(Zamowienie zamowienie) {
		// TODO - implement BazaZamowienAdapter.aktualizuj
		throw new UnsupportedOperationException();
	}

}
