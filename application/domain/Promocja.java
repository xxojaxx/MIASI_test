package application.domain;

public class Promocja {

	private int minimalnaIlosc;
	private float cenaPromocyjna;

	public int getMinimalnaIlosc() {
		return this.minimalnaIlosc;
	}

	public float getCenaPromocyjna() {
		return this.cenaPromocyjna;
	}

	/**
	 * 
	 * @param ilosc
	 */
	public boolean czyDotyczy(int ilosc) {
		return ilosc >= minimalnaIlosc;
	}

	/**
	 * 
	 * @param minimalnaIlosc
	 * @param cenaPromocyjna
	 */
	public Promocja(int minimalnaIlosc, float cenaPromocyjna) {
		this.minimalnaIlosc = minimalnaIlosc;
		this.cenaPromocyjna = cenaPromocyjna;
	}

}
