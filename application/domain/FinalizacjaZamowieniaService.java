package application.domain;

import application.port.out.*;

public class FinalizacjaZamowieniaService {

	private BazaZamowienPort bazaZamowienPort;

	public FinalizacjaZamowieniaService() {
	}

	public FinalizacjaZamowieniaService(BazaZamowienPort bazaZamowienPort) {
		this.bazaZamowienPort = bazaZamowienPort;
	}

	/**
	 * 
	 * @param zamowienie
	 */
	public void finalizuj(Zamowienie zamowienie) {
		zamowienie.finalizuj();
		if (bazaZamowienPort != null) {
			bazaZamowienPort.aktualizuj(zamowienie);
		}
	}

	public Zamowienie finalizuj(int idZamowienia) {
		if (bazaZamowienPort == null) {
			throw new IllegalStateException("Brakuje portu bazy zamowien.");
		}

		Zamowienie zamowienie = bazaZamowienPort.pobierz(idZamowienia);
		finalizuj(zamowienie);
		return zamowienie;
	}

}
