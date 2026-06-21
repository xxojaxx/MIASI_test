package application.service;

import application.port.in.*;
import application.domain.*;
import application.port.out.*;

public class ZamowienieProduktowService implements ZamowienieProduktowUseCase {

	private ZlozenieZamowieniaService zlozenieZamowieniaService;
	private FinalizacjaZamowieniaService finalizacjaZamowieniaService;
	private PlatnoscPort platnoscPort;
	private PowiadomieniePort powiadomieniePort;

	/**
	 * 
	 * @param zlozenieZamowieniaService
	 * @param finalizacjaZamowieniaService
	 * @param zamowienieRepositoryPort
	 * @param katalogProduktowPort
	 * @param platnoscPort
	 * @param powiadomieniePort
	 */
	public ZamowienieProduktowService(ZlozenieZamowieniaService zlozenieZamowieniaService, FinalizacjaZamowieniaService finalizacjaZamowieniaService, BazaZamowienPort zamowienieRepositoryPort, KatalogProduktowPort katalogProduktowPort, PlatnoscPort platnoscPort, PowiadomieniePort powiadomieniePort) {
		// TODO - implement ZamowienieProduktowService.ZamowienieProduktowService
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idKupujacego
	 * @param pozycje
	 */
	public int zamowProdukty(int idKupujacego, java.util.List<PozycjaZamowieniaDTO> pozycje) {
		// TODO - implement ZamowienieProduktowService.zamowProdukty
		throw new UnsupportedOperationException();
	}

}