package application.domain;

import java.util.List;
import application.port.out.*;

public class ZlozenieZamowieniaService {

	ZamowienieFactory zamowienieFactory;
	private KatalogProduktowPort katalogProduktowPort;
	private BazaZamowienPort bazaZamowienPort;

	/**
	 * 
	 * @param zamowienieFactory
	 */
	public ZlozenieZamowieniaService(ZamowienieFactory zamowienieFactory) {
		this.zamowienieFactory = zamowienieFactory;
	}

	public ZlozenieZamowieniaService(ZamowienieFactory zamowienieFactory, KatalogProduktowPort katalogProduktowPort, BazaZamowienPort bazaZamowienPort) {
		this.zamowienieFactory = zamowienieFactory;
		this.katalogProduktowPort = katalogProduktowPort;
		this.bazaZamowienPort = bazaZamowienPort;
	}

	/**
	 * 
	 * @param idKupujacego
	 * @param pozycje
	 */
	public Zamowienie zlozZamowienie(int idKupujacego, List pozycje) {
		if (katalogProduktowPort == null || bazaZamowienPort == null) {
			throw new IllegalStateException("Brakuje portow wymaganych do zlozenia zamowienia.");
		}

		Zamowienie zamowienie = zamowienieFactory.utworz(idKupujacego);
		for (Object pozycja : pozycje) {
			int[] spec = (int[]) pozycja;
			int idProduktu = spec[0];
			int iloscProduktu = spec[1];
			ProduktInfo produkt = katalogProduktowPort.pobierzProdukt(idProduktu);
			Promocja promocja = katalogProduktowPort.pobierzPromocje(idProduktu);
			zamowienie.dodajPozycje(new PozycjaZamowienia(
					idProduktu,
					iloscProduktu,
					produkt.getCena(),
					promocja
			));
		}

		bazaZamowienPort.zapisz(zamowienie);
		return zamowienie;
	}

}
