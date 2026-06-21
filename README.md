# Testy w projekcie

Projekt zawiera testy architektury, testy widocznosci klas oraz testy integracyjne adapterow.
Kod produkcyjny implementuje prosty scenariusz zlozenia zamowienia na bazie klas istniejacych w projekcie: utworzenie pozycji, naliczenie promocji, zapis wstepnego zamowienia, platnosc, finalizacja i powiadomienie klienta.

## Uruchamianie

Wszystkie testy JUnit/ArchUnit uruchamia sie poleceniem:

```powershell
mvn test
```

Jesli Maven nie jest jeszcze dodany do `PATH`, mozna uruchomic go pelna sciezka:

```powershell
& "C:\Program Files\Apache\Maven\apache-maven-3.9.16\bin\mvn.cmd" test
```

## Testy architektury

Plik: `src/test/java/architecture/ArchitectureRulesTest.java`

Te testy sprawdzaja zaleznosci miedzy warstwami:

- `application` nie powinno zalezec od `infrastructure`.
- `infrastructure` jest warstwa brzegowa i moze zalezec od `application`.
- Pakiety aplikacyjne sa podzielone na domena, porty wejsciowe, porty wyjsciowe i serwisy aplikacyjne.
- Adaptery wejsciowe i wyjsciowe znajduja sie po stronie infrastruktury.

## Testy widocznosci klas

Plik: `src/test/java/architecture/ClassVisibilityRulesTest.java`

Te testy sprawdzaja deklarowana widocznosc klas:

- klasy i interfejsy bedace publicznym API musza byc `public`,
- szczegoly implementacyjne maja pozostac ukryte poza pakietem, czyli nie moga byc `public`.

Przyklady klas publicznych:

- `application.domain.Zamowienie`
- `application.domain.Promocja`
- `application.port.in.ZamowienieProduktowUseCase`
- `application.port.out.BazaZamowienPort`
- `infrastructure.in.web.ZamowienieController`

Przyklady klas ukrytych pakietowo:

- `application.domain.PozycjaZamowienia`
- `application.domain.StatusZamowienia`
- `infrastructure.out.catalog.KatalogProduktowAdapter`
- `infrastructure.out.payment.SystemPlatnosciAdapter`
- `infrastructure.out.persistence.BazaZamowienAdapter`

## Testy kompilacyjne widocznosci

Plik: `src/test/java/architecture/CompileTimeVisibilityTest.java`

Te testy tworza male klasy testowe w locie i probuja je skompilowac kompilatorem Javy.
Dzieki temu sprawdzaja realna widocznosc z punktu widzenia kodu w innych pakietach.

Sprawdzane sa dwa typy sytuacji:

- pozytywne: klasa powinna byc widoczna i probe powinno dac sie skompilowac,
- negatywne: klasa nie powinna byc widoczna i kompilacja powinna sie nie udac.

Testy sprawdzaja m.in.:

- publiczne klasy API mozna zaimportowac z obcego pakietu,
- klas domenowych ukrytych pakietowo nie mozna zaimportowac z obcego pakietu,
- adapterow infrastruktury ukrytych pakietowo nie mozna zaimportowac z obcego pakietu,
- adaptery mozna podlaczyc do odpowiednich portow wewnatrz ich wlasnych pakietow,
- kod zewnetrzny moze zalezec od portow, bez widzenia klas adapterow.

## Testy integracyjne adapterow

Testy adapterow znajduja sie w pakietach odpowiadajacych konkretnym adapterom:

- `src/test/java/infrastructure/out/catalog/KatalogProduktowAdapterIntegrationTest.java`
- `src/test/java/infrastructure/out/notification/PowiadomienieAdapterIntegrationTest.java`
- `src/test/java/infrastructure/out/payment/SystemPlatnosciAdapterIntegrationTest.java`
- `src/test/java/infrastructure/out/persistence/BazaZamowienAdapterIntegrationTest.java`
- `src/test/java/infrastructure/in/web/ZamowienieControllerFlowIntegrationTest.java`

Te testy sprawdzaja, czy adaptery sa poprawnie podpiete do portow:

- `KatalogProduktowAdapter` implementuje `KatalogProduktowPort`,
- `PowiadomienieAdapter` moze byc uzyty jako `PowiadomieniePort`,
- `SystemPlatnosciAdapter` implementuje `PlatnoscPort`,
- `BazaZamowienAdapter` moze byc uzyty jako `BazaZamowienPort`.

Testy sprawdzaja tez realne zachowanie adapterow in-memory:

- katalog zwraca produkty i promocje,
- platnosc przechodzi dla poprawnych danych i jest odrzucana dla niepoprawnej kwoty,
- baza zamowien zapisuje, pobiera i aktualizuje zamowienie,
- adapter powiadomien zapisuje tresc ostatniego powiadomienia,
- pelny scenariusz przez `ZamowienieController` tworzy zamowienie, liczy cene z promocja, zapisuje je, finalizuje i zwraca odpowiedz dla klienta.

Adaptery infrastruktury pozostaja ukryte pakietowo, zgodnie z testami widocznosci.
