package application.port.in;

public class PozycjaZamowieniaDTO {

	private int idProduktu;
	private int iloscProduktu;

	public int getIdProduktu() {
		return this.idProduktu;
	}

	public int getIloscProduktu() {
		return this.iloscProduktu;
	}

	/**
	 * 
	 * @param idProduktu
	 * @param iloscProduktu
	 */
	public PozycjaZamowieniaDTO(int idProduktu, int iloscProduktu) {
		this.idProduktu = idProduktu;
		this.iloscProduktu = iloscProduktu;
	}

}
