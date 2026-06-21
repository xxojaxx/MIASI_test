package architecture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CompileTimeVisibilityTest {

	@TempDir
	Path tempDir;

	@Test
	void public_classes_can_be_used_from_an_external_package() throws IOException {
		String source = """
				package probe;

				import application.domain.Promocja;
				import application.domain.Zamowienie;
				import application.port.in.ZamowienieProduktowUseCase;
				import application.port.out.BazaZamowienPort;
				import infrastructure.in.web.ZamowienieController;

				class VisibleProbe {
					Promocja promocja;
					Zamowienie zamowienie;
					ZamowienieProduktowUseCase useCase;
					BazaZamowienPort bazaZamowienPort;
					ZamowienieController controller;
				}
				""";

		assertTrue(compiles("VisibleProbe", source));
	}

	@Test
	void package_private_domain_classes_cannot_be_used_from_an_external_package() throws IOException {
		String source = """
				package probe;

				import application.domain.PozycjaZamowienia;
				import application.domain.StatusZamowienia;

				class HiddenDomainProbe {
					PozycjaZamowienia pozycja;
					StatusZamowienia status;
				}
				""";

		assertFalse(compiles("HiddenDomainProbe", source));
	}

	@Test
	void package_private_infrastructure_implementations_cannot_be_used_from_an_external_package() throws IOException {
		String source = """
				package probe;

				import infrastructure.out.catalog.KatalogProduktowAdapter;
				import infrastructure.out.notification.PowiadomienieAdapter;
				import infrastructure.out.payment.SystemPlatnosciAdapter;
				import infrastructure.out.persistence.BazaZamowienAdapter;

				class HiddenInfrastructureProbe {
					KatalogProduktowAdapter katalog;
					PowiadomienieAdapter powiadomienie;
					SystemPlatnosciAdapter platnosci;
					BazaZamowienAdapter baza;
				}
				""";

		assertFalse(compiles("HiddenInfrastructureProbe", source));
	}

	@Test
	void catalog_adapter_can_be_wired_to_its_port_inside_catalog_package() throws IOException {
		String source = """
				package infrastructure.out.catalog;

				import application.port.out.KatalogProduktowPort;

				class CatalogAdapterProbe {
					KatalogProduktowPort port = new KatalogProduktowAdapter();
				}
				""";

		assertTrue(compiles("CatalogAdapterProbe", source));
	}

	@Test
	void notification_adapter_can_be_wired_to_its_port_inside_notification_package() throws IOException {
		String source = """
				package infrastructure.out.notification;

				import application.port.out.PowiadomieniePort;

				class NotificationAdapterProbe {
					PowiadomieniePort port = new PowiadomienieAdapter();
				}
				""";

		assertTrue(compiles("NotificationAdapterProbe", source));
	}

	@Test
	void payment_adapter_can_be_wired_to_its_port_inside_payment_package() throws IOException {
		String source = """
				package infrastructure.out.payment;

				import application.port.out.PlatnoscPort;

				class PaymentAdapterProbe {
					PlatnoscPort port = new SystemPlatnosciAdapter(new ZewnetrznySystemPlatnosci());
				}
				""";

		assertTrue(compiles("PaymentAdapterProbe", source));
	}

	@Test
	void persistence_adapter_can_be_wired_to_its_port_inside_persistence_package() throws IOException {
		String source = """
				package infrastructure.out.persistence;

				import application.port.out.BazaZamowienPort;

				class PersistenceAdapterProbe {
					BazaZamowienPort port = new BazaZamowienAdapter();
				}
				""";

		assertTrue(compiles("PersistenceAdapterProbe", source));
	}

	@Test
	void external_code_can_depend_on_ports_without_seeing_adapter_classes() throws IOException {
		String source = """
				package probe;

				import application.port.out.BazaZamowienPort;
				import application.port.out.KatalogProduktowPort;
				import application.port.out.PlatnoscPort;
				import application.port.out.PowiadomieniePort;

				class PortOnlyProbe {
					BazaZamowienPort bazaZamowienPort;
					KatalogProduktowPort katalogProduktowPort;
					PlatnoscPort platnoscPort;
					PowiadomieniePort powiadomieniePort;
				}
				""";

		assertTrue(compiles("PortOnlyProbe", source));
	}

	private boolean compiles(String className, String source) throws IOException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		assertNotNull(compiler, "Tests must be run with a JDK, not a JRE");

		Path sourceFile = tempDir.resolve(className + ".java");
		Files.writeString(sourceFile, source);

		List<String> options = List.of(
				"-classpath", System.getProperty("java.class.path"),
				"-d", tempDir.resolve("classes").toString()
		);

		ByteArrayOutputStream compilerOutput = new ByteArrayOutputStream();

		return compiler.run(null, compilerOutput, compilerOutput,
				options.get(0), options.get(1), options.get(2), options.get(3), sourceFile.toString()) == 0;
	}
}
