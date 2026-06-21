package infrastructure.in.web;

import java.util.*;
import application.port.in.*;

public class ZamowienieRequest {

	private int idKupujacego;
	private Collection<PozycjaZamowieniaDTO> pozycje;

	public int getIdKupujacego() {
		return this.idKupujacego;
	}

	/**
	 * 
	 * @param idKupujacego
	 * @param pozycje
	 */
	public ZamowienieRequest(int idKupujacego, List pozycje) {
		// TODO - implement ZamowienieRequest.ZamowienieRequest
		throw new UnsupportedOperationException();
	}

	public List getPozycje() {
		// TODO - implement ZamowienieRequest.getPozycje
		throw new UnsupportedOperationException();
	}

}