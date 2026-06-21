package architecture;

import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClassVisibilityRulesTest {

	private final com.tngtech.archunit.core.domain.JavaClasses classes =
			new ClassFileImporter()
					.withImportOption(new ImportOption.DoNotIncludeTests())
					.importPackages("application", "infrastructure");

	@Test
	void public_api_classes_are_visible_outside_their_packages() {
		assertPublic("application.domain.Zamowienie");
		assertPublic("application.domain.ZamowienieFactory");
		assertPublic("application.domain.ZlozenieZamowieniaService");
		assertPublic("application.domain.FinalizacjaZamowieniaService");
		assertPublic("application.domain.Promocja");
		assertPublic("application.port.in.ZamowienieProduktowUseCase");
		assertPublic("application.port.in.PozycjaZamowieniaDTO");
		assertPublic("application.port.out.BazaZamowienPort");
		assertPublic("application.port.out.KatalogProduktowPort");
		assertPublic("application.port.out.PlatnoscPort");
		assertPublic("application.port.out.PowiadomieniePort");
		assertPublic("application.port.out.ProduktInfo");
		assertPublic("infrastructure.in.web.ZamowienieController");
		assertPublic("infrastructure.in.web.ZamowienieRequest");
		assertPublic("infrastructure.in.web.ZamowienieResponse");
	}

	@Test
	void implementation_details_are_hidden_outside_their_packages() {
		assertPackagePrivate("application.domain.PozycjaZamowienia");
		assertPackagePrivate("application.domain.StatusZamowienia");
		assertPackagePrivate("infrastructure.out.catalog.KatalogProduktowAdapter");
		assertPackagePrivate("infrastructure.out.notification.PowiadomienieAdapter");
		assertPackagePrivate("infrastructure.out.payment.SystemPlatnosciAdapter");
		assertPackagePrivate("infrastructure.out.payment.ZewnetrznySystemPlatnosci");
		assertPackagePrivate("infrastructure.out.persistence.BazaZamowienAdapter");
	}

	private void assertPublic(String className) {
		Set<JavaModifier> modifiers = classes.get(className).getModifiers();

		assertTrue(modifiers.contains(JavaModifier.PUBLIC), className + " should be public");
	}

	private void assertPackagePrivate(String className) {
		Set<JavaModifier> modifiers = classes.get(className).getModifiers();

		assertFalse(modifiers.contains(JavaModifier.PUBLIC), className + " should not be public");
	}
}
