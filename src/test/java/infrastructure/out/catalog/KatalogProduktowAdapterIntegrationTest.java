package infrastructure.out.catalog;

import application.port.out.KatalogProduktowPort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KatalogProduktowAdapterIntegrationTest {

	@Test
	void adapter_implements_catalog_port() {
		assertTrue(KatalogProduktowPort.class.isAssignableFrom(KatalogProduktowAdapter.class));
	}

	@Test
	void adapter_returns_products_and_promotions() {
		KatalogProduktowPort port = new KatalogProduktowAdapter();

		assertEquals("Ksiazka", port.pobierzProdukt(1).getNazwa());
		assertEquals(50, port.pobierzProdukt(1).getCena());
		assertNotNull(port.pobierzPromocje(1));
		assertEquals(40, port.pobierzPromocje(1).getCenaPromocyjna());
	}

	@Test
	void adapter_rejects_unknown_product() {
		KatalogProduktowPort port = new KatalogProduktowAdapter();

		assertThrows(IllegalArgumentException.class, () -> port.pobierzProdukt(999));
	}
}
