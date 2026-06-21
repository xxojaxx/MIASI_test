package application.port.out;

import application.domain.*;

public interface BazaZamowienPort {

	/**
	 * 
	 * @param zamowienie
	 */
	abstract void zapisz(Zamowienie zamowienie);

	/**
	 * 
	 * @param idZamowienia
	 */
	abstract Zamowienie pobierz(int idZamowienia);

	/**
	 * 
	 * @param zamowienie
	 */
	abstract void aktualizuj(Zamowienie zamowienie);

}