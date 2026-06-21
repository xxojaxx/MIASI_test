package application.port.in;

public interface ZamowienieProduktowUseCase {

	/**
	 * 
	 * @param idKupujacego
	 * @param pozycje
	 */
	abstract int zamowProdukty(int idKupujacego, java.util.List<PozycjaZamowieniaDTO> pozycje);

}