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
		// TODO - implement PozycjaZamowienia.obliczCene
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param promocja
	 */
	public void ustawPromocje(Promocja promocja) {
		// TODO - implement PozycjaZamowienia.ustawPromocje
		throw new UnsupportedOperationException();
	}

	public boolean sprawdzPromocje() {
		// TODO - implement PozycjaZamowienia.sprawdzPromocje
		throw new UnsupportedOperationException();
	}

	public float obliczPromocje() {
		// TODO - implement PozycjaZamowienia.obliczPromocje
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idProduktu
	 * @param iloscProduktu
	 * @param cena
	 * @param promocja
	 */
	public PozycjaZamowienia(int idProduktu, int iloscProduktu, float cena, Promocja promocja) {
		// TODO - implement PozycjaZamowienia.PozycjaZamowienia
		throw new UnsupportedOperationException();
	}

}