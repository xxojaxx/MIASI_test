package application.port.out;

public interface PlatnoscPort {

	/**
	 * 
	 * @param idKupujacego
	 * @param kwota
	 */
	abstract boolean zaplac(int idKupujacego, float kwota);

}