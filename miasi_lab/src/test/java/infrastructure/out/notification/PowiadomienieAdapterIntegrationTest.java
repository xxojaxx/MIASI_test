package infrastructure.out.notification;

import application.port.out.PowiadomieniePort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PowiadomienieAdapterIntegrationTest {

	@Test
	void adapter_can_be_used_through_notification_port_inside_its_package() {
		PowiadomieniePort port = new PowiadomienieAdapter();

		assertTrue(port instanceof PowiadomienieAdapter);
	}

	@Test
	void sending_notification_is_not_implemented_yet() {
		PowiadomieniePort port = new PowiadomienieAdapter();

		assertThrows(UnsupportedOperationException.class, () -> port.powiadomOUkonczeniu(1, 10));
	}
}
