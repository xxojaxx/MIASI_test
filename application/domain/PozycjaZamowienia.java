package application.domain;

class PozycjaZamowienia {

	private int idProduktu;
	private int iloscProduktu;
	private float cena;
	private float cenaPromocyjna;
	private Promocja promocja;
	private int idPozycjaZamowienia;

	public int getIdProduktu() {
		return this.idProduktu;
	}

	public int getIloscProduktu() {
		return this.iloscProduktu;
	}

	public float getCena() {
		return this.cena;
	}

	public float getCenaPromocyjna() {
		return this.cenaPromocyjna;
	}

	public Promocja getPromocja() {
		return this.promocja;
	}

	public float obliczCene() {
		if (sprawdzPromocje()) {
			return obliczPromocje();
		}
		return cena * iloscProduktu;
	}

	/**
	 * 
	 * @param promocja
	 */
	public void ustawPromocje(Promocja promocja) {
		this.promocja = promocja;
		this.cenaPromocyjna = promocja == null ? 0 : promocja.getCenaPromocyjna();
	}

	public boolean sprawdzPromocje() {
		return promocja != null && promocja.czyDotyczy(iloscProduktu);
	}

	public float obliczPromocje() {
		return cenaPromocyjna * iloscProduktu;
	}

	/**
	 * 
	 * @param idProduktu
	 * @param iloscProduktu
	 * @param cena
	 * @param promocja
	 */
	public PozycjaZamowienia(int idProduktu, int iloscProduktu, float cena, Promocja promocja) {
		this.idProduktu = idProduktu;
		this.iloscProduktu = iloscProduktu;
		this.cena = cena;
		ustawPromocje(promocja);
	}

}
