package infrastructure.out.persistence;

import application.domain.Zamowienie;
import application.domain.ZamowienieFactory;
import application.port.out.BazaZamowienPort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BazaZamowienAdapterIntegrationTest {

	@Test
	void adapter_can_be_used_through_order_database_port_inside_its_package() {
		BazaZamowienPort port = new BazaZamowienAdapter();

		assertTrue(port instanceof BazaZamowienAdapter);
	}

	@Test
	void adapter_saves_and_loads_order() {
		BazaZamowienPort port = new BazaZamowienAdapter();
		Zamowienie zamowienie = new ZamowienieFactory().utworz(1);

		port.zapisz(zamowienie);

		assertSame(zamowienie, port.pobierz(zamowienie.getIdZamowienia()));
	}

	@Test
	void adapter_updates_existing_order() {
		BazaZamowienPort port = new BazaZamowienAdapter();
		Zamowienie zamowienie = new ZamowienieFactory().utworz(1);
		port.zapisz(zamowienie);

		zamowienie.finalizuj();
		port.aktualizuj(zamowienie);

		assertSame(zamowienie, port.pobierz(zamowienie.getIdZamowienia()));
	}
}
