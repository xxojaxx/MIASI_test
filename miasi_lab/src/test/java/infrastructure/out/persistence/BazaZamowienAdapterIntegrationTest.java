package infrastructure.out.persistence;

import application.port.out.BazaZamowienPort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BazaZamowienAdapterIntegrationTest {

	@Test
	void adapter_can_be_used_through_order_database_port_inside_its_package() {
		BazaZamowienPort port = new BazaZamowienAdapter();

		assertTrue(port instanceof BazaZamowienAdapter);
	}

	@Test
	void saving_order_is_not_implemented_yet() {
		BazaZamowienPort port = new BazaZamowienAdapter();

		assertThrows(UnsupportedOperationException.class, () -> port.zapisz(null));
	}

	@Test
	void loading_order_is_not_implemented_yet() {
		BazaZamowienPort port = new BazaZamowienAdapter();

		assertThrows(UnsupportedOperationException.class, () -> port.pobierz(10));
	}

	@Test
	void updating_order_is_not_implemented_yet() {
		BazaZamowienPort port = new BazaZamowienAdapter();

		assertThrows(UnsupportedOperationException.class, () -> port.aktualizuj(null));
	}
}
