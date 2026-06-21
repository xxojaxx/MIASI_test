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

	public boolean czySfinalizowane() {
		return status == StatusZamowienia.SFINALIZOWANE;
	}

	/**
	 * 
	 * @param idZamowienia
	 * @param idKupujacego
	 * @param status
	 */
	public Zamowienie(int idZamowienia, int idKupujacego, StatusZamowienia status) {
		this(idZamowienia, idKupujacego, status, new ArrayList<>());
	}

	/**
	 * 
	 * @param idZamowienia
	 * @param idKupujacego
	 * @param status
	 * @param pozycje
	 */
	public Zamowienie(int idZamowienia, int idKupujacego, StatusZamowienia status, java.util.List<PozycjaZamowienia> pozycje) {
		this.idZamowienia = idZamowienia;
		this.idKupujacego = idKupujacego;
		this.status = status;
		this.pozycje = new ArrayList<>(pozycje);
	}

	public java.util.List<PozycjaZamowienia> getPozycje() {
		return new ArrayList<>(pozycje);
	}

	/**
	 * 
	 * @param pozycja
	 */
	public void dodajPozycje(PozycjaZamowienia pozycja) {
		pozycje.add(pozycja);
	}

	public float obliczCene() {
		float suma = 0;
		for (PozycjaZamowienia pozycja : pozycje) {
			suma += pozycja.obliczCene();
		}
		return suma;
	}

	public void finalizuj() {
		this.status = StatusZamowienia.SFINALIZOWANE;
	}

}
