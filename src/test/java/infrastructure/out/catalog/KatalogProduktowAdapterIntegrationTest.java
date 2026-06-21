package infrastructure.out.catalog;

import application.port.out.KatalogProduktowPort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KatalogProduktowAdapterIntegrationTest {

	@Test
	void adapter_implements_catalog_port() {
		assertTrue(KatalogProduktowPort.class.isAssignableFrom(KatalogProduktowAdapter.class));
	}

	@Test
	void constructor_is_not_implemented_yet() {
		assertThrows(UnsupportedOperationException.class, KatalogProduktowAdapter::new);
	}
}
