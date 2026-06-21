package application.service;

import application.port.in.*;
import application.domain.*;
import application.port.out.*;

import java.util.ArrayList;
import java.util.List;

public class ZamowienieProduktowService implements ZamowienieProduktowUseCase {

	private ZlozenieZamowieniaService zlozenieZamowieniaService;
	private FinalizacjaZamowieniaService finalizacjaZamowieniaService;
	private BazaZamowienPort zamowienieRepositoryPort;
	private KatalogProduktowPort katalogProduktowPort;
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
		this.zamowienieRepositoryPort = zamowienieRepositoryPort;
		this.katalogProduktowPort = katalogProduktowPort;
		this.platnoscPort = platnoscPort;
		this.powiadomieniePort = powiadomieniePort;
		this.zlozenieZamowieniaService = zlozenieZamowieniaService != null
				? zlozenieZamowieniaService
				: new ZlozenieZamowieniaService(new ZamowienieFactory(), katalogProduktowPort, zamowienieRepositoryPort);
		this.finalizacjaZamowieniaService = finalizacjaZamowieniaService != null
				? finalizacjaZamowieniaService
				: new FinalizacjaZamowieniaService(zamowienieRepositoryPort);
	}

	/**
	 * 
	 * @param idKupujacego
	 * @param pozycje
	 */
	public int zamowProdukty(int idKupujacego, java.util.List<PozycjaZamowieniaDTO> pozycje) {
		List<int[]> specyfikacjaPozycji = new ArrayList<>();
		for (PozycjaZamowieniaDTO pozycja : pozycje) {
			specyfikacjaPozycji.add(new int[] {pozycja.getIdProduktu(), pozycja.getIloscProduktu()});
		}

		Zamowienie zamowienie = zlozenieZamowieniaService.zlozZamowienie(idKupujacego, specyfikacjaPozycji);
		float cenaDoZaplaty = zamowienie.obliczCene();

		if (!platnoscPort.zaplac(idKupujacego, cenaDoZaplaty)) {
			throw new IllegalStateException("Platnosc nie zostala zrealizowana.");
		}

		Zamowienie finalizowaneZamowienie = finalizacjaZamowieniaService.finalizuj(zamowienie.getIdZamowienia());
		powiadomieniePort.powiadomOUkonczeniu(idKupujacego, finalizowaneZamowienie.getIdZamowienia());

		return finalizowaneZamowienie.getIdZamowienia();
	}

}
