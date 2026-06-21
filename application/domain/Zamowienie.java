package application.domain;

import java.util.*;

public class Zamowienie {

	private int idZamowienia;
	private int idKupujacego;
	private Collection<PozycjaZamowienia> pozycje;
	private StatusZamowienia status;

	public int getIdZamowienia() {
		return this.idZamowienia;
	}

	public int getIdKupujacego() {
		return this.idKupujacego;
	}

	public StatusZamowienia getStatus() {
		return this.status;
	}

	public void setStatus(StatusZamowienia status) {
		this.status = status;
	}

	/**
	 * 
	 * @param idZamowienia
	 * @param idKupujacego
	 * @param status
	 */
	public Zamowienie(int idZamowienia, int idKupujacego, StatusZamowienia status) {
		// TODO - implement Zamowienie.Zamowienie
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idZamowienia
	 * @param idKupujacego
	 * @param status
	 * @param pozycje
	 */
	public Zamowienie(int idZamowienia, int idKupujacego, StatusZamowienia status, java.util.List<PozycjaZamowienia> pozycje) {
		// TODO - implement Zamowienie.Zamowienie
		throw new UnsupportedOperationException();
	}

	public java.util.List<PozycjaZamowienia> getPozycje() {
		// TODO - implement Zamowienie.getPozycje
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pozycja
	 */
	public void dodajPozycje(PozycjaZamowienia pozycja) {
		// TODO - implement Zamowienie.dodajPozycje
		throw new UnsupportedOperationException();
	}

	public float obliczCene() {
		// TODO - implement Zamowienie.obliczCene
		throw new UnsupportedOperationException();
	}

	public void finalizuj() {
		// TODO - implement Zamowienie.finalizuj
		throw new UnsupportedOperationException();
	}

}