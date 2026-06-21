package infrastructure.in.web;

import java.util.*;
import application.port.in.*;

public class ZamowienieRequest {

	private int idKupujacego;
	private List<PozycjaZamowieniaDTO> pozycje;

	public int getIdKupujacego() {
		return this.idKupujacego;
	}

	/**
	 * 
	 * @param idKupujacego
	 * @param pozycje
	 */
	public ZamowienieRequest(int idKupujacego, List<PozycjaZamowieniaDTO> pozycje) {
		this.idKupujacego = idKupujacego;
		this.pozycje = new ArrayList<>(pozycje);
	}

	public List<PozycjaZamowieniaDTO> getPozycje() {
		return new ArrayList<>(pozycje);
	}

}
