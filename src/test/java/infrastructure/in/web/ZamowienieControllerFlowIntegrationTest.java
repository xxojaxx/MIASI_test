package infrastructure.in.web;

import application.domain.FinalizacjaZamowieniaService;
import application.domain.ZamowienieFactory;
import application.domain.ZlozenieZamowieniaService;
import application.port.in.PozycjaZamowieniaDTO;
import application.port.out.BazaZamowienPort;
import application.port.out.KatalogProduktowPort;
import application.port.out.PlatnoscPort;
import application.port.out.PowiadomieniePort;
import application.service.ZamowienieProduktowService;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ZamowienieControllerFlowIntegrationTest {

	@Test
	void buyer_can_place_pay_finalize_and_receive_completed_order_response() {
		BazaZamowienPort bazaZamowienPort = newPort("infrastructure.out.persistence.BazaZamowienAdapter");
		KatalogProduktowPort katalogProduktowPort = newPort("infrastructure.out.catalog.KatalogProduktowAdapter");
		PlatnoscPort platnoscPort = newPaymentPort();
		PowiadomieniePort powiadomieniePort = newPort("infrastructure.out.notification.PowiadomienieAdapter");

		ZlozenieZamowieniaService zlozenieZamowieniaService = new ZlozenieZamowieniaService(
				new ZamowienieFactory(),
				katalogProduktowPort,
				bazaZamowienPort
		);
		FinalizacjaZamowieniaService finalizacjaZamowieniaService = new FinalizacjaZamowieniaService(bazaZamowienPort);
		ZamowienieProduktowService useCase = new ZamowienieProduktowService(
				zlozenieZamowieniaService,
				finalizacjaZamowieniaService,
				bazaZamowienPort,
				katalogProduktowPort,
				platnoscPort,
				powiadomieniePort
		);
		ZamowienieController controller = new ZamowienieController(useCase);

		ZamowienieRequest request = new ZamowienieRequest(100, List.of(
				new PozycjaZamowieniaDTO(1, 2),
				new PozycjaZamowieniaDTO(2, 1)
		));

		ZamowienieResponse response = controller.zamowProdukty(request);

		assertEquals(1, response.getIdZamowienia());
		assertEquals("Zamowienie zostalo zlozone, oplacone i sfinalizowane.", response.getKomunikat());
		assertEquals(200, bazaZamowienPort.pobierz(response.getIdZamowienia()).obliczCene());
		assertTrue(bazaZamowienPort.pobierz(response.getIdZamowienia()).czySfinalizowane());
	}

	@SuppressWarnings("unchecked")
	private static <T> T newPort(String className) {
		try {
			Constructor<?> constructor = Class.forName(className).getDeclaredConstructor();
			constructor.setAccessible(true);
			return (T) constructor.newInstance();
		} catch (ReflectiveOperationException exception) {
			throw new AssertionError("Nie udalo sie utworzyc adaptera: " + className, exception);
		}
	}

	private static PlatnoscPort newPaymentPort() {
		try {
			Object zewnetrznySystemPlatnosci = newPort("infrastructure.out.payment.ZewnetrznySystemPlatnosci");
			Constructor<?> constructor = Class.forName("infrastructure.out.payment.SystemPlatnosciAdapter")
					.getDeclaredConstructor(zewnetrznySystemPlatnosci.getClass());
			constructor.setAccessible(true);
			return (PlatnoscPort) constructor.newInstance(zewnetrznySystemPlatnosci);
		} catch (ReflectiveOperationException exception) {
			throw new AssertionError("Nie udalo sie utworzyc adaptera platnosci.", exception);
		}
	}
}
