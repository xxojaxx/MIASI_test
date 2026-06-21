package architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(
		packages = {"application", "infrastructure"},
		importOptions = ImportOption.DoNotIncludeTests.class
)
class ArchitectureRulesTest {

	@ArchTest
	static final ArchRule application_does_not_depend_on_infrastructure =
			layeredArchitecture().consideringOnlyDependenciesInLayers()
					.layer("Domain").definedBy("application.domain..")
					.layer("UseCasePorts").definedBy("application.port.in..")
					.layer("DrivenPorts").definedBy("application.port.out..")
					.layer("ApplicationService").definedBy("application.service..")
					.layer("InboundAdapters").definedBy("infrastructure.in..")
					.layer("OutboundAdapters").definedBy("infrastructure.out..")
					.whereLayer("Domain").mayOnlyBeAccessedByLayers("ApplicationService", "DrivenPorts", "OutboundAdapters")
					.whereLayer("UseCasePorts").mayOnlyBeAccessedByLayers("ApplicationService", "InboundAdapters")
					.whereLayer("DrivenPorts").mayOnlyBeAccessedByLayers("Domain", "ApplicationService", "OutboundAdapters")
					.whereLayer("ApplicationService").mayNotBeAccessedByAnyLayer()
					.whereLayer("InboundAdapters").mayNotBeAccessedByAnyLayer()
					.whereLayer("OutboundAdapters").mayNotBeAccessedByAnyLayer();

	@ArchTest
	static final ArchRule infrastructure_is_on_the_edge =
			layeredArchitecture().consideringOnlyDependenciesInLayers()
					.layer("Application").definedBy("application..")
					.layer("Infrastructure").definedBy("infrastructure..")
					.whereLayer("Application").mayNotAccessAnyLayer()
					.whereLayer("Infrastructure").mayOnlyAccessLayers("Application");
}
