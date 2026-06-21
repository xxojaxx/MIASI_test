package infrastructure.out.payment;

import application.port.out.PlatnoscPort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SystemPlatnosciAdapterIntegrationTest {

	@Test
	void adapter_implements_payment_port() {
		assertTrue(PlatnoscPort.class.isAssignableFrom(SystemPlatnosciAdapter.class));
	}

	@Test
	void adapter_constructor_is_not_implemented_yet() {
		ZewnetrznySystemPlatnosci zewnetrznySystemPlatnosci = new ZewnetrznySystemPlatnosci();

		assertThrows(
				UnsupportedOperationException.class,
				() -> new SystemPlatnosciAdapter(zewnetrznySystemPlatnosci)
		);
	}

	@Test
	void external_payment_system_is_package_local() {
		assertTrue(SystemPlatnosciAdapter.class.getPackageName()
				.equals(ZewnetrznySystemPlatnosci.class.getPackageName()));
	}
}
