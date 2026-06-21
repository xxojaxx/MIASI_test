package infrastructure.out.payment;

import application.port.out.PlatnoscPort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SystemPlatnosciAdapterIntegrationTest {

	@Test
	void adapter_implements_payment_port() {
		assertTrue(PlatnoscPort.class.isAssignableFrom(SystemPlatnosciAdapter.class));
	}

	@Test
	void adapter_delegates_successful_payment_to_external_system() {
		PlatnoscPort port = new SystemPlatnosciAdapter(new ZewnetrznySystemPlatnosci());

		assertTrue(port.zaplac(1, 200));
	}

	@Test
	void adapter_rejects_invalid_payment() {
		PlatnoscPort port = new SystemPlatnosciAdapter(new ZewnetrznySystemPlatnosci());

		assertFalse(port.zaplac(1, 0));
	}

	@Test
	void external_payment_system_is_package_local() {
		assertTrue(SystemPlatnosciAdapter.class.getPackageName()
				.equals(ZewnetrznySystemPlatnosci.class.getPackageName()));
	}
}
